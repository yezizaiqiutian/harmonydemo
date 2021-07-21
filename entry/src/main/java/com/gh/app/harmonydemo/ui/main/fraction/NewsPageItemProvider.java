package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import com.github.boxuanjia.toycar.ToyCar;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

import java.util.List;

public class NewsPageItemProvider extends BaseItemProvider {

    private List<NewsBean> list;
    private Context context;

    public NewsPageItemProvider(Context context, List<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    public void updata(List<NewsBean> list) {
        this.list = list;
        notifyDataChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        if (list != null && position >= 0 && position < list.size()){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        final Component cpt;
        if (convertComponent == null) {
            cpt = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_newspage, null, false);
        } else {
            cpt = convertComponent;
        }
        NewsBean newsBean = list.get(position);
        Text tv_title = (Text) cpt.findComponentById(ResourceTable.Id_tv_title);
        Text tv_content = (Text) cpt.findComponentById(ResourceTable.Id_tv_content);
        Image iv_img = (Image) cpt.findComponentById(ResourceTable.Id_iv_img);
        tv_title.setText(newsBean.getTitle());
        tv_content.setText(newsBean.getContent());
        cpt.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                new ToastDialog(context).setText(newsBean.getContent()).show();
            }
        });

        ToyCar.load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcar0.autoimg.cn%2Fupload%2F2013%2F2%2F18%2Fu_20130218165304639264.jpg&refer=http%3A%2F%2Fcar0.autoimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629449230&t=aa0b75e90d902ef763c8280e70533265").into(iv_img);

        return cpt;
    }
}

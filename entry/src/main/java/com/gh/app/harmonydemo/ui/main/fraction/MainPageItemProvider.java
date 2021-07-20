package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import ohos.agp.components.*;
import ohos.app.Context;

import java.util.List;

public class MainPageItemProvider extends BaseItemProvider {

    private List<NewsBean> list;
    private Context context;

    public MainPageItemProvider(Context context,List<NewsBean> list) {
        this.context = context;
        this.list = list;
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
            cpt = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_mainpage, null, false);
        } else {
            cpt = convertComponent;
        }
        NewsBean newsBean = list.get(position);
        Text tv_title = (Text) cpt.findComponentById(ResourceTable.Id_tv_title);
        Text tv_content = (Text) cpt.findComponentById(ResourceTable.Id_tv_content);
        tv_title.setText(newsBean.getTitle());
        tv_content.setText(newsBean.getContent());
        return cpt;
    }
}

package com.news.ye.newsdemo.custom_arr;

import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.loader.SkinManager;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * Created by Feng on 2016/7/30.
 */
public class CollapsingToolbarLayoutArr extends SkinAttr {
    @Override
    public void apply(View view) {
        if (view instanceof CollapsingToolbarLayout){
            CollapsingToolbarLayout c1= (CollapsingToolbarLayout) view;
            if (RES_TYPE_NAME_COLOR.equals(attrValueTypeName)){
//                int color = SkinManager.getInstance().getColor(attrValueRefId);
                int color= SkinResourcesUtils.getColor(attrValueRefId);
                c1.setContentScrimColor(color);
                c1.setBackgroundColor(color);
            }
        }
    }
}

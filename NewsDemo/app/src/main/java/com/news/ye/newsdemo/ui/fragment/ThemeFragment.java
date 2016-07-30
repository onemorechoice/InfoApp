package com.news.ye.newsdemo.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.data.entity.Theme;
import com.news.ye.newsdemo.ui.adapter.ThemeAdapter;
import com.news.ye.newsdemo.ui.base.BaseFragment;
import com.news.ye.newsdemo.util.FileUtils;
import com.news.ye.newsdemo.util.Toasts;
import com.news.ye.newsdemo.util.Tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import solid.ren.skinlibrary.listener.ILoaderListener;
import solid.ren.skinlibrary.loader.SkinManager;
import solid.ren.skinlibrary.utils.SkinFileUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends BaseFragment {


    private Unbinder umBind;
    @BindView(R.id.rv_theme)
    RecyclerView rv;
    private String [] theme_name={"经典蓝色","活力橙色","清新绿色"};
    private int [] theme_color={R.drawable.theme_a,R.drawable.theme_b,R.drawable.theme_c};
    private List<Theme> list;
    private ThemeAdapter adapter;
    private MaterialDialog dialog;
    private static String SKIN_BROWN_NAME = "skin_brown.skin";
    private static String SKIN_BLACK_NAME = "skin_black.skin";
    private static String SKIN_ORANGE_NAME = "theme_orange.skin";
    private static String SKIN_GREEN_NAME = "theme_green.skin";
    private static String SKIN_DIR;
    private String skinFullName;


    public ThemeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_theme, container, false);
        umBind= ButterKnife.bind(this,view);
        setThemes();
        initView();
        return view;
    }

    private void setThemes() {
      SKIN_DIR = FileUtils.getSkinDirPath(getActivity());
      list=new ArrayList<>();
        for (int i = 0; i <theme_name.length; i++) {
            Theme theme=new Theme();
            theme.setName(theme_name[i]);
            theme.setImg(theme_color[i]);
            list.add(theme);
        }
    }

    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        rv.setLayoutManager(layoutManager);
        adapter=new ThemeAdapter(getActivity(),list);
        adapter.setOnItemClickListener(mOnItemClickListener);
        rv.setAdapter(adapter);
        //设置间距
        ThemeAdapter.SpaceItemDecoration sd=adapter.new SpaceItemDecoration(Tools.dpTopx(getActivity(),16));
        rv.addItemDecoration(sd);
    }
    ThemeAdapter.OnItemClickListener mOnItemClickListener=new ThemeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            String theme_name=null;
            if (position==0){
                SkinManager.getInstance().restoreDefaultTheme();
                return;
            }
            if (position==1){
               skinFullName = SKIN_DIR + File.separator + SKIN_ORANGE_NAME;
               FileUtils.moveRawToDir(getActivity(), SKIN_ORANGE_NAME, skinFullName);
                theme_name=SKIN_ORANGE_NAME;

            }
            if (position==2){
                skinFullName = SKIN_DIR + File.separator + SKIN_GREEN_NAME ;
                FileUtils.moveRawToDir(getActivity(), SKIN_GREEN_NAME , skinFullName);
                theme_name=SKIN_GREEN_NAME  ;
            }
            dialog = new MaterialDialog.Builder(getContext())
                    .title("换肤中")
                    .content("请耐心等待")
                    .canceledOnTouchOutside(false)
                    .progress(false, 100, true).build();


            File skin = new File(skinFullName);
            if (!skin.exists()) {
                Toasts.showShort( "请检查" + skinFullName + "是否存在");
                return;
            }
            Log.i("skin",SkinFileUtils.getSkinDir(getActivity()));
            SkinManager.getInstance().loadSkin(theme_name, new ILoaderListener() {
                @Override
                public void onStart() {
                    dialog.show();
                }

                @Override
                public void onSuccess() {
                   dialog.dismiss();
                    Toasts.showShort("切换成功");
                }

                @Override
                public void onFailed(String errMsg) {
                    dialog.dismiss();
                    Toasts.showShort("切换失败");
                }

                @Override
                public void onProgress(int progress) {

                }
            });


        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        umBind.unbind();
    }
}

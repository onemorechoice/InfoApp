package com.news.ye.newsdemo.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.news.ye.newsdemo.R;
import com.news.ye.newsdemo.presenter.Impl.PicPresenterImpl;
import com.news.ye.newsdemo.presenter.PicPresenter;
import com.news.ye.newsdemo.ui.base.ToolbarActivity;
import com.news.ye.newsdemo.ui.iView.PicView;
import com.news.ye.newsdemo.util.DateFormatUtil;
import com.news.ye.newsdemo.util.Toasts;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureActivity extends ToolbarActivity implements PicView{
    public static final String EXTRA_IMAGE_URL = "image_url";
    public static final String EXTRA_IMAGE_TITLE = "image_title";
    public static final String TRANSIT_PIC = "picture";
    private Unbinder umBinder;
    private String ImageUrl;
    private String ImageData;
    @BindView(R.id.beautiful_img)
    PhotoView giv;
    private PhotoViewAttacher mPhotoViewAttacher;
    private PicPresenter picPresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_picture;
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(provideContentViewId());
        picPresenter=new PicPresenterImpl(this);
        umBinder = ButterKnife.bind(this);
        ViewCompat.setTransitionName(giv, TRANSIT_PIC);
        ImageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        ImageData = getIntent().getStringExtra(EXTRA_IMAGE_TITLE);
        setTitle(DateFormatUtil.formatDate(ImageData));
        Glide.with(this)
                .load(ImageUrl)
                .error(R.drawable.error)
                .placeholder(R.drawable.loading)
                .into(giv);
        setAppBarAlpha(0.7f);
        setupPhotoAttacher();
    }

    private void setupPhotoAttacher() {
        mPhotoViewAttacher = new PhotoViewAttacher(giv);
        mPhotoViewAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                Log.i("Click", "Click");
                hideOrShowToolbar();
            }
        });
        mPhotoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(PictureActivity.this)
                        .setMessage("保存到手机？")
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }
                        )
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        picPresenter.savePic(PictureActivity.this,ImageUrl,ImageData);
                                        dialog.dismiss();
                                    }
                                })
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        umBinder.unbind();
        mPhotoViewAttacher.cleanup();
        picPresenter.unsubcrible();
    }

    @Override
    public void savePictureSuccess() {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Meizhi");
        String msg = String.format("图片已保存至 %s 文件夹",
                appDir.getAbsolutePath());
        Toasts.showShort(msg);
    }

    @Override
    public void FailToSave(Throwable e) {
        Toasts.showShort(e.getMessage() + "\n再试试...");
    }

    @Override
    public void sharePictureSuccess() {

    }

    @Override
    public void FailtoFail() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pic,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_save:
                picPresenter.savePic(PictureActivity.this,ImageUrl,ImageData);
                return true;
            case R.id.action_share:
                Toasts.showShort("分享功能构建中");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

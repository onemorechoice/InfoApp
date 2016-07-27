package com.news.ye.newsdemo.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by YU on 2016/7/13.
 */
public class PicModelImpl {

//    public static Observable<Uri> savePicToPhone(final Context context, final String url, final String title){
//        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
//            @Override
//            public void call(Subscriber<? super Bitmap> subscriber) {
//                Bitmap bitmap=null;
//                try{
//                    bitmap= Glide.with(context).load(url).asBitmap().centerCrop().into(500,500).get();
//                }catch (Throwable e){
//                    subscriber.onError(e);
//                }
//                 if (bitmap==null){
//                     subscriber.onError(new Throwable("图片下载失败"));
//                 }else{
//                     subscriber.onNext(bitmap);
//                     subscriber.onCompleted();
//                 }
//            }
//        }).flatMap(new Func1<Bitmap, Observable<Uri>>() {
//            @Override
//            public Observable<Uri> call(Bitmap bitmap) {
//                File file=new File(Environment.getExternalStorageDirectory(),"Pic");
//                if (!file.exists()){
//                    file.mkdirs();
//                }
//             String fileName=title.replace('/','-')+".jpg";
//                File pic=new File(file,fileName);
//                try{
//                    FileOutputStream outputStream=new FileOutputStream(pic);
//                    //assert 可以代替if
//                    assert bitmap!=null;
//                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
//                    outputStream.flush();
//                    outputStream.close();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                Uri uri=Uri.fromFile(pic);
//                // 通知图库更新
//                Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
//                context.sendBroadcast(scannerIntent);
//                return Observable.just(uri);
//            }
//        }).subscribeOn(Schedulers.io());
public static Observable<Uri> savePicToPhone(final Context context, final String url, final String title){
    return Observable.create(new Observable.OnSubscribe<Bitmap>() {
        @Override
        public void call(Subscriber<? super Bitmap> subscriber) {
            Bitmap bitmap=null;
            try{
                bitmap= Glide.with(context).load(url).asBitmap().centerCrop().into(500,500).get();
            }catch (Throwable e){
                subscriber.onError(e);
            }
            if (bitmap==null){
                subscriber.onError(new Throwable("图片下载失败"));
            }else{
                subscriber.onNext(bitmap);
                subscriber.onCompleted();
            }
        }
    }).flatMap(new Func1<Bitmap, Observable<Uri>>() {
        @Override
        public Observable<Uri> call(Bitmap bitmap) {
            File file=new File(Environment.getExternalStorageDirectory(),"Pic");
            if (!file.exists()){
                file.mkdirs();
            }
            String fileName=title.replace('/','-')+".jpg";
            File pic=new File(file,fileName);
            try{
                FileOutputStream outputStream=new FileOutputStream(pic);
                //assert 可以代替if
                assert bitmap!=null;
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                outputStream.flush();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            Uri uri=Uri.fromFile(pic);
            // 通知图库更新
            Intent scannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri);
            context.sendBroadcast(scannerIntent);
            return Observable.just(uri);
        }
    }).subscribeOn(Schedulers.io());




    }

}

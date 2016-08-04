package com.news.ye.newsdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.hanks.htextview.HTextView;
import com.news.ye.newsdemo.R;


public class WelcomeActivity extends AppCompatActivity {
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			changePage();
			handler.removeMessages(-1);
		}
	};
	private HTextView tv;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		// 隐藏状态栏
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
		tv=(HTextView)findViewById(R.id.AppName);
		tv.animateText("无名小资讯");
		img=(ImageView)findViewById(R.id.banner);
//		初始化缓存工具类
//		Jump2NextPage();
//		模拟banner
//		handler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//          img.setVisibility(View.VISIBLE);
//			}
//		},1000);
		handler.sendMessageDelayed(handler.obtainMessage(-1), 3000);
	}



	protected void changePage() {
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		//第一个参数为启动时动画效果，第二个参数为退出时动画效果
		overridePendingTransition(android.R.anim.fade_in,R.anim.alph);
		finish();
	}
	@Override
		public void onBackPressed() {
		super.onBackPressed();
		finish();
		}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP)
		{
			handler.sendMessage(handler.obtainMessage(-1));
			finish();
		}

		return super.onTouchEvent(event);
	}
}

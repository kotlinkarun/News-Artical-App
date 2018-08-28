package com.app.article.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.app.article.R;

public class PostActivity extends Activity {
	WebView web;
	ProgressDialog dialog;
	String url="";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_post);
		web = (WebView) findViewById(R.id.webview);
		url=getIntent().getStringExtra("url");
		if(url!=null) {

			web.setWebViewClient(new WebViewClient() {

				// This method will be triggered when the Page Started Loading

				public void onPageStarted(WebView view, String url, Bitmap favicon) {
					dialog = ProgressDialog.show(PostActivity.this, null, "Please Wait...");
					dialog.setCancelable(true);
					super.onPageStarted(view, url, favicon);
				}

				public void onPageFinished(WebView view, String url) {
					dialog.dismiss();
					super.onPageFinished(view, url);
				}
				public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
					dialog.dismiss();
					Toast.makeText(PostActivity.this, "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
					web.loadUrl(url);
					super.onReceivedError(view, errorCode, description, failingUrl);
				}
			});
			web.loadUrl(url);
			web.getSettings().setLoadWithOverviewMode(true);
			web.getSettings().setUseWideViewPort(true);
		}
	}
}
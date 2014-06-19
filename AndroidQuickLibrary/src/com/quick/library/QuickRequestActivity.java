package com.quick.library;

import android.app.ProgressDialog;

import com.android.http.LoadControler;
import com.android.http.RequestManager.RequestListener;

public class QuickRequestActivity extends QuickTitleActivity implements RequestListener {
	private LoadControler mLoadControler = null;
	private ProgressDialog mProgressDialog = null;

	public void get(String url, RequestListener requestListener, int actionId) {
		mLoadControler = QuickHelper.get(url, requestListener, actionId);
	}

	public void post(String url, String data, RequestListener requestListener, int actionId) {
		mLoadControler = QuickHelper.post(url, data, requestListener, actionId);
	}

	/**
	 * onRequest start
	 */
	@Override
	public void onRequest() {
		showDialog("loading...");
	}

	/**
	 * onError
	 */
	@Override
	public void onError(String errorMsg, String url, int arg2) {
		dismissDialog();
	}

	/**
	 * onSuccess
	 */
	@Override
	public void onSuccess(String result, String url, int arg2) {
		dismissDialog();
	}

	/**
	 * show progress dialog
	 * 
	 * @param message
	 *            message
	 * @param cancel
	 *            cancelable
	 */
	public void showDialog(String message) {
		if (isFinishing()) {
			return;
		}
		if (mProgressDialog == null) {
			mProgressDialog = ProgressDialog.show(this, "", message);
			mProgressDialog.setCanceledOnTouchOutside(false);
		} else {
			mProgressDialog.setMessage(message);
			mProgressDialog.show();
		}
	}

	public boolean isDialogShowing() {
		return (mProgressDialog != null && mProgressDialog.isShowing());
	}

	public void dismissDialog() {
		if (isFinishing()) {
			return;
		}
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public void onBackPressed() {
		if (mLoadControler != null) {
			mLoadControler.cancel();
		}
		super.onBackPressed();
	}

}

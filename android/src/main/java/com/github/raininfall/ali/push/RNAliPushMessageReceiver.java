package com.github.raininfall.ali.push;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by rongqiansong on 2017/9/22.
 */

public class RNAliPushMessageReceiver extends MessageReceiver {
		// 消息接收部分的LOG_TAG
		public static final String TAG = "RNAliPushReceiver";

		@Override
		public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
				Log.i(TAG, "Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
				try {
						/* Broadcast */
						final Intent intent = new Intent("com.github.raininfall.rnalipush.NOTIFIER");
						intent.putExtra("title", title);
						intent.putExtra("summary",summary);
						intent.putExtra("extra", new JSONObject(extraMap).toString());
						context.sendBroadcast(intent);
				} catch (Exception e) {
						Log.e(TAG, e.getMessage());
				}
		}

		@Override
		public void onMessage(Context context, CPushMessage cPushMessage) {
				Log.i(TAG, "onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
		}
		@Override
		public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
				Log.i(TAG, "onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
		}
		@Override
		protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
				Log.i(TAG, "onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
		}
		@Override
		protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
				Log.i(TAG, "onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);

		}
		@Override
		protected void onNotificationRemoved(Context context, String messageId) {
				Log.i(TAG, "onNotificationRemoved");
		}


}
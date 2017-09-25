
package com.github.raininfall.ali.push;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.telecom.Call;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class RNAliPushModule extends ReactContextBaseJavaModule implements LifecycleEventListener{
		private static final String TAG = "RNAliPushModule";
		public static final String RECEIVE_NOTIFIER_KEY = "RECEIVE_NOTIFIER";
		public static final String RECEIVE_NOTIFIER = "ReceiveNotifier";

		private final ReactApplicationContext reactContext;
		private final BroadcastReceiver receiver;

		public RNAliPushModule(ReactApplicationContext reactContext) {
				super(reactContext);

				this.reactContext = reactContext;
				this.receiver = new RNAliPushMessageBridgeReceiver(this.reactContext);

				reactContext.addLifecycleEventListener(this);
				registerBridgeReceiver();
		}

		@Override
		public String getName() {
				return "RNAliPush";
		}

		@Nullable
		@Override
		public Map<String, Object> getConstants() {
				final Map<String, Object> constants = new HashMap<>();
				constants.put(RECEIVE_NOTIFIER_KEY, RECEIVE_NOTIFIER);
				return constants;
		}

		/* Export methods */
		@ReactMethod
		public void bindAccount(final String accountName, final Callback cb) {
				PushServiceFactory.getCloudPushService().bindAccount(accountName, new CommonCallback() {
						@Override
						public void onSuccess(String s) {
								cb.invoke();
						}

						@Override
						public void onFailed(String code, String message) {
								cb.invoke(code, message);
						}
				});
		}

		@ReactMethod
		public void unbindAccount(final Callback cb) {
				PushServiceFactory.getCloudPushService().unbindAccount(new CommonCallback() {
						@Override
						public void onSuccess(String s) {
								cb.invoke();
						}

						@Override
						public void onFailed(String code, String message) {
								cb.invoke(code, message);
						}
				});
		}

		/* Native Part*/

		private void registerBridgeReceiver() {
				IntentFilter filter = new IntentFilter();
				filter.addAction("com.github.raininfall.rnalipush.NOTIFIER");
				reactContext.registerReceiver(this.receiver,filter);
		}

		@Override
		public void onHostResume() {

		}

		@Override
		public void onHostPause() {

		}

		@Override
		public void onHostDestroy() {

		}
}
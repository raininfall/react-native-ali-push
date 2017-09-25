package com.github.raininfall.ali.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;


/**
 * Created by rongqiansong on 2017/9/25.
 */

public class RNAliPushMessageBridgeReceiver extends BroadcastReceiver{
		private static final String TAG = "RNAliPushBridge";

		private final ReactContext reactContext;

		public RNAliPushMessageBridgeReceiver(final ReactContext reactContext) {
				this.reactContext = reactContext;
		}

		@Override
		public void onReceive(Context context, Intent intent) {
				try {
						final WritableMap data = Arguments.fromBundle(intent.getExtras());
						reactContext
										.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
										.emit(RNAliPushModule.RECEIVE_NOTIFIER, data);
				} catch (Exception e) {
						Log.e(TAG, e.getMessage());
				}
		}
}

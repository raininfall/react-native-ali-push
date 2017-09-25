import {
  NativeModules,
  DeviceEventEmitter
} from 'react-native';
import Symbol from 'es6-symbol';

const { RNAliPush } = NativeModules;
//export default RNAliPush;

const listeners = {};

RNAliPush.addNotifierListener = (cb) => {
  const handle = Symbol(cb);
  listeners[handle] = DeviceEventEmitter.addListener(
    RNAliPush.RECEIVE_NOTIFIER,
    (data) => {
      cb(data);
    }
  );

  return handle;
};

RNAliPush.removeNotifierListener = (handle) => {
  if (!listeners[handle]) {
    return;
  }
  listeners[handle].remove();
  delete listeners[handle];
};

export default RNAliPush;

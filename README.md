
# react-native-ali-push

## Getting started

`$ npm install react-native-ali-push --save`

### Mostly automatic installation

`$ react-native link react-native-ali-push`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.github.raininfall.ali.push.RNAliPushPackage;` to the imports at the top of the file
  - Add `new RNAliPushPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-ali-push'
  	project(':react-native-ali-push').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-ali-push/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-ali-push')
  	```


## Usage
```javascript
import RNAliPush from 'react-native-ali-push';

// TODO: What to do with the module?
RNAliPush;
```
  
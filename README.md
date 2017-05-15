# cordova-plugin-jdpay
京东支付cordova插件 ionic1 cordova phonegap
### 关于京东支付
接入京东支付的sdk，如果手机没有安装手机京东app，那么会直接进入wap收银台，输入手机号获取验证码进行支付。如果有安装手机京东app，那么就直接调起手机京东app进行支付。
***
### 支持平台
1. android
2. iOS
***
### 安装
1. 在线安装
    cordova plugin add cordova-plugin-jdpay --variable APP_ID=[your app_id] --variable MERCHANT=[your merchant_id]

    cordova plugin add https://github.com/hhjjj1010/cordova-plugin-jdpay.git --variable APP_ID=[your app_id]--variable MERCHANT=[your merchant_id]
2. 本地安装
如果网络不是很好，可以从git上下载插件到本地，然后通过本地安装的方式进行安装使用
    cordova plugin add /your/local/path --variable APP_ID=[your app_id]--variable MERCHANT=[your merchant_id]

3. 参数说明
app_id和merchant_id都是需要向京东申请，具体的申请流程请自行联系京东技术支付。
***
### 使用API

    var params = {
      merchant: data.merchant,
      appId:data.appId
      orderId: data.orderId,
      signData: data.signData
    };
    /**
    * 为保证安全，orderId以及signData最好是放到服务器端去生成
    * orderId申请请参照服务端接入京东支付的文档
    * signData生成规则请参照服务器端接入APP SDK的文档
    * 相关开发文档可在申请商户号和appId时询问京东技术支持
    * */
    cordova.plugins.JDPay.payment(params, function success(resultDict) {
      alert("支付成功");

    }, function error(resultDict) {

    var statusText = "无操作";
    if (resultDict.payStatus === "JDP_PAY_CANCEL") {
      statusText = "用户取消";
    } else if (resultDict.payStatus === "JDP_PAY_FAIL") {
      statusText = "支付失败";
    }
      alert(statusText);
    });

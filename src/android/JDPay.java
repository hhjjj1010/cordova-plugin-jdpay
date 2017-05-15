package cn.hhjjj.jdpay;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import android.widget.Toast;

import com.jdpaysdk.author.Constants;
import com.jdpaysdk.author.JDPayAuthor;

/**
 * This class echoes a string called from JavaScript.
 */
public class JDPay extends CordovaPlugin {

    private CallbackContext callback;
    private String app_id, merchant_id;

    @Override
    protected void pluginInitialize() {

        super.pluginInitialize();
        app_id = preferences.getString("jdpappid", "");
        merchant_id = preferences.getString("jdpmerchant", "");
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callback = callbackContext;

        if (action.equals("payment")) {
            this.payment(args, callbackContext);
            return true;
        }
        return false;
    }

    private void payment(JSONArray args, CallbackContext callbackContext) {
        if (args != null && args.length() > 0) {

            final JSONObject params;
            try {
                params = args.getJSONObject(0);
            } catch (JSONException e) {
                Toast.makeText(cordova.getActivity(), "传入参数错误", Toast.LENGTH_LONG).show();
                return;
            }

            String orderId, merchant, appId, signData;

            try {
                merchant = params.has("merchant") ? params.getString("merchant") : merchant_id;
                appId = params.has("appId") ? params.getString("appId") : app_id;
                orderId = params.getString("orderId");
                signData = params.getString("signData");
            } catch (Exception e) {
                Toast.makeText(cordova.getActivity(), "传入参数错误", Toast.LENGTH_LONG).show();
                return;
            }

            JDPayAuthor jdPayAuthor = new JDPayAuthor();
            jdPayAuthor.author(cordova.getActivity(), orderId, merchant, appId, signData);

            cordova.setActivityResultCallback(this);

        } else {
            Toast.makeText(cordova.getActivity(), "传入参数错误", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (Constants.PAY_RESPONSE_CODE == resultCode) {
            //返回信息接收
            String result = intent.getStringExtra(JDPayAuthor.JDPAY_RESULT);

            JSONObject resultDict;
            try {
                resultDict = new JSONObject(result);
                String status = resultDict.getString("payStatus");

                if (status.equals("JDP_PAY_SUCCESS")) {
                    callback.success(resultDict);
                } else {
                    callback.error(resultDict);
                }
            } catch (JSONException e) {
                Toast.makeText(cordova.getActivity(), "JSON异常", Toast.LENGTH_LONG).show();
            }
        }
    }
}

package custom.connectivity;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class Connectivity extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (this.cordova.getActivity().isFinishing()) {
			return true;
		}

		if (action.equals("getNetworkInfo")) {
			this.getNetworkInfo(args.getJSONObject(0), callbackContext);
			return true;
		} else {
			return false;
		}
	}

	// getNetworkInfo
	public void getNetworkInfo(final JSONObject options, final CallbackContext callbackContext) {
		final CordovaInterface cordova = this.cordova;

		JSONObject result = new JSONObject();

		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) cordova.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			result.put("mobile", connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState());
			result.put("wifi", connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState());
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, result));
		} catch (Exception ex) {
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, ex.getMessage()));
		}
	}
}

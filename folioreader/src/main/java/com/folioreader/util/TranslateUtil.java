package com.folioreader.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.folioreader.model.TextSelectionImpl;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by priyank on 5/12/16.
 */
public class TranslateUtil extends TextSelectionUtil {

    private static final String TAG = "TranslateUtil";

    public static String getText(String json) {
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(json);
            return jObject.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void sendTranslateBroadcastEvent(Context context, String text) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(getTranslateBroadcastIntent(text));
    }

    public static Intent getTranslateBroadcastIntent(String text) {
        Bundle bundle = new Bundle();
        bundle.putString(TextSelectionImpl.INTENT, text);
        return new Intent(TextSelectionImpl.BROADCAST_EVENT_TRANSLATE).putExtras(bundle);
    }
}

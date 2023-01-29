package com.folioreader.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.folioreader.model.TextSelectionImpl;


/**
 * Created by priyank on 5/12/16.
 */
public class AddUtil extends TextSelectionUtil {

    private static final String TAG = "AddUtil";


    public static void sendAddBroadcastEvent(Context context, TextSelectionImpl textSelection) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(getAddBroadcastIntent(textSelection));
    }

    public static Intent getAddBroadcastIntent(TextSelectionImpl textSelectionImpl) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(TextSelectionImpl.INTENT, textSelectionImpl);
        return new Intent(TextSelectionImpl.BROADCAST_EVENT_ADD).putExtras(bundle);
    }
}

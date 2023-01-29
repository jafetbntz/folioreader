package com.folioreader.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.folioreader.model.HighLight;
import com.folioreader.model.HighlightImpl;
import com.folioreader.model.sqlite.HighLightTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by priyank on 5/12/16.
 */
public class HighlightUtil extends TextSelectionUtil {

    private static final String TAG = "HighlightUtil";

    public static HighlightImpl createHighlightRangy(Context context,
                                              String content,
                                              String bookId,
                                              String pageId,
                                              int pageNo,
                                              String oldRangy) {
        try {
            JSONObject jObject = new JSONObject(content);

            String rangy = jObject.getString("rangy");
            String textContent = jObject.getString("content");
            String color = jObject.getString("color");

            String rangyHighlightElement = getRangyString(rangy, oldRangy);

            HighlightImpl highlightImpl = new HighlightImpl();
            highlightImpl.setContent(textContent);
            highlightImpl.setType(color);
            highlightImpl.setPageNumber(pageNo);
            highlightImpl.setBookId(bookId);
            highlightImpl.setPageId(pageId);
            highlightImpl.setRangy(rangyHighlightElement);
            highlightImpl.setDate(Calendar.getInstance().getTime());

            // Save highlight to database
            long id = HighLightTable.insertHighlight(highlightImpl);
            if (id != -1) {
                highlightImpl.setId((int) id);
            }

            return highlightImpl;

        } catch (JSONException e) {
            Log.e(TAG, "createHighlightRangy failed", e);
        }

        return null;
    }

    public static void sendHighlightBroadcastEvent(Context context,
                                                   HighlightImpl highlightImpl,
                                                   HighLight.HighLightAction action) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(
                getHighlightBroadcastIntent(highlightImpl, action));
    }

    public static Intent getHighlightBroadcastIntent(HighlightImpl highlightImpl,
                                                     HighLight.HighLightAction modify) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(HighlightImpl.INTENT, highlightImpl);
        bundle.putSerializable(HighLight.HighLightAction.class.getName(), modify);
        return new Intent(HighlightImpl.BROADCAST_EVENT).putExtras(bundle);
    }
}

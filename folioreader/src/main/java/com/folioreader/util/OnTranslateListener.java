package com.folioreader.util;

import com.folioreader.model.TextSelection;
import com.folioreader.model.TextSelectionImpl;

/**
 * Interface to convey translate events.
 *
 * @author gautam chibde on 26/9/17.
 */

public interface OnTranslateListener {

    /**
     * This method will be invoked when a textSelection is translated
     *
     * @param text that was selected {@link TextSelectionImpl}.
     */
    void onTranslate(String text);
}

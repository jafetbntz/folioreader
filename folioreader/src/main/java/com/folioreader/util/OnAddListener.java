package com.folioreader.util;

import com.folioreader.model.TextSelection;
import com.folioreader.model.TextSelectionImpl;

/**
 * Interface to convey add to vocabulary events.
 *
 * @author gautam chibde on 26/9/17.
 */

public interface OnAddListener {

    /**
     * This method will be invoked when a textSelection is added to the vocabulary
     *
     * @param textSelection meta-data for created highlight {@link TextSelectionImpl}.
     */
    void onAdd(TextSelection textSelection);
}

package com.folioreader.model;


/**
 * Interface to access Highlight data.
 *
 * @author gautam chibde on 9/10/17.
 */

public interface HighLight extends TextSelection {

    /**
     * Highlight action
     */
    enum HighLightAction {
        NEW, DELETE, MODIFY
    }

    /**
     * Returns Field defines the color of the highlight.
     */
    String getType();

}

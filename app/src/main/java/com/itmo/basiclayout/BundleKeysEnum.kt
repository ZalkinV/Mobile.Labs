package com.itmo.basiclayout

enum class BundleKeysEnum {
    ;
    enum class Task1 {
        LIST_ELEMENTS,
        LIST_VISIBILITY,
        TEXTVIEW_TEXT,
        EDITTEXT_TEXT,
        SWITCHER_STATE,
        ;
        enum class Details {
            NUM_NAT,
            NUM_FIB_PREV,
            NUM_FIB,
            NUM_COL,
            ;
        }
    }
}
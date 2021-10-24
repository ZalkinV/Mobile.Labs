package com.itmo.basiclayout.keysEnums

enum class BundleKeysEnum {
    ;
    enum class Task1 {
        LIST_ELEMENTS,
        LIST_VISIBILITY,
        TEXTVIEW_TEXT,
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
package com.example.hugo.coursintent;

import android.provider.BaseColumns;

/**
 * Created by Hugo on 07/11/2017.
 */

public class DbContract {
    private DbContract(){

    }

    public static final class MessageEntries implements BaseColumns {
        public static final String TABLE_NAME = "messages";
        public static final String USERNAME = "username";
        public static final String MESSAGE = "texte";
    }
}

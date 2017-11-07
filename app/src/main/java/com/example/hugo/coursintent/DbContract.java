package com.example.hugo.coursintent;

import android.provider.BaseColumns;

/**
 * Created by Hugo on 07/11/2017.
 */

public class DbContract {
    private DbContract(){

    }

    public static final class StudentEntries implements BaseColumns {
        public static final String TABLE_NAME = "student";
        public static final String FIRST_NAME = "firstname";
        public static final String LAST_NAME = "lastname";
    }
}

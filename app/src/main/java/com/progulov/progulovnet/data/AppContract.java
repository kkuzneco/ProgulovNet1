package com.progulov.progulovnet.data;

import android.provider.BaseColumns;

public final class AppContract {
    private AppContract(){};

    public static final class AllSubjects implements BaseColumns{
        public final static String TABLE_NAME = "subjects";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME="name";
        public final static String COLUMN_DEPARTMENT="department";
    }
    public static final class AllLecturers implements BaseColumns{
        public final static String TABLE_NAME = "lecturers";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME="name";
        public final static String COLUMN_DEPARTMENT="department";
    }
    public static final class AllStudents implements BaseColumns{
        public final static String TABLE_NAME = "students";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME="name";

    }
}

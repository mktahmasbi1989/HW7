package com.example.mohamdkazem.advancetodolist.dataBase;

public class TaskDbSchema {
    public static final String NAME = "task.db";
    public static final int VERSION =3;

    public static final class TasksTable {
        public static final String NAME = "tasks";

        public static final class tasksCols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DETAIL = "detail";
            public static final String DATE = "date";
            public static final String DONE = "done";
            public static final String TIME = "time";

        }
    }

        public static final class UsersTable {
            public static final String NAME = "users";

            public static final class usersCols {
                public static final String USERNAME = "userName";
                public static final String PASSWORD = "password";
                public static final String EMAIL="email";
            }

        }
    }




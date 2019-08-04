package com.gmail.webtest.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmMemoData extends RealmObject {
//    @PrimaryKey
    public String title;
    public String content;
    public String dateString;
}

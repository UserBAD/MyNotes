package com.example.mynotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notes implements Parcelable {

    private String name;
    private String text;
    private String data;

    public Notes(String name, String text, String data) {
        this.name = name;
        this.text = text;
        this.data = data;
    }

    protected Notes(Parcel in) {
        name = in.readString();
        text = in.readString();
        data = in.readString();
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public String getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(text);
        parcel.writeString(data);
    }
}

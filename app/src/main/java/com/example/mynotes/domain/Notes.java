package com.example.mynotes.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Objects;

public class Notes implements Parcelable {

    private final String id;
    private String name;
    private final String imageUrl;
    private final Date createdAt;

    public Notes(String id, String name, String imageUrl, Date createdAt) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }

    protected Notes(Parcel in) {
        id = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        createdAt = (Date) in.readSerializable();
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return Objects.equals(id, notes.id) && Objects.equals(name, notes.name) && Objects.equals(imageUrl, notes.imageUrl) && Objects.equals(createdAt, notes.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageUrl, createdAt);
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeSerializable(createdAt);
    }


    public void remove(Notes notes) {
    }
}

package com.tistory.thepassion.pacelable;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    int serial;
    String isbn;
    String title;
    Bitmap image;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(serial);
        dest.writeString(isbn);
        dest.writeString(title);
        dest.writeParcelable(image, flags);
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public Book(Parcel src) {
        readFromParcel(src);
    }

    public void readFromParcel(Parcel src) {
        serial = src.readInt();
        isbn = src.readString();
        title = src.readString();
        image = src.readParcelable(Bitmap.class.getClassLoader());
    }

    public Book(int serial, String isbn, String title, Bitmap image) {
        this.serial = serial;
        this.title = title;
        this.isbn = isbn;
        this.image = image;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

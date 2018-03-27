package com.example.has.asapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by has on 2018/3/27.
 */
public class User implements Parcelable {
    public  int userId;
    public  String userName;
    public  boolean isMale;
    public Book book;
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(userId);
        out.writeString(userName);
        out.writeInt(isMale?1:0);
        out.writeParcelable(book,0);
    }

    private static Parcelable.Creator<User> CREATOR=new Parcelable.Creator<User>(){
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in){
        userId=in.readInt();
        userName=in.readString();
        isMale=in.readInt()==1;
        book=in.readParcelable(Thread.currentThread().getContextClassLoader());

    }
}

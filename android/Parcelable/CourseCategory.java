package com.dj.zfwx.client.bean;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Parcel;
import android.os.Parcelable;

public class CourseCategory implements Parcelable
{
	public String id;
	public String name;
	public String count;
	public String dd_count;
	public String adUrl;

	public ArrayList<CourseCategory> sub = new ArrayList<CourseCategory>();
	 
	public CourseCategory(JSONObject json)
	{
		if( json == null )
    		return;
		
		id   = json.optString("id");
		name = json.optString("name");
		count = json.optString("count","");
		dd_count = json.optString("dd_count","");
		adUrl=json.optString("cgIcon","");
		parseJsonArray(json);
	}
	
	public CourseCategory(String id,String name,String count,String dd_count)
	{
		this.id = id;
		this.name = name;
		this.count = count;
		this.dd_count = dd_count;
	}

	public CourseCategory(String id,String name,String count)
	{
		this.id = id;
		this.name = name;
		this.count = count;
	}
	 
	public CourseCategory()
	{
		super();
	}
	
	public static final Parcelable.Creator<CourseCategory> CREATOR = new Parcelable.Creator<CourseCategory>() 
	{
		@SuppressWarnings("unchecked")
		@Override
		public CourseCategory createFromParcel(Parcel source) 
		{
			CourseCategory lecture = new CourseCategory();
			lecture.id      = source.readString();
			lecture.name    = source.readString();
			lecture.count   = source.readString();
			lecture.dd_count   = source.readString();

			lecture.sub     = source.readArrayList(ArrayList.class.getClassLoader());
			return lecture;
		}

		@Override
		public CourseCategory[] newArray(int size) 
		{
			return new CourseCategory[size];
		}
	};

	@Override
	public int describeContents() 
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) 
	{
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(count);
		dest.writeString(dd_count);
		dest.writeParcelable((Parcelable)sub,0);
	}
	
	void parseJsonArray(JSONObject json)
	{
		JSONArray array = json.optJSONArray("sub");
		if(array != null && array.length() > 0)
		{
			for (int i = 0; i < array.length(); i++) 
			{
				try 
				{
					CourseCategory item = new CourseCategory(array.optJSONObject(i));
					sub.add(item);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}

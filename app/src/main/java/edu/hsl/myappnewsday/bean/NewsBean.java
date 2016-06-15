package edu.hsl.myappnewsday.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class NewsBean implements Parcelable {
    String     message;
    int        status;
    List<Data> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);

    }

    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel source) {
            NewsBean bean = new NewsBean();
            bean.setData(source.createTypedArrayList(Data.CREATOR));
            return bean;
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[0];
        }
    };

    public static class Data implements Parcelable {

        String summary;
        String icon;
        String stamp;
        String title;
        String nid;
        String link;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(summary);
            dest.writeString(icon);
            dest.writeString(stamp);
            dest.writeString(title);
            dest.writeString(nid);
            dest.writeString(link);

        }

        public static Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel source) {
                Data data = new Data();
                data.summary = source.readString();
                data.icon = source.readString();
                data.stamp = source.readString();
                data.title = source.readString();
                data.nid = source.readString();
                data.link = source.readString();
                return data;
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}

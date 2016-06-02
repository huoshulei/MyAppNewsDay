package edu.hsl.myappnewsday.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
public class NewsBean {
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

    public class Data {
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
    }
}

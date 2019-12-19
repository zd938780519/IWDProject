package com.example.project.iwdproject.Beans;

import java.util.List;

public class FirstHomePageBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"banner":[{"id":1,"img":"https://img.zcool.cn/community/016b9055f8e59d6ac7251df8534b14.jpg","url":"","status":1,"created_at":"2019-11-19 15:44:09"},{"id":2,"img":"https://img.zcool.cn/community/015a6b5b7a75dca80120d8c06ba908.jpg","url":"","status":1,"created_at":"2019-11-19 15:44:39"}],"notice":[{"id":2,"content":"又一条公告","push_time":0,"status":1,"created_at":"2019-12-12 22:28:28"},{"id":1,"content":"一条公告","push_time":0,"status":1,"created_at":"2019-12-11 17:04:54"}]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<NoticeBean> notice;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<NoticeBean> getNotice() {
            return notice;
        }

        public void setNotice(List<NoticeBean> notice) {
            this.notice = notice;
        }

        public static class BannerBean {
            /**
             * id : 1
             * img : https://img.zcool.cn/community/016b9055f8e59d6ac7251df8534b14.jpg
             * url :
             * status : 1
             * created_at : 2019-11-19 15:44:09
             */

            private int id;
            private String img;
            private String url;
            private int status;
            private String created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }

        public static class NoticeBean {
            /**
             * id : 2
             * content : 又一条公告
             * push_time : 0
             * status : 1
             * created_at : 2019-12-12 22:28:28
             */

            private int id;
            private String content;
            private int push_time;
            private int status;
            private String created_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getPush_time() {
                return push_time;
            }

            public void setPush_time(int push_time) {
                this.push_time = push_time;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }
        }
    }
}

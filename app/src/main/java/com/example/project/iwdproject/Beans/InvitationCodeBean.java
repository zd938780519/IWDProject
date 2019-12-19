package com.example.project.iwdproject.Beans;

public class InvitationCodeBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"phone":"185****1785","num":"6eb807352","img_url":"http://47.244.218.212/down_and.png","url":"http://47.244.218.212/IWD.apk"}
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
        /**
         * phone : 185****1785
         * num : 6eb807352
         * img_url : http://47.244.218.212/down_and.png
         * url : http://47.244.218.212/IWD.apk
         */

        private String phone;
        private String num;
        private String img_url;
        private String url;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

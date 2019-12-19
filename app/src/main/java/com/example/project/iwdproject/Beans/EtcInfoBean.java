package com.example.project.iwdproject.Beans;

public class EtcInfoBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"id":22,"user_id":28,"address":"0x44a4cbc0216f8eab79d11cbe7dca86d71546408f","qrcode":"http://47.244.218.212/eth/0x44a4cbc0216f8eab79d11cbe7dca86d71546408f.png","flag":0,"created_at":"2019-12-13 11:22:32"}
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
         * id : 22
         * user_id : 28
         * address : 0x44a4cbc0216f8eab79d11cbe7dca86d71546408f
         * qrcode : http://47.244.218.212/eth/0x44a4cbc0216f8eab79d11cbe7dca86d71546408f.png
         * flag : 0
         * created_at : 2019-12-13 11:22:32
         */

        private int id;
        private int user_id;
        private String address;
        private String qrcode;
        private int flag;
        private String created_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }

        public int getFlag() {
            return flag;
        }

        public void setFlag(int flag) {
            this.flag = flag;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}

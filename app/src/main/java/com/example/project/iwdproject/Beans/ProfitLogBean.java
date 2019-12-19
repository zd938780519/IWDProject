package com.example.project.iwdproject.Beans;

import java.util.List;

public class ProfitLogBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : [{"id":35,"user_id":28,"token":1,"amount":"2.0200","io":1,"source_id":2,"status":1,"created_at":"2019-12-17 22:29:43"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 35
         * user_id : 28
         * token : 1
         * amount : 2.0200
         * io : 1
         * source_id : 2
         * status : 1
         * created_at : 2019-12-17 22:29:43
         */

        private int id;
        private int user_id;
        private int token;
        private String amount;
        private int io;
        private int source_id;
        private int status;
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

        public int getToken() {
            return token;
        }

        public void setToken(int token) {
            this.token = token;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getIo() {
            return io;
        }

        public void setIo(int io) {
            this.io = io;
        }

        public int getSource_id() {
            return source_id;
        }

        public void setSource_id(int source_id) {
            this.source_id = source_id;
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

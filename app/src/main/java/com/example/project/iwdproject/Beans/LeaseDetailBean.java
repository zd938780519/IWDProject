package com.example.project.iwdproject.Beans;

public class LeaseDetailBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"total":"1.0000","fee":"5%","created_at":"2019-12-24 13:05:53","days":0,"amount":"1.0000","status":1,"id":687}
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
         * total : 1.0000
         * fee : 5%
         * created_at : 2019-12-24 13:05:53
         * days : 0
         * amount : 1.0000
         * status : 1
         * id : 687
         */

        private String total;
        private String fee;
        private String created_at;
        private int days;
        private String amount;
        private int status;
        private int id;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}

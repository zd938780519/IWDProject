package com.example.project.iwdproject.Beans;

public class FeesBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"charge":"0","true_num":"0"}
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
         * charge : 0
         * true_num : 0
         */

        private String charge;
        private String true_num;

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getTrue_num() {
            return true_num;
        }

        public void setTrue_num(String true_num) {
            this.true_num = true_num;
        }
    }
}

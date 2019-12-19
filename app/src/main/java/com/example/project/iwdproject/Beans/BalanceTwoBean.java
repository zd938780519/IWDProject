package com.example.project.iwdproject.Beans;

public class BalanceTwoBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"name":"IWD","total":"0.0000","seven":"0.0000","thirty":"0.0000"}
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
         * name : IWD
         * total : 0.0000
         * seven : 0.0000
         * thirty : 0.0000
         */

        private String name;
        private String total;
        private String seven;
        private String thirty;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getSeven() {
            return seven;
        }

        public void setSeven(String seven) {
            this.seven = seven;
        }

        public String getThirty() {
            return thirty;
        }

        public void setThirty(String thirty) {
            this.thirty = thirty;
        }
    }
}

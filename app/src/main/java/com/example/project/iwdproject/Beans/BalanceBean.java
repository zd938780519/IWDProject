package com.example.project.iwdproject.Beans;

public class BalanceBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"IWD":{"total":"0","num":"0","withdrawal":"0","total_price":"0"}}
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
         * IWD : {"total":"0","num":"0","withdrawal":"0","total_price":"0"}
         */

        private IWDBean IWD;

        public IWDBean getIWD() {
            return IWD;
        }

        public void setIWD(IWDBean IWD) {
            this.IWD = IWD;
        }

        public static class IWDBean {
            /**
             * total : 0
             * num : 0
             * withdrawal : 0
             * total_price : 0
             */

            private String total;
            private String num;
            private String withdrawal;
            private String total_price;

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getWithdrawal() {
                return withdrawal;
            }

            public void setWithdrawal(String withdrawal) {
                this.withdrawal = withdrawal;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }
        }
    }
}

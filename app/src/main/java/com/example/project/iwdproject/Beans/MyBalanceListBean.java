package com.example.project.iwdproject.Beans;

import java.util.List;

public class MyBalanceListBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"total_num":109.95989499999999,"list":[{"id":1,"name":"IWD","num":"199.7999","price":"0.05","change":"0","total":"9.989995"},{"id":2,"name":"USDT","num":"99.9699","price":"1.00","change":"0","total":"99.9699"}]}
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
         * total_num : 109.95989499999999
         * list : [{"id":1,"name":"IWD","num":"199.7999","price":"0.05","change":"0","total":"9.989995"},{"id":2,"name":"USDT","num":"99.9699","price":"1.00","change":"0","total":"99.9699"}]
         */

        private double total_num;
        private List<ListBean> list;

        public double getTotal_num() {
            return total_num;
        }

        public void setTotal_num(double total_num) {
            this.total_num = total_num;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * name : IWD
             * num : 199.7999
             * price : 0.05
             * change : 0
             * total : 9.989995
             */

            private int id;
            private String name;
            private String num;
            private String price;
            private String change;
            private String total;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getChange() {
                return change;
            }

            public void setChange(String change) {
                this.change = change;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }
    }
}

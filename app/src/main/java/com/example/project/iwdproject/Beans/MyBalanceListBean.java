package com.example.project.iwdproject.Beans;

import java.util.List;

public class MyBalanceListBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"total_num":"0.00","list":[{"id":1,"name":"IWD","num":"0.00","price":"1.02","change":"2","total":"0.00","flag":2,"time":""},{"id":2,"name":"USDT","num":"0.00","price":"1.00","change":"0","total":"0.00","flag":2,"time":""},{"id":3,"name":"IWD锁仓","num":"0.00","price":"1.02","change":"2","total":"0.00","flag":1,"time":"2020-02-01 01:00:00"},{"id":4,"name":"USDT锁仓","num":"0.00","price":"1.00","change":"0","total":"0.00","flag":1,"time":"2020-02-01 01:00:00"}]}
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
         * total_num : 0.00
         * list : [{"id":1,"name":"IWD","num":"0.00","price":"1.02","change":"2","total":"0.00","flag":2,"time":""},{"id":2,"name":"USDT","num":"0.00","price":"1.00","change":"0","total":"0.00","flag":2,"time":""},{"id":3,"name":"IWD锁仓","num":"0.00","price":"1.02","change":"2","total":"0.00","flag":1,"time":"2020-02-01 01:00:00"},{"id":4,"name":"USDT锁仓","num":"0.00","price":"1.00","change":"0","total":"0.00","flag":1,"time":"2020-02-01 01:00:00"}]
         */

        private String total_num;
        private List<ListBean> list;

        public String getTotal_num() {
            return total_num;
        }

        public void setTotal_num(String total_num) {
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
             * num : 0.00
             * price : 1.02
             * change : 2
             * total : 0.00
             * flag : 2
             * time :
             */

            private int id;
            private String name;
            private String num;
            private String price;
            private String change;
            private String total;
            private int flag;
            private String time;

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

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }
    }
}

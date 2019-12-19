package com.example.project.iwdproject.Beans;

import java.util.List;

public class MarketBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : [{"id":1,"name":"IWD/USDT","cny":"7.03","usd":"0.00","change":"0"},{"id":2,"name":"USDT","cny":"7.00","usd":"1.00","change":"0"},{"id":3,"name":"BTC/USDT","cny":"50848.51","usd":"7264.07","change":"-1.06"},{"id":4,"name":"ETH/USDT","cny":"1026.43","usd":"146.63","change":"-0.79"},{"id":5,"name":"XRP/USDT","cny":"1.56","usd":"0.22","change":"0.23"},{"id":6,"name":"BCH/USDT","cny":"1453.55","usd":"207.65","change":"-0.45"},{"id":7,"name":"TRX/USDT","cny":"0.10","usd":"0.01","change":"-0.43"},{"id":8,"name":"ETC/USDT","cny":"26.58","usd":"3.80","change":"1.01"}]
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
         * id : 1
         * name : IWD/USDT
         * cny : 7.03
         * usd : 0.00
         * change : 0
         */

        private int id;
        private String name;
        private String cny;
        private String usd;
        private String change;

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

        public String getCny() {
            return cny;
        }

        public void setCny(String cny) {
            this.cny = cny;
        }

        public String getUsd() {
            return usd;
        }

        public void setUsd(String usd) {
            this.usd = usd;
        }

        public String getChange() {
            return change;
        }

        public void setChange(String change) {
            this.change = change;
        }
    }
}

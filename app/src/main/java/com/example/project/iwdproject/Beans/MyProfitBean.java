package com.example.project.iwdproject.Beans;

public class MyProfitBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"total":"2.0200","total_usdt":"0.1","seven":"2.0200","thirty":"2.0200","direct":"0","team":"0","team_profit":0}
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
         * total : 2.0200
         * total_usdt : 0.1
         * seven : 2.0200
         * thirty : 2.0200
         * direct : 0
         * team : 0
         * team_profit : 0
         */

        private String total;
        private String total_usdt;
        private String seven;
        private String thirty;
        private String direct;
        private String team;
        private int team_profit;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getTotal_usdt() {
            return total_usdt;
        }

        public void setTotal_usdt(String total_usdt) {
            this.total_usdt = total_usdt;
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

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getTeam() {
            return team;
        }

        public void setTeam(String team) {
            this.team = team;
        }

        public int getTeam_profit() {
            return team_profit;
        }

        public void setTeam_profit(int team_profit) {
            this.team_profit = team_profit;
        }
    }
}

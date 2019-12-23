package com.example.project.iwdproject.Beans;

public class MyProfitBean {

    /**
     * code : 10086
     * message : 获取成功
     * data : {"total":"2901.5001","total_usdt":"2959.53","seven":"2918.3335","thirty":"2918.3335","direct":"5","team":"18","team_profit":"2566.7418"}
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
         * total : 2901.5001
         * total_usdt : 2959.53
         * seven : 2918.3335
         * thirty : 2918.3335
         * direct : 5
         * team : 18
         * team_profit : 2566.7418
         */

        private String total;
        private String total_usdt;
        private String seven;
        private String thirty;
        private String direct;
        private String team;
        private String team_profit;

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

        public String getTeam_profit() {
            return team_profit;
        }

        public void setTeam_profit(String team_profit) {
            this.team_profit = team_profit;
        }
    }
}

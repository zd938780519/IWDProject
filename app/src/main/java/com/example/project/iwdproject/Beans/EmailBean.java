package com.example.project.iwdproject.Beans;

public class EmailBean {

    /**
     * code : 10086
     * message : 登录成功
     * data : {"access_token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC80Ny4yNDQuMjE4LjIxMlwvYXBpXC9sb2dpbl9lbWFpbCIsImlhdCI6MTU3NjUwMzEzMywiZXhwIjoxNTc3MTA3OTMzLCJuYmYiOjE1NzY1MDMxMzMsImp0aSI6IkVkV0VGNTAxTjlGZnpwSlUiLCJzdWIiOjU3LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwiZW1haWwiOiI4Mzg3ODA1MTlAcXEuY29tIiwidGltZSI6MTU3NjUwMzEzM30.tSGNOgpmlJna-K1PYDTt9Hllo0BwiMsxoUkRdCQXTHk","token_type":"bearer","expires_in":"10080","user_id":57,"email":"838780519@qq.com","portrait":"http://tianxiadatong-1256037739.cos.ap-shanghai.myqcloud.com/tianxiadatong/156472680779.jpg","certification":1,"level":0}
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
         * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC80Ny4yNDQuMjE4LjIxMlwvYXBpXC9sb2dpbl9lbWFpbCIsImlhdCI6MTU3NjUwMzEzMywiZXhwIjoxNTc3MTA3OTMzLCJuYmYiOjE1NzY1MDMxMzMsImp0aSI6IkVkV0VGNTAxTjlGZnpwSlUiLCJzdWIiOjU3LCJwcnYiOiIyM2JkNWM4OTQ5ZjYwMGFkYjM5ZTcwMWM0MDA4NzJkYjdhNTk3NmY3IiwiZW1haWwiOiI4Mzg3ODA1MTlAcXEuY29tIiwidGltZSI6MTU3NjUwMzEzM30.tSGNOgpmlJna-K1PYDTt9Hllo0BwiMsxoUkRdCQXTHk
         * token_type : bearer
         * expires_in : 10080
         * user_id : 57
         * email : 838780519@qq.com
         * portrait : http://tianxiadatong-1256037739.cos.ap-shanghai.myqcloud.com/tianxiadatong/156472680779.jpg
         * certification : 1
         * level : 0
         */

        private String access_token;
        private String token_type;
        private String expires_in;
        private int user_id;
        private String email;
        private String portrait;
        private int certification;
        private int level;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getCertification() {
            return certification;
        }

        public void setCertification(int certification) {
            this.certification = certification;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}

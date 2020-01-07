package com.smart.ludoclassic.modals;

public class UserModal {

    /**
     * status : 1
     * message : Logged in successfully
     * userDetails : {"username":"dadi","password":"c69f6c2592d81e5d24f34b30b78a2bf2","mobile":"8885270193","email":"dadi@gmail.com","userId":"27","otp":719297}
     */

    private String status;
    private String message;
    private UserDetailsBean userDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetailsBean getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsBean userDetails) {
        this.userDetails = userDetails;
    }

    public static class UserDetailsBean {
        /**
         * username : dadi
         * password : c69f6c2592d81e5d24f34b30b78a2bf2
         * mobile : 8885270193
         * email : dadi@gmail.com
         * userId : 27
         * otp : 719297
         */

        private String username;
        private String password;
        private String mobile;
        private String email;
        private String userId;
        private int otp;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getOtp() {
            return otp;
        }

        public void setOtp(int otp) {
            this.otp = otp;
        }
    }
}

package com.example.androidlabs;


    public class Message {


        private long id;
        private String message;
        private boolean checker;






        public Message(long id ,String message, boolean checker)
        {
            this.id=id;
            this.message=message;
            this.checker=checker;
        }

        public Message(String message, boolean checker) {
            this.message=message;
            this.checker=checker;
        }
        public Message() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public boolean isChecker() {
            return checker;
        }

        public void setChecker(boolean checker) {
            this.checker = checker;
        }




    }

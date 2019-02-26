package com.example.androidlabs;


    public class Message {


    String message;
    private boolean checker;





    public Message(String message,boolean checker){

this.message=message;
this.checker=checker;
        }

        public Message(){

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

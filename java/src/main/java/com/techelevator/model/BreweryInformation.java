package com.techelevator.model;

public class BreweryInformation {

//Brewery variables to consider combining

//        private int id;
//        private String name;
//        private String breweryType;
//        private String street;
//        private String city;
//        private String state;
//        private String postalCode;
//        private String websiteUrl;
//        private String phone;
//        private long longitude;
//        private long latitude;


        private String obdb_id;
        private String phone;  //contact information?

        private String brewHistory;
        private String hoursOpen;
        private String brewImage;

        //can also be - private String daysOpen;
        private boolean openMon;
        private boolean openTue;
        private boolean openWed;
        private boolean openThu;
        private boolean openFri;
        private boolean openSat;
        private boolean openSun;




        public BreweryInformation() {
        }

        public BreweryInformation(String obdb_id, String phone, String brewHistory, String hoursOpen, String brewImage, boolean openMon, boolean openTue, boolean openWed, boolean openThu, boolean openFri, boolean openSat, boolean openSun) {
                this.obdb_id = obdb_id;
                this.phone = phone;
                this.brewHistory = brewHistory;
                this.hoursOpen = hoursOpen;
                this.brewImage = brewImage;
                this.openMon = openMon;
                this.openTue = openTue;
                this.openWed = openWed;
                this.openThu = openThu;
                this.openFri = openFri;
                this.openSat = openSat;
                this.openSun = openSun;
        }

        public String getObdb_id() {
                return obdb_id;
        }

        public void setObdb_id(String obdb_id) {
                this.obdb_id = obdb_id;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public String getBrewHistory() {
                return brewHistory;
        }

        public void setBrewHistory(String brewHistory) {
                this.brewHistory = brewHistory;
        }

        public String getHoursOpen() {
                return hoursOpen;
        }

        public void setHoursOpen(String hoursOpen) {
                this.hoursOpen = hoursOpen;
        }

        public String getBrewImage() {
                return brewImage;
        }

        public void setBrewImage(String brewImage) {
                this.brewImage = brewImage;
        }

        public boolean isOpenMon() {
                return openMon;
        }

        public void setOpenMon(boolean openMon) {
                this.openMon = openMon;
        }

        public boolean isOpenTue() {
                return openTue;
        }

        public void setOpenTue(boolean openTue) {
                this.openTue = openTue;
        }

        public boolean isOpenWed() {
                return openWed;
        }

        public void setOpenWed(boolean openWed) {
                this.openWed = openWed;
        }

        public boolean isOpenThu() {
                return openThu;
        }

        public void setOpenThu(boolean openThu) {
                this.openThu = openThu;
        }

        public boolean isOpenFri() {
                return openFri;
        }

        public void setOpenFri(boolean openFri) {
                this.openFri = openFri;
        }

        public boolean isOpenSat() {
                return openSat;
        }

        public void setOpenSat(boolean openSat) {
                this.openSat = openSat;
        }

        public boolean isOpenSun() {
                return openSun;
        }

        public void setOpenSun(boolean openSun) {
                this.openSun = openSun;
        }
}
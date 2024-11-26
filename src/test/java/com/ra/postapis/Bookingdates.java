package com.ra.postapis;

public class Bookingdates {
    /*{
           "firstname" : "Jim",
               "lastname" : "Brown",
               "totalprice" : 111,
               "depositpaid" : true,
               "bookingdates" : {
           "checkin" : "2018-01-01",
                   "checkout" : "2019-01-01"
       },
           "additionalneeds" : "Breakfast"
       }*/

    String checkin;
    String checkout;

    public Bookingdates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }
}

package staff.item;

public class Parcel extends Item{
    private String deliveryComp;
    private String tracking;

    public Parcel(
            String typeItem,
            String fullNumRoom,
            String nameRec, String surnameRec,
            String size,
            String nameSend, String surnameSend,
            String address,
            String dateAndTime,
            String deliveryComp, String tracking) {
        super(typeItem,
                fullNumRoom,
                nameRec, surnameRec,
                size,
                nameSend, surnameSend,
                address, dateAndTime);
        this.deliveryComp = deliveryComp;
        this.tracking = tracking;

    }
    public String getDeliveryComp() {
        return deliveryComp;
    }

    public String getTracking() {
        return tracking;
    }

    @Override
    public String toString() {
        return "Package" +super.toString()+
                "delivery Company" +deliveryComp+
                "tracking" +tracking;
    }
}

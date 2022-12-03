package staff.item;

public class Mail extends Item {
    public Mail(
            String typeItem,
            String fullNumRoom,
            String nameRec, String surnameRec,
            String size,
            String nameSend, String surnameSend,
            String address,
            String dateAndTime
    ) {
        super(typeItem, fullNumRoom, nameRec, surnameRec, size, nameSend, surnameSend, address, dateAndTime);

    }

    @Override
    public String toString() {
        return "Mail"+ super.toString();
    }
}

package staff.item;

public class Document extends Item{
    private String priority;
    public Document(
                String typeParcel,
                String fullNumRoom,
                String nameRec, String surnameRec,
                String size,
                String nameSend, String surnameSend,
                String address,
                String dateAndTime,
                String priority
    ){

        super(typeParcel,
                fullNumRoom,
                nameRec, surnameRec,
                size,
                nameSend, surnameSend,
                address, dateAndTime);
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Document"+super.toString()+
                "priority" + priority;
    }
}

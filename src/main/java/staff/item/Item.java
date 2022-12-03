package staff.item;

import staff.room.Room;

import java.util.ArrayList;

public class Item {
    private ArrayList<Item> items;
    private ArrayList<Item> tempItems;
    private String typeItem;

    private String fullNumRoom;

    private String nameRec;
    private String surnameRec;

    private String nameSend;
    private String surnameSend;

    private String size;

    private String dateAndTime;


    private String address;
    public Item(){
        items = new ArrayList<>();
        tempItems = new ArrayList<>();
    }
    public Item(String typeItem,
                String fullNumRoom,
                String nameRec, String surnameRec,
                String size,
                String nameSend, String surnameSend,
                String address,
                String dateAndTime){
        this.typeItem = typeItem;
        this.nameRec = nameRec;
        this.surnameRec = surnameRec;
        this.fullNumRoom = fullNumRoom;
        this.size = size;
        this.nameSend = nameSend;
        this.surnameSend = surnameSend;
        this.address = address;
        this.dateAndTime = dateAndTime;
    }
    public boolean checkRoom(String build, String floor, String room){
        String create = build+floor+room;
        for(Item i: items) {
            if (i.getFullNumRoom().equals(create)) {
                return true;
            }
        }
        return false;
    }

    public void addTempObject(ArrayList<Item> it){
        tempItems.clear();
        tempItems.addAll(it);
    }
    public void addTempObject(String roomNumber){
        tempItems.clear();
        for(Item it:items){
            System.out.println(it.getFullNumRoom());
            if(it.getFullNumRoom().equals(roomNumber)){
                tempItems.add(it);
            }
        }
    }


    public String getTypeItem() {
        return typeItem;
    }

    public String getNameRec() {
        return nameRec;
    }

    public String getSurnameRec() {
        return surnameRec;
    }

    public String concatenateRoom(String building, String floor, String room){
        return building+floor+room;
    }

    public String getFullNumRoom(){
        return fullNumRoom;
    }

    public String getSize(){return size;}

    public String getNameSend() {
        return nameSend;
    }

    public String getSurnameSend() {
        return surnameSend;
    }

    public String getAddress() {
        return address;
    }
    public ArrayList<Item> getTempItems() {
        return tempItems;
    }
    public ArrayList<Item> getItems() {
        return items;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }
    @Override
    public String toString(){
        return "Room : "+fullNumRoom+
                "Name : "+nameRec+
                "Surname : "+surnameRec+
                "Address : " + address;
    }
    public void add(Item m){
        items.add(m);
    }
}

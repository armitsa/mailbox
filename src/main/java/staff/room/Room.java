package staff.room;

import java.util.ArrayList;

public class Room {
    private ArrayList<Room> rooms;
    private String building;
    private String floor;
    private String room;
    private String typeRoom;
    private String name="None";
    private int maxRec = 0;

    public Room(
            String building, String floor, String room,
            String typeRoom,
            String name,
            int maxRec){
        this.building = building;
        this.floor = floor;
        this.room = room;
        this.typeRoom = typeRoom;
        this.name = name;
        this.maxRec = maxRec;

    }
    public Room() {
        rooms = new ArrayList<>();
    }

    public String getFullRoomNumber(){
        return building+floor+room;
    }
    public String getBuilding(){
        return building;
    }
    public String getFloor(){
        return floor;
    }
    public String getRoom(){
        return room;
    }
    public String getTypeRoom(){
        return typeRoom;
    }
    public int getMaxRes() { return maxRec; }
    public String getName() { return name; }
    public ArrayList<Room> getRooms() { return rooms; }

    public boolean checkRoom(String build, String floor, String room){
        String create = build+floor+room;
        for(Room x : rooms) {
            if (x.getFullRoomNumber().equals(create)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String building,String floor,String room,String name){
        String create = building+floor+room;
        for (Room x : rooms){
            if (x.getFullRoomNumber().equals(create) && x.getName().equals("None")){
                x.setName(name);
                return true;
            }
        }
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Building : " + building+ " Floor : " +floor+ " Room : "+room+ " Name : "+name;
    }

    public void add(Room r) { rooms.add(r);
    }




}

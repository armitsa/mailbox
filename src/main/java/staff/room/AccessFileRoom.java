package staff.room;

import java.io.IOException;
import java.util.ArrayList;

public interface AccessFileRoom {
//    boolean checkRoom(String build, String floor, String room) throws IOException;
    void setRooms(Room rooms) throws IOException;
    Room getRoomList() throws IOException;

}

package staff.room;

import java.io.*;
import java.util.ArrayList;


public class MakefileRoom implements AccessFileRoom{
    private String fileDirectoryName;
    private String fileName;

    public MakefileRoom(String fileDirectoryName, String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();

    }
    public void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }


    public Room getRoomList() throws IOException {
        Room rooms = new Room();
        File file = new File(fileDirectoryName + File.separator + fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] data = line.split(",");
            Room room = new Room(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    Integer.parseInt(data[5]));
            rooms.add(room);
        }
        fileReader.close();
        bufferedReader.close();
        return rooms;
    }

    public void setRooms(Room rooms) throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fileWriter);
        String line;
        for (Room x : rooms.getRooms())
        {
            line = x.getBuilding()+","+
                    x.getFloor()+","+
                    x.getRoom()+","+
                    x.getTypeRoom()+","+
                    x.getName()+","+
                    x.getMaxRes();
            writer.append(line);
            writer.newLine();
        }
        writer.close();
        fileWriter.close();
    }
}

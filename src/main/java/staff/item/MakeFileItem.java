package staff.item;


import java.io.*;

public class MakeFileItem implements AccessFileItem {
    private String fileDirectoryName;
    private String fileName;

    public MakeFileItem(String fileDirectoryName, String fileName){
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

    public Item getItemList() throws IOException {
        Item items = new Item();
        File file = new File(fileDirectoryName + File.separator + fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            String[] data = line.split(",");
            if (data[0].equals("Mail")) {
                Mail mail = new Mail(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8]);
                items.add(mail);
            }
            else if (data[0].equals("Document")) {
                Document document = new Document(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]);
                items.add(document);
            }
            else if (data[0].equals("Package")) {
                Parcel parcel = new Parcel(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10]);
                items.add(parcel);
            }
        }
        fileReader.close();
        bufferedReader.close();
        return items;
    }

    public void setItems(Item items) throws IOException {
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
        for (Item m : items.getItems()) {
            if (m instanceof Mail) {
                line = m.getTypeItem() + ","
                        + m.getFullNumRoom()+ ","
                        + m.getNameRec() + "," + m.getSurnameRec() + ","
                        + m.getSize() + ","
                        + m.getNameSend() + "," + m.getSurnameSend() + ","
                        + m.getAddress() + ","
                        + m.getDateAndTime();
                writer.append(line);
                writer.newLine();
            }
            else if (m instanceof Document) {
                line = m.getTypeItem() + ","
                        + m.getFullNumRoom() + ","
                        + m.getNameRec() + "," + m.getSurnameRec() + ","
                        + m.getSize() + ","
                        + m.getNameSend() + "," + m.getSurnameSend() + ","
                        + m.getAddress() + ","
                        + m.getDateAndTime()+","
                        + ((Document) m).getPriority();
                writer.append(line);
                writer.newLine();
            }
            else if (m instanceof Parcel) {
                line = m.getTypeItem() + ","
                        + m.getFullNumRoom()+ ","
                        + m.getNameRec() + "," + m.getSurnameRec() + ","
                        + m.getSize() + ","
                        + m.getNameSend() + "," + m.getSurnameSend() + ","
                        + m.getAddress() + ","
                        + m.getDateAndTime()+","
                        +((Parcel) m).getDeliveryComp()+","
                        +((Parcel) m).getTracking();
                writer.append(line);
                writer.newLine();
            }
        }
        writer.close();
        fileWriter.close();
    }
}

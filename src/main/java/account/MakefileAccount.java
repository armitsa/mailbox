package account;

import java.io.*;


public class MakefileAccount implements AccessFile{
    private String fileDirectoryName;
    private String fileName;
    private Account accounts;

    public MakefileAccount(String fileDirectoryName, String fileName) {
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


    public Account getAccountList() throws IOException{
        accounts = new Account();
        File file = new File(fileDirectoryName+File.separator + fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = "";
        while ((line = bufferedReader.readLine()) != null){
            String[] data = line.split(",");
            Account account = new Account(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    data[4],
                    data[5]);
            accounts.add(account);
        }
        fileReader.close();
        bufferedReader.close();
        return accounts;
    }

    public void setAccounts(Account accounts) throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        BufferedWriter writer = new BufferedWriter(fileWriter);
        String line = "";
        for (Account a: accounts.getAccounts()) {
            line = a.getPermission()+","+
                    a.getUsername()+","+
                    a.getPassword()+","+
                    a.getName()+","+
                    a.getSurname()+","+
                    a.getDateAndTime();
            writer.append(line);
            writer.newLine();

        }
        writer.close();
        fileWriter.close();

    }

}

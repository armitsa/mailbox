package account;

import java.util.ArrayList;

public class Account {
    private ArrayList<Account> accounts;
    private ArrayList<Account> staffsList;
    private String username;
    private String password;
    private String permission;
    private String name;
    private String surname;
    private String dateAndTime;

    public Account(String permission, String username, String password,String name, String surname,String dateAndTime){
        this.permission = permission;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateAndTime = dateAndTime;
    }
    public Account(){
        accounts = new ArrayList<>();
        staffsList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getPermission() { return permission; }
    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public String getDateAndTime() {return dateAndTime; }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public ArrayList<Account> getStaffsList() {
        return staffsList;
    }

    public boolean checkUsername(String username){
        for (Account acc : accounts){
            if (acc.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String toString(){
        return "Permission : " +permission+ " Username : " +username+" login: "+dateAndTime;
    }

    public void add(Account acc) {
        accounts.add(acc);
    }

    public void addStaff(ArrayList<Account> account){
        for (Account acc : account){
            if (acc.getPermission().equals("Staff")){
                staffsList.add(acc);
            }
        }
    }

}

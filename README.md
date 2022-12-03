#### 6210406769
### Directory Structure
 - AliveDomitory(Main program)
    - 6210406769.jar
    - 6210406769.pdf
    - data(CSV folder สำหรับการ log in โปรแกรม)
        - Account.csv
        - Item.csv
        - Room.csv
    - lib
        - bootstrapfx-core-0.2.4.jar
    - 6210406769.jar
    - 6210406769.pdf
 - data(CSV folder ก่อนติดตั้ง program)
    - Account.csv
    - Item.csv
    - Room.csv
 - src(Code)
    - main
        - java
            - account(package สำหรับเก็บ account ทั้งหมด)
                - AboutMeController.java
                - AccessFile.Java
                - Account.java
                - AliveDormitory.java
                - ChangePassword.java
                - GuideController.java
                - LoginController.java
                - MakeFileAccount.java
            - admin(package หน้าที่ของ admin)
                - AddStaffController.java
                - AdminController.java
            - staff(package หน้าที่ของ staff)
                - item
                    - AccessFileItem.java
                    - AddItemController.java
                    - Document.java
                    - Item.java
                    - Mail.java
                    - MakeFileItem.java
                    - Parcel.java
                    - RemoveItemController.java
                    - StaffItemController.java
                - room
                    - AccessFileRoom.java
                    - AddRoomController.java
                    - AddUserController.java
                    - MakeFileRoom.java
                    - Room.java
                    - StaffDormController.java
                - StaffController.java
    - resource
        - picture

#### week 1
 - add marven into project
 - add Class Admin Staff for create account
#### week 2
 - add GUI into project
#### week 3
 - test polymorphism
 - update fxml file for login page
#### week 4
 - make csv file and learning about add file in csv and readfile from .csv file
#### week 5
 - add data to csv 
 - make file README.md
#### week 6
 - fixed bug and update code
#### User test
- admin 
    username : admin 
    password : 0000
- staff
    username : F1
    password : 1111
    
#### update
 - fixed add account
 - update all alert
 - update pdf(csv information)
 - update README
    
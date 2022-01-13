package model;

public class Employee {
    private int id;

    private String name;

    private String contactNumber;

    private String img;


    public Employee(int id, String name, String contactNumber ,String img) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

package backend.Classes;

public class Client {

    private int clientId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clienId) {
        this.clientId = clienId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return clientId + firstName + lastName + address + email;
    }
}

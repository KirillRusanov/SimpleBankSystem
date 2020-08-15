package banking;

public class Account {
    private String id;
    private String pin;
    private int balance;


    public Account(String id, String pin) {
        this.id = id;
        this.pin = pin;
        this.balance = 0;
    }

    public Account() {
        this.id = "Unknown";
        this.pin = "Unknown";
    }

    public String getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public int getBalance() {
        return balance;
    }
}

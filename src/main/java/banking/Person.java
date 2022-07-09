package banking;

public class Person extends AccountHolder {
    private final String firstName;
    private final String lastName;
    private String middleName;

    public Person(String firstName, String middleName, String lastName, int idNumber) {
        super(idNumber);
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Person(String firstName,  String lastName, int idNumber) {
        super(idNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }
}

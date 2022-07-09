package banking;

import java.util.ArrayList;
import java.util.List;

public class PersonalAccount extends Account{

    private List<Person> authorizedUsers;

    public PersonalAccount(AccountHolder accountHolder,
                             Long accountNumber,
                             int pin, double balance) {

        super(accountHolder, accountNumber, pin, balance);
        this.authorizedUsers = new ArrayList<>();
    }

    protected void addAuthorizedUser(Person person) {
        authorizedUsers.add(person);
    }

    public boolean isAuthorizedUser(Person person) {
        for (Person p: authorizedUsers)
        {
            if (p==person)
                return true;
        }
        return false;
    }


}

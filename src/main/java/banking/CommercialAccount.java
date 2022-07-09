package banking;

import java.util.ArrayList;
import java.util.List;

public class CommercialAccount extends Account {

    private List<Company> authorizedUsers = new ArrayList<>();

    public CommercialAccount(AccountHolder accountHolder,
                             Long accountNumber,
                             int pin, double balance) {

        super(accountHolder, accountNumber, pin, balance);
        addAuthorizedUser((Company) accountHolder);
    }

    protected void addAuthorizedUser(Company company) {
        authorizedUsers.add(company);
    }

    public boolean isAuthorizedUser(Company company) {
        for (Company c: authorizedUsers)
        {
            if (c==company)
                return true;
        }
        return false;
    }
}

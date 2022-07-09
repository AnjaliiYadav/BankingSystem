import banking.Bank;
import banking.Company;
import banking.Person;
import banking.Transactions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SampleTest {

    /**
     * The bank/
     */
    Bank bank;

    /**
     * Account for Stark Industries.
     */
    Long starks;

    /**
     * Account for Wonka Industries.
     */

    Long wonka;

    /**
     * Account for George R. R. Martin
     */

    Long martin;

    /**
     * Account for Patrick Rothfuss.
     */

    Long rothfuss;

    @Before
    public void setUp() throws Exception {

        bank = new Bank();
        Company company1StarkIndustries = new Company("Stark Industries", 1);
        Company company2WonkaIndustries = new Company("Wonka Industries", 2);
        Person person1GeorgeMartin = new Person("George", "R. R", "Martin", 1);
        Person person2GPatrickRothfuss = new Person("Patrick", "Rothfuss", 2);

        starks = bank.openCommercialAccount(company1StarkIndustries, 1111, 0.0);
        wonka = bank.openCommercialAccount(company2WonkaIndustries, 2222, 100000.0);

        martin = bank.openPersonalAccount(person1GeorgeMartin, 1111, 0.0);
        rothfuss = bank.openPersonalAccount(person2GPatrickRothfuss, 2222, 578.2);

    }

    @After
    public void clearDown() throws Exception {
        bank = null;
        starks = null;
        wonka = null;

        martin = null;
        rothfuss = null;
    }

    @Test
    public void invalidAccountNumberTest() {
        Assert.assertTrue("1st and 2nd accounts were not assigned sequential account numbers.",
                starks + 1 == wonka);

        Assert.assertTrue("2nd and 3rd accounts were not assigned sequential account numbers.",
                wonka + 1 == martin);

        Assert.assertTrue("3rd and 4th accounts were not assigned sequential account numbers.",
                martin + 1 == rothfuss);

        assertEquals(bank.getBalance(starks), 0.0, 0);
        assertEquals(bank.getBalance(wonka), 100000.0, 0);
        assertEquals(bank.getBalance(martin), 0.0, 0);
        assertEquals(bank.getBalance(rothfuss), 578.2, 0);

    }

    @Test
    public void debitTest() {
        double amount = 100.0;
        assertFalse("Account " + starks + " should have insufficient funds.", bank.debit(starks, amount));
        assertTrue("Account " + wonka + " should have insufficient funds.", bank.debit(wonka, amount));
        assertFalse("Account " + martin + " should have insufficient funds.", bank.debit(martin, amount));
        assertTrue("Account " + rothfuss + " should have insufficient funds.", bank.debit(rothfuss, amount));
    }

    @Test
    public void creditTest() {
        double amount = 357.45;

        double beforeDeposit1 = bank.getBalance(starks);
        double beforeDeposit2 = bank.getBalance(wonka);
        double beforeDeposit3 = bank.getBalance(martin);
        double beforeDeposit4 = bank.getBalance(rothfuss);

        bank.credit(starks, amount);
        bank.credit(wonka, amount);
        bank.credit(martin, amount);
        bank.credit(rothfuss, amount);

        assertEquals(beforeDeposit1 + amount, bank.getBalance(starks), 0);
        assertEquals(beforeDeposit2 + amount, bank.getBalance(wonka), 0);
        assertEquals(beforeDeposit3 + amount, bank.getBalance(martin), 0);
        assertEquals(beforeDeposit4 + amount, bank.getBalance(rothfuss), 0);

    }

    @Test(expected = Exception.class)
    public void invalidPinTransaction() throws Exception {
        new Transactions(martin, bank, 1234);
    }

    @Test
    public void transactionTest() throws Exception {
        Transactions transactions = new Transactions( martin,bank, 1111);
        double beforeDeposit = transactions.getBalance();

        double amount = 23245.43;
        transactions.credit(amount);
        assertEquals(beforeDeposit + amount, transactions.getBalance(), 0);
        assertTrue("Debit was unsuccessful.", transactions.debit(amount));
        assertFalse("This transaction should have overdrawn the account.", transactions.debit(amount));
        assertEquals(beforeDeposit, transactions.getBalance(), 0);
        assertEquals(transactions.getBalance(), bank.getBalance(martin), 0);

    }


}

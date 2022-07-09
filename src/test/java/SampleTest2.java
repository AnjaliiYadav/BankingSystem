import banking.Bank;
import banking.Company;
import banking.Person;
import banking.Transactions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class SampleTest2 {

    /**
     * The bank/
     */
    Bank bank;
    /**
     * The account number for john.
     */
    Long john;
    /**
     * The account number for arya.
     */
    Long arya;

    /**
     * The account number for sansa.
     */
    Long sansa;
    /**
     * The account number for robb
     */
    Long robb;
    /**
     * The account number for bran
     */
    Long bran;


    @Before
    public void setUp() throws Exception {
        bank = new Bank();
        Person person1john = new Person("john", "snow", 1);
        Person person2arya = new Person("arya", "stark", 2);
        Person person3daniel = new Person("sansa", "stark", 3);
        Company company1robb = new Company("robb", 1);
        Company company2bran = new Company("bran", 2);
        john = bank.openPersonalAccount(person1john, 1111, 0.0);
        arya = bank.openPersonalAccount(person2arya, 2222, 456.78);
        sansa = bank.openPersonalAccount(person3daniel,3333,500.00);
        robb = bank.openCommercialAccount(company1robb, 1111, 0.0);
        bran = bank.openCommercialAccount(company2bran, 2222, 123456789.00);
    }

    @After
    public void tearDown() throws Exception {
        bank = null;
        john = null;
        arya = null;
        sansa =null;
        robb = null;
        bran = null;
    }

    @Test
    public void invalidAccountNumberTest() {
        Assert.assertTrue("1st and 2nd accounts were not assigned sequential account numbers.",
                john + 1 == arya);
        Assert.assertTrue("2nd and 3rd accounts were not assigned sequential account numbers.",
                arya + 1 == sansa);
        Assert.assertTrue("3rd and 4th accounts were not assigned sequential account numbers.",
                sansa + 1 == robb);
        Assert.assertTrue("4rd and 5th accounts were not assigned sequential account numbers.",
                robb + 1 == bran);

        assertEquals(bank.getBalance(john), 0.0, 0);
        assertEquals(bank.getBalance(arya), 456.78, 0);
        assertEquals(bank.getBalance(sansa), 500.00, 0);
        assertEquals(bank.getBalance(robb), 0.0, 0);
        assertEquals(bank.getBalance(bran), 123456789.00, 0);
        Assert.assertNotEquals(bank.getBalance(john), bank.getBalance(arya));
        Assert.assertNotEquals(bank.getBalance(john), bank.getBalance(sansa));
        Assert.assertNotEquals(bank.getBalance(robb), bank.getBalance(bran));
    }

    /**
     * Debit an account.
     */
    @Test
    public void debitTest() {
        double amount = 200.0;
        assertFalse("Account " + john + " should have insufficient funds.", bank.debit(john, amount));
        assertTrue("Account " + arya + " should have sufficient funds.", bank.debit(arya, amount));
        assertTrue("Account " + sansa + " should have sufficient funds.", bank.debit(sansa, amount));
        assertFalse("Account " + robb + " should have insufficient funds.", bank.debit(robb, amount));
        assertTrue("Account " + bran + " should have sufficient funds.", bank.debit(bran, amount));
    }

    /**
     * Test crediting accounts inside {@link Bank}.
     */
    @Test
    public void bankCreditTest() {
        double amount = 500.00;
        double beforeDeposit1 = bank.getBalance(john);
        double beforeDeposit2 = bank.getBalance(arya);
        double beforeDeposit3 = bank.getBalance(sansa);
        double beforeDeposit4 = bank.getBalance(robb);
        double beforeDeposit5 = bank.getBalance(bran);
        bank.credit(john, amount);
        bank.credit(arya, amount);
        bank.credit(sansa, amount);
        bank.credit(robb, amount);
        bank.credit(bran, amount);
        assertEquals(beforeDeposit1 + amount, bank.getBalance(john), 0);
        assertEquals(beforeDeposit2 + amount, bank.getBalance(arya), 0);
        assertEquals(beforeDeposit3 + amount, bank.getBalance(sansa),0);
        assertEquals(beforeDeposit4 + amount, bank.getBalance(robb), 0);
        assertEquals(beforeDeposit5 + amount, bank.getBalance(bran), 0);
    }

    /**
     * Tests {@link Transactions}: an attempt to access an account with an invalid PIN must throw an
     * Exception.
     *
     * @throws Exception
     *             Account validation failed.
     */
    @Test(expected = Exception.class)
    public void invalidPinTransaction() throws Exception {
        new Transactions( john,bank, 9999);
    }

    /**
     * Tests {@link Transactions}
     *
     * @throws Exception
     *             Account validation failed.
     */
    @Test
    public void transactionTest() throws Exception {
        Transactions transaction1 = new Transactions(sansa, bank,3333);
        double beforeDeposit1 = transaction1.getBalance();
        double amount = 1000000.23;
        transaction1.credit(amount);
        assertEquals(beforeDeposit1 + amount, transaction1.getBalance(), 0);
        assertTrue("Debit was unsuccessful.", transaction1.debit(amount));
        assertFalse("This transaction should have overdrawn the account.", transaction1.debit(amount));
        assertEquals(beforeDeposit1, transaction1.getBalance(), 0);
        assertEquals(transaction1.getBalance(), bank.getBalance(sansa), 0);
    }
    @Test
    public void transactionTest2() throws Exception{
        Transactions t2 = new Transactions( john,bank, 1111);
        double beforeDeposit1 = t2.getBalance();
        double amount = 19239.34;
        t2.credit(amount);
        assertEquals(beforeDeposit1 + amount, t2.getBalance(), 0);
        assertTrue("Debit was unsuccessful.", t2.debit(amount));
        assertEquals(beforeDeposit1, t2.getBalance(), 0);
        assertEquals(t2.getBalance(), bank.getBalance(john), 0);
    }
}

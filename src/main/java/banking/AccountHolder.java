package banking;

public abstract class AccountHolder {

    private final int idNumber;

    /**
     * @param idNumber identification ID issued by the government
     */
    public AccountHolder(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return private int{@link AccountHolder#idNumber}
     */

    public int getIdNumber() {
        return idNumber;
    }
}

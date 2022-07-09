package banking;

public class Company extends AccountHolder {
    public final String companyName;

    public Company(String companyName, int taxId) {
        super(taxId);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
}

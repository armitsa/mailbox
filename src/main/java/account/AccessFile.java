package account;
import java.io.IOException;

public interface AccessFile {

    Account getAccountList() throws IOException;
    void setAccounts(Account accounts) throws IOException;

}

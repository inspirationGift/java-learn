package ATM_sample;

import java.util.HashMap;

public interface AccountOperation {
    Boolean loginToAccount(HashMap<String, String> hashMap);

    Double withdraw(Double draw);

    Double deposit(Double sum);
}

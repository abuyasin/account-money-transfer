package abu.sadat.yasin.core.service;

import abu.sadat.yasin.dto.AccountData;

import java.util.List;
import java.util.Map;

/**
* @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */

public interface IAccountService {

    List<AccountData> getAccounts(Map<String, Object> param) throws Exception;

    void createAccount(AccountData account) throws Exception;
 
    void updateAccount(AccountData account) throws Exception;
    
}

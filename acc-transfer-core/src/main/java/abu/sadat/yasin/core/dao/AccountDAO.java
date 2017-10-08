package abu.sadat.yasin.core.dao;

import abu.sadat.yasin.dto.AccountData;

import java.util.List;
import java.util.Map;

/**
* @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
*/
public interface AccountDAO {

    void createAnAccount(AccountData account) throws Exception;

    void updateAnAccount(AccountData account) throws Exception;

    List<AccountData> getAccountList(Map<String, Object> param) throws Exception;

}

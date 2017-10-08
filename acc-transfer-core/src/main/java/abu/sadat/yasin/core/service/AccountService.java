package abu.sadat.yasin.core.service;

import abu.sadat.yasin.dto.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import abu.sadat.yasin.core.dao.AccountDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@Service("accountService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AccountService extends BaseService implements IAccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public List<AccountData> getAccounts(Map<String, Object> param) throws Exception {
        return this.accountDAO.getAccountList(param);
    }

    @Override
    public void createAccount(AccountData account) throws Exception {
        if (account == null) {
            throw new Exception("Account cannot be null");
        }
        if (account.getAccountNo() != null || !account.getAccountNo().equalsIgnoreCase("")) {
            this.accountDAO.createAnAccount(account);
        } else {
            throw new Exception("Can't create a new Account");
        }
    }

    @Override
    public void updateAccount(AccountData account) throws Exception {

//        System.out.print(account);
        if (account == null) {
            throw new Exception("Account cannot be null");
        } else {
            this.accountDAO.updateAnAccount(account);
        }
    }

}

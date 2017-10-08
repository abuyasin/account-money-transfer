package abu.sadat.yasin.rest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import abu.sadat.yasin.dto.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import abu.sadat.yasin.core.service.IAccountService;

/**
 * AccountController is used to create, update and view the accounts and its balance.
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@RequestMapping(value = "/account")
@RestController
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private IAccountService accountService;

    /**
     * Provides list of accounts
     * @param accountNo optional parameter, if accountNo placed than only single 
     * account row of data will be available otherwise whole list of accounts 
     * will be displayed.
     * @return List of accounts in JSON format
     */
    @ResponseBody
    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public List<AccountData> getAccounts(
            @RequestParam(name = "accountNo", required = false) String accountNo
    ) {
        List<AccountData> accounts = null;

        // Map the request parameters
        Map<String, Object> param = new HashMap();
        if (accountNo != null && StringUtils.isNotBlank(accountNo)) {
            param.put("accountNo", accountNo);
        }

        // Try fetching accounts
        try {
            accounts = this.accountService.getAccounts(param);
        } catch (Exception e) {
            log.error("Error on retrieving accounts", e);
        }

        // Return empty list in case of null
        if (CollectionUtils.isEmpty(accounts)) {
            accounts = Collections.EMPTY_LIST;
        }

        return accounts;
    }

    /**
     * Create a new account
     * @param account, it takes AccountData parameters
     * @param result, used to get validation errors.
     * @return created account info with success message otherwise display error message in JSON format.
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AccountData createAccount(@Valid @RequestBody AccountData account, BindingResult result) {
        if (result.hasErrors()) {
            log.error("================Has validation Error in REST Account Create==============" + result.toString());
            account.setCode(-1);
            account.setMsg(result.toString());
        } else {
            try {
                this.accountService.createAccount(account);
                account.setCode(1);
                account.setMsg("success");
            } catch (Exception e) {
                log.error("Error on creating account", e);
                account.setCode(-1);
                account.setMsg(e.toString());
            }
        }

        return account;
    }

    /**
     * Update an account
     * @param account, account information need to be update.
     * @param result, provides validation errors.
     * @return updated account info with success message otherwise fail message in JSON format.
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public AccountData updateAccount(@Valid @RequestBody AccountData account, BindingResult result) {
        if (result.hasErrors()) {
            log.error("================Has validation Error in REST Account UPDATE==============" + result.toString());
            account.setCode(-1);
            account.setMsg(result.toString());
        } else {
            try {
                this.accountService.updateAccount(account);
                account.setCode(1);
                account.setMsg("success");
            } catch (Exception e) {
                log.error("Error on updating account", e);
                account.setCode(-1);
                account.setMsg(e.toString());
            }
        }

        return account;
    }
}

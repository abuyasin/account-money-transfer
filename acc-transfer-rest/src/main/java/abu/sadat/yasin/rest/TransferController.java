package abu.sadat.yasin.rest;

import abu.sadat.yasin.core.service.IAccountService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import abu.sadat.yasin.dto.TransferData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import abu.sadat.yasin.core.service.ITransferService;
import abu.sadat.yasin.dto.AccountData;

/**
 * TransferController used to transfer and request money between two accounts.
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@RequestMapping(value = "/transfer")
@RestController
public class TransferController {

    private static final Logger log = LoggerFactory.getLogger(TransferController.class);

    @Autowired
    private ITransferService transferService;

    @Autowired
    private IAccountService accountService;

    private AccountData srcAccount = new AccountData();
    private AccountData desAccount = new AccountData();

    /**
     * Get the list of transactions
     * @param transactionId, optional param, if placed will return a sigle transaction data or null
     * @return transaction data list in JSON format
     */
    @ResponseBody
    @RequestMapping(value = {"", "/", "/list"}, method = RequestMethod.GET)
    public List<TransferData> getTransfers(
            @RequestParam(name = "transactionId", required = false) String transactionId
    ) {
        List<TransferData> transfers = null;

        // Map the request parameters
        Map<String, Object> param = new HashMap();
        if (transactionId != null && StringUtils.isNotBlank(transactionId)) {
            param.put("transactionId", transactionId);
        }

        // Try fetching transfers
        try {
            transfers = this.transferService.getTransfers(param);
        } catch (Exception e) {
            log.error("Error on retrieving transfers", e);
        }

        // Return empty list in case of null
        if (CollectionUtils.isEmpty(transfers)) {
            transfers = Collections.EMPTY_LIST;
        }

        return transfers;
    }

    /**
     * Create a new transaction with transfer or request
     * @param transfer, TransferData parameters
     * @param result, provides validation errors
     * @return created transfer or request info and if its a transfer than balance 
     * update in accounts with success or fail message in JSON format.
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public TransferData createTransfer(@Valid @RequestBody TransferData transfer, BindingResult result) {
        if (result.hasErrors()) {
            log.error("================Has validation Error in REST TRANSFER CREATE==============" + result.toString());
            transfer.setCode(-1);
            transfer.setMsg(result.toString());
        } else {
            if (transfer.getType().equals("t")) { // for transfer money

                try {
//                    log.info("data+++++++++++++++="+transfer.toString());
                    Map<String, Object> param = new HashMap();
                    param.put("accountNo", transfer.getSrcAccountNo());
                    srcAccount = (AccountData) this.accountService.getAccounts(param).get(0);

                    if (srcAccount == null) { //Check for Invalid or null source account
                        log.error("Invalid or null source account");
                        transfer.setCode(-1);
                        transfer.setMsg("Invalid or null source account");
                    } else {
                        Map<String, Object> param2 = new HashMap();
                        param2.put("accountNo", transfer.getDesAccountNo());
                        desAccount = (AccountData) this.accountService.getAccounts(param2).get(0);

                        if (desAccount == null) { // check for Invalid or null destination account
                            log.error("Invalid or null destination account");
                            transfer.setCode(-1);
                            transfer.setMsg("Invalid or null destination account");
                        } else {
                            if (srcAccount.getBalance() < transfer.getAmount()) { // check for sufficient balance in current account
                                transfer.setCode(-1);
                                transfer.setMsg("Insufficiant balance into source Account");
                            } else {
                                try {
                                    srcAccount.setBalance(srcAccount.getBalance() - transfer.getAmount());
                                    this.accountService.updateAccount(srcAccount);

                                    desAccount.setBalance(desAccount.getBalance() + transfer.getAmount());
                                    this.accountService.updateAccount(desAccount);

                                    transfer.setStatus(1);
                                    this.transferService.createTransfer(transfer);
                                    transfer.setCode(1);
                                    transfer.setMsg("success");
                                } catch (Exception e) {
                                    log.error("Error on creating transfer", e);
                                    transfer.setCode(-1);
                                    transfer.setMsg(e.toString());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    log.error("Error on getting transfer srcAccountNo", ex);
                    transfer.setCode(-1);
                    transfer.setMsg("Error on getting transfer srcAccountNo" + ex);
                }
            } else { // for request, request will be created but money transfer update in blance will effect later
                try {
                    this.transferService.createTransfer(transfer);
                    transfer.setCode(1);
                    transfer.setMsg("success");
                } catch (Exception e) {
                    log.error("Error on creating transfer", e);
                    transfer.setCode(-1);
                    transfer.setMsg(e.toString());
                }
            }
        }

        return transfer;
    }

    /**
     * update a transfer, actually it only update request types transfer.
     * @param transfer, TransferData is a request transfer info
     * @param result provides validation error however not in used.
     * @return updated request info and balance update into account with success 
     * or fail message in JSON format
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public TransferData updateTransfer(@Valid @RequestBody TransferData transfer, BindingResult result) {
//        if (result.hasErrors()) {
//            log.error("================Has validation Error in REST TRANSFER UPDATE==============" + result.toString());
//        } else {

        try {

            if (transfer.getType().equals("r") && transfer.getStatus() == 1) {
                Map<String, Object> paramT = new HashMap();
                paramT.put("transactionId", transfer.getTransactionId());

                // Try fetching transfers
                transfer = this.transferService.getTransfers(paramT).get(0);

                if (transfer == null) {// invalid transaction
                    transfer.setCode(-1);
                    transfer.setMsg("Invalid transaction, there is no such transaction.");
                } else {
                    try {
                        Map<String, Object> param = new HashMap();
                        param.put("accountNo", transfer.getSrcAccountNo());
                        srcAccount = (AccountData) this.accountService.getAccounts(param).get(0);

                        if (srcAccount == null) { //Check for Invalid or null source account
                            log.error("Invalid or null source account");
                            transfer.setCode(-1);
                            transfer.setMsg("Invalid or null source account");
                        } else {
                            Map<String, Object> param2 = new HashMap();
                            param2.put("accountNo", transfer.getDesAccountNo());
                            desAccount = (AccountData) this.accountService.getAccounts(param2).get(0);

                            if (desAccount == null) { // check for Invalid or null destination account
                                log.error("Invalid or null destination account");
                                transfer.setCode(-1);
                                transfer.setMsg("Invalid or null destination account");
                            } else {
                                if (srcAccount.getBalance() < transfer.getAmount()) { // check for sufficiant balance.
                                    transfer.setCode(-1);
                                    transfer.setMsg("Insufficiant balance into source Account, Can't be accept.");
                                } else {
                                    try {
                                        srcAccount.setBalance(srcAccount.getBalance() - transfer.getAmount());
                                        this.accountService.updateAccount(srcAccount);

                                        desAccount.setBalance(desAccount.getBalance() + transfer.getAmount());
                                        this.accountService.updateAccount(desAccount);

                                        transfer.setStatus(1);
                                        this.transferService.updateTransfer(transfer);
                                        transfer.setCode(1);
                                        transfer.setMsg("success");
                                    } catch (Exception e) {
                                        log.error("Error on updating transfer request", e);
                                        transfer.setCode(-1);
                                        transfer.setMsg(e.toString());
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        log.error("Error on getting transfer request srcAccountNo", ex);
                        transfer.setCode(-1);
                        transfer.setMsg("Error on getting transfer request srcAccountNo" + ex);
                    }
                }
            } else if (transfer.getType().equals("t")) { // once a transfer happened, it will not be updated anymore.
                transfer.setCode(-1);
                transfer.setMsg("Tranfer is not updateable, its alreay transfered.");
            } else { // request may reject 
                try {
                    this.transferService.updateTransfer(transfer);
                    transfer.setCode(1);
                    transfer.setMsg("success");
                } catch (Exception e) {
                    log.error("Error on updating transfer", e);
                    transfer.setCode(-1);
                    transfer.setMsg(e.toString());
                }
            }
        } catch (Exception e) {
            log.error("Error on getting transfer data", e);
            transfer.setCode(-1);
            transfer.setMsg(e.toString());
        }
//        }

        return transfer;
    }
}

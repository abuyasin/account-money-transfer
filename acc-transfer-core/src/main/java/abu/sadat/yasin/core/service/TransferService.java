package abu.sadat.yasin.core.service;

import abu.sadat.yasin.dto.TransferData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import abu.sadat.yasin.core.dao.TransferDAO;
import java.text.SimpleDateFormat;

/**
 * @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
@Service("transferService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TransferService extends BaseService implements ITransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private TransferDAO transferDAO;

    @Override
    public List<TransferData> getTransfers(Map<String, Object> param) throws Exception {
        return this.transferDAO.getTransferList(param);
    }

    @Override
    public void createTransfer(TransferData transfer) throws Exception {
        if (transfer == null) {
            throw new Exception("Transfer cannot be null");
        }     
        else if (transfer.getTransactionId() == null || transfer.getTransactionId().equalsIgnoreCase("")) {

            String randomId1 = UUID.randomUUID().toString().substring(0, 5);
            Date dNow = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("y-MM-dd-HH-mm-ss-SSS");
            transfer.setTransactionId( dateFormat.format(dNow) + ":" + randomId1);

            System.err.println("=======" + transfer.toString() + "==========");
            this.transferDAO.createAnTransfer(transfer);
        } else {
            throw new Exception("Can't create a Transfer");
        }

    }

    @Override
    public void updateTransfer(TransferData transfer) throws Exception {

        if (transfer == null) {
            throw new Exception("Transfer cannot be null");
        } else {
            this.transferDAO.updateAnTransfer(transfer);
        }
    }

}

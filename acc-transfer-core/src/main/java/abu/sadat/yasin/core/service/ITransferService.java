package abu.sadat.yasin.core.service;

import java.util.List;
import java.util.Map;

import abu.sadat.yasin.dto.TransferData;

/**
* @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
**/

public interface ITransferService {

    List<TransferData> getTransfers(Map<String, Object> param) throws Exception;

    void createTransfer(TransferData transfer) throws Exception;

    void updateTransfer(TransferData transfer) throws Exception;
}

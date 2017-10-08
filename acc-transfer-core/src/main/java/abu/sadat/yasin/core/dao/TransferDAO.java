package abu.sadat.yasin.core.dao;

import abu.sadat.yasin.dto.TransferData;

import java.util.List;
import java.util.Map;

/**
* @author Abu Sadat Mohammed Yasin, Dhaka, Bangladesh
 */
public interface TransferDAO {

    void createAnTransfer(TransferData transfer) throws Exception;

    void updateAnTransfer(TransferData transfer) throws Exception;

    List<TransferData> getTransferList(Map<String, Object> param) throws Exception;

}

/**
 * 
 */
package net.gigs2go.tableserver.core.data;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author tim
 *
 */
public class DataServiceFactory {
    private Logger log = LoggerFactory.getLogger(DataServiceFactory.class);

    private Map<String, TransferDataService> transferServices = new HashMap<String, TransferDataService>();
    private Map<String, StorageDataService> storageServices = new HashMap<String, StorageDataService>();

    /**
     * @return the transferServices
     */
    public Map<String, TransferDataService> getTransferServices() {
        return this.transferServices;
    }

    /**
     * @param transferServices the transferServices to set
     */
    public void setTransferServices(Map<String, TransferDataService> transferServices) {
        this.transferServices = transferServices;
    }

    /**
     * @return the storageServices
     */
    public Map<String, StorageDataService> getStorageServices() {
        return this.storageServices;
    }

    /**
     * @param storageServices the storageServices to set
     */
    public void setStorageServices(Map<String, StorageDataService> storageServices) {
        this.storageServices = storageServices;
    }

}

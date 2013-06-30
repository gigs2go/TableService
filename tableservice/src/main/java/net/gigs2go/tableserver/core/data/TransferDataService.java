/**
 * 
 */
package net.gigs2go.tableserver.core.data;

import java.io.InputStream;


/**
 * @author tim
 * 
 */
public interface TransferDataService {

    Schema getSchema(InputStream is) throws TransferException;

    Data getData(InputStream is) throws TransferException;

    //void writeXML(Object o);

}
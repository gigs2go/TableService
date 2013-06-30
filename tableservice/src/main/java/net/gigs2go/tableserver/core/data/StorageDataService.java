/**
 * 
 */
package net.gigs2go.tableserver.core.data;


/**
 * @author tim
 * 
 */
public interface StorageDataService {
    Result addSchema(Schema schema) throws StorageException;

    Schema loadSchema(String schemaref) throws StorageException;

    Result addData(Schema schema, Data data) throws StorageException;
}

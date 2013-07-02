/**
 * 
 */
package uk.org.gigs2go.tableserver.core.data;


/**
 * @author tim
 * 
 */
public interface StorageDataService {
    Result addSchema(Schema schema) throws StorageException;

    Schema loadSchema(String schemaref) throws StorageException;

    Result addData(Schema schema, Table table) throws StorageException;
}

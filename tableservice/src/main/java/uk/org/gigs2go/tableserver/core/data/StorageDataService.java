/**
 * 
 */
package uk.org.gigs2go.tableserver.core.data;

import java.util.Map;

/**
 * @author tim
 * 
 */
public interface StorageDataService {
    Result addSchema(Schema schema) throws StorageException;

    Schema loadSchema(String schemaref) throws StorageException;

    Result addData(Schema schema, Table table) throws StorageException;

    Map<String, Object> getResults(Query query) throws StorageException;

    /**
     * Converts from the given aggregation type to the local DB syntax
     * @param aggregation
     * @return
     * @throws StorageException
     */
    String getAggregation(AggregationType aggregation) throws StorageException;

    /**
     * Converts from a String to an appropriate local DB storage format
     * @param type
     * @param value
     * @return
     */
    Object getDBValue(ColumnDataType type, String value);

}

/**
 * 
 */
package uk.org.gigs2go.tableserver.core.services.storage;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import uk.org.gigs2go.tableserver.core.data.AggregationType;
import uk.org.gigs2go.tableserver.core.data.Column;
import uk.org.gigs2go.tableserver.core.data.ColumnDataType;
import uk.org.gigs2go.tableserver.core.data.ColumnType;
import uk.org.gigs2go.tableserver.core.data.ColumnValue;
import uk.org.gigs2go.tableserver.core.data.Query;
import uk.org.gigs2go.tableserver.core.data.Result;
import uk.org.gigs2go.tableserver.core.data.Row;
import uk.org.gigs2go.tableserver.core.data.Schema;
import uk.org.gigs2go.tableserver.core.data.Schema.Columns;
import uk.org.gigs2go.tableserver.core.data.StorageDataService;
import uk.org.gigs2go.tableserver.core.data.StorageException;
import uk.org.gigs2go.tableserver.core.data.Table;

/**
 * @author tim
 * 
 */
@Service("mongoCollectionService")
public class MongoStorageDataServiceImpl implements StorageDataService {
    private Logger log = LoggerFactory.getLogger(MongoStorageDataServiceImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public MongoStorageDataServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see uk.org.gigs2go.tableserver.core.services.storage.DataService#addSchema(uk.org.gigs2go.tableserver.core.data.jaxb.Schema)
     */
    @Override
    public Result addSchema(Schema schema) throws StorageException {
        Result result = new Result();
        Map<String, Object> doc = new HashMap<String, Object>();
        doc.put("_id", schema.getName());
        //doc.putAll(schema.getColumns());
        for (Column column : schema.getColumns().getColumn()) {
            ColumnDataType dataType = column.getDataType();
            ColumnType type = column.getType();
            Boolean indexed = column.isIndexed();
            Map<String, Object> dbColumn = new HashMap<String, Object>();
            dbColumn.put(Column.DATATYPE, dataType.toString());
            dbColumn.put(Column.TYPE, type.toString());
            dbColumn.put(Column.INDEXED, indexed.toString());

            log.debug("Adding column {}", column);
            doc.put(column.getName(), dbColumn);
        }
        log.debug("Adding schema {} with {} columns", schema.getName(), doc.size());
        log.debug("Doc is {}", doc);
        DB db = mongoTemplate.getDb();
        log.debug("DB is {}", db);
        log.debug("Creating DBObject");
        DBObject dbObject = new BasicDBObject(doc);
        log.debug("Created DBObject : {}", dbObject.toString());
        DBCollection dbCollection = db.getCollection(Schema.COLLECTION);
        dbCollection.save(dbObject);
        return result;
    }

    /* (non-Javadoc)
     * @see uk.org.gigs2go.tableserver.core.services.storage.DataService#loadSchema(java.lang.String)
     */
    @Override
    public Schema loadSchema(String schemaref) throws StorageException {
        Schema result = null;
        log.debug("Loading {}", schemaref);
        DB db = mongoTemplate.getDb();
        DBCollection dbCollection = db.getCollection(Schema.COLLECTION);
        DBObject schemaQuery = new BasicDBObject("_id", schemaref);
        DBObject dbSchema = dbCollection.findOne(schemaQuery);
        if (dbSchema != null) {
            result = new Schema();
            result.setName(schemaref);
            Columns columns = result.getColumns();
            uk.org.gigs2go.tableserver.core.data.Column column = null;
            for (String s : dbSchema.keySet()) {
                if (s.equals("_id")) {
                    log.debug("Got schema name {}", dbSchema.get(s));
                    continue;
                }
                log.debug("Looking for key {}", s);
                DBObject o = (DBObject) dbSchema.get(s);
                log.debug("Got object {}", o);
                column = new Column();
                column.setName(s);
                column.setDataType(ColumnDataType.valueOf((String) o.get(Column.DATATYPE)));
                column.setType(ColumnType.valueOf((String) o.get(Column.TYPE)));
                columns.getColumn().add(column);
            }
        }
        log.debug("Got {}", result);
        return result;
    }

    /* (non-Javadoc)
     * @see uk.org.gigs2go.tableserver.core.services.storage.DataService#addData(uk.org.gigs2go.tableserver.core.data.jaxb.Schema, uk.org.gigs2go.tableserver.core.data.jaxb.Data)
     */
    @Override
    public Result addData(Schema schema, Table table) throws StorageException {
        Result result = new Result();
        // First map schema for retrieval
        Map<String, Column> columns = new HashMap<String, Column>();
        for (Column column : schema.getColumns().getColumn()) {
            columns.put(column.getName(), column);
        }
        DB db = mongoTemplate.getDb();
        log.debug("Adding data to collection {}", table.getName());
        DBCollection dbCollection = db.getCollection(table.getName());

        // Map the data collection to the schema
        DBObject dbObject = new BasicDBObject("schemaName", schema.getName());
        dbCollection.save(dbObject);
        // Map the data to the schema
        Map<String, Object> doc = null;
        for (Row row : table.getRows().getRow()) {
            doc = new HashMap<String, Object>();
            for (ColumnValue value : row.getColumnvalue()) {
                log.debug("Got {}", value);
                Column column = columns.get(value.getName().trim());
                log.debug("Got {}", column);
                ColumnDataType type = column.getDataType();
                doc.put(value.getName().trim(), getDBValue(type, value.getValue().trim()));
            }
            dbObject = new BasicDBObject(doc);
            dbCollection.save(dbObject);
        }

        log.debug("Got {}", result);
        return result;
    }

    public Object getDBValue(ColumnDataType type, String value) {
        Object result = type.convert((String) value);

        if (result != null) {
            switch (type) {
                case DATE:
                case DATETIME:
                case YEAR:
                    result = ((LocalDate) result).toDate();
                    break;
                default:
                    break;

            }
        }

        return result;
    }

    /*
     * Dimension 
     * Measure 
     * Aggregation - count, sum, min, max, avg
     * Table(Collection)
     * 
     * SELECT <Dimension>, <Aggregation>(<Measure>) as <Aggregation><Measure>
     * FROM <Table> GROUP BY <Dimension>
     * 
     * db.<Table>.aggregate( [ { $group: { _id: "$<Dimension2>", <Aggregation><Measure>: { $<Aggregation>: "$<Measure>" } } } ] )
     */
    @Override
    public Map<String, Object> getResults(Query query) throws StorageException {
        final Map<String, Object> result = new HashMap<String, Object>();
        String collectionName = query.getCollectionName();
        String dimension = query.getDimension();
        String measure = query.getMeasure();
        String aggregation = getAggregation(query.getAggregation());
        log.debug("Getting data for collection {}", collectionName);
        DBCollection collection = mongoTemplate.getDb().getCollection(collectionName);
        DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$" + dimension).append(aggregation + measure, new BasicDBObject("$" + aggregation, "$" + measure)));
        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));
        log.debug("Group : {}", group);
        AggregationOutput output = collection.aggregate(group, sort);
        for (DBObject resultObject : output.results()) {
            String name = (String) resultObject.get("_id");
            Object value = resultObject.get(aggregation + measure);
            if (value == null) {
                value = "null";
            }
            log.debug("Adding datapoint {} : {}", name, value);
            result.put(name, value);
        }
        return result;
    }

    //
    //    /*
    //     * Dimension 
    //     * Dimension2 
    //     * DimensionValue 
    //     * Measure 
    //     * Aggregation - count, sum, min, max, avg 
    //     * Table(Collection)
    //     * 
    //     * SELECT <Dimension2>, <Aggregation>(<Measure>) as <Aggregation><Measure>
    //     * FROM <Table> 
    //     * WHERE <Dimension> = <DimensionValue> 
    //     * GROUP BY <Dimension2>
    //     * 
    //     * db.<Table>.aggregate( [ { $match: { <Dimension>: '<DimensionValue>' } }, TODO - check quotes for DimensionValue 
    //     * { $group: { _id: "$<Dimension2>", <Aggregation><Measure>: { $<Aggregation>: "$<Measure>" }
    //     * } } ] )
    //     */
    //    @Override
    //    public List<ChartItem> getChartItems(String collectionName, String dimension, String dimensionValue, String dimension2, String measure, String aggregation) {
    //        final List<ChartItem> result = new ArrayList<ChartItem>();
    //        log.debug("Getting data for collection {}", collectionName);
    //        log.debug("Using columns : {}/{}", dimension, dimension2);
    //        DBCollection collection = mongoTemplate.getDb().getCollection(collectionName);
    //        DBObject match = new BasicDBObject("$match", new BasicDBObject(dimension, dimensionValue));
    //        DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$" + dimension2).append(aggregation + measure, new BasicDBObject("$" + aggregation, "$" + measure)));
    //        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));
    //        log.debug("Match : {}", match);
    //        log.debug("Group : {}", group);
    //        AggregationOutput output = collection.aggregate(match, group, sort);
    //        for (DBObject resultObject : output.results()) {
    //            ChartItem item = new ChartItem();
    //            item.setName((String) resultObject.get("_id"));
    //            Object value = resultObject.get(aggregation + measure);
    //            if (value == null) {
    //                value = "null";
    //            }
    //            item.setValue(value.toString());
    //            log.debug("Adding item {}", item);
    //            result.add(item);
    //            log.debug("Got {}", item);
    //        }
    //        return result;
    //    }
    //

    /* (non-Javadoc)
     * @see uk.org.gigs2go.tableserver.core.data.StorageDataService#getAggregation(uk.org.gigs2go.tableserver.core.data.AggregationType)
     */
    @Override
    public String getAggregation(AggregationType aggregation) throws StorageException {
        String result = null;
        switch (aggregation) {
            case COUNT:
                result = "count";
                break;
            case AVG:
                result = "avg";
                break;
            case MAX:
                result = "max";
                break;
            case MIN:
                result = "min";
                break;
            case SUM:
                result = "sum";
                break;
            default:
                Result error = new Result();
                error.setSuccess(Result.FAIL);
                error.getMessages().add("Unhandled Aggregation type " + aggregation.toString());
                throw new StorageException(error);
        }
        return result;
    }
}

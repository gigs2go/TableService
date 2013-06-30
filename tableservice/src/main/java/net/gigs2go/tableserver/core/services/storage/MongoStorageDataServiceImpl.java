/**
 * 
 */
package net.gigs2go.tableserver.core.services.storage;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import net.gigs2go.tableserver.core.data.Column;
import net.gigs2go.tableserver.core.data.ColumnDataType;
import net.gigs2go.tableserver.core.data.ColumnType;
import net.gigs2go.tableserver.core.data.ColumnValue;
import net.gigs2go.tableserver.core.data.Data;
import net.gigs2go.tableserver.core.data.Result;
import net.gigs2go.tableserver.core.data.Row;
import net.gigs2go.tableserver.core.data.Schema;
import net.gigs2go.tableserver.core.data.Schema.Columns;
import net.gigs2go.tableserver.core.data.StorageDataService;
import net.gigs2go.tableserver.core.data.StorageException;

//import net.gigs2go.tableserver.core.data.jaxb.Row;

/**
 * @author tim
 * 
 */
@Service("mongoCollectionService")
public class MongoStorageDataServiceImpl implements StorageDataService {
    private Logger log = LoggerFactory.getLogger(MongoStorageDataServiceImpl.class);

    @Autowired
    MongoTemplate mongoTemplate;

    // @Autowired
    // MongoDocumentRepository documentRepo;

    public MongoStorageDataServiceImpl() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see net.gigs2go.tableserver.core.services.storage.DataService#addSchema(net.gigs2go.tableserver.core.data.jaxb.Schema)
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
     * @see net.gigs2go.tableserver.core.services.storage.DataService#loadSchema(java.lang.String)
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
            net.gigs2go.tableserver.core.data.Column column = null;
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
     * @see net.gigs2go.tableserver.core.services.storage.DataService#addData(net.gigs2go.tableserver.core.data.jaxb.Schema, net.gigs2go.tableserver.core.data.jaxb.Data)
     */
    @Override
    public Result addData(Schema schema, Data data) throws StorageException {
        Result result = new Result();
        // First map schema for retrieval
        Map<String, Column> columns = new HashMap<String, Column>();
        for (Column column : schema.getColumns().getColumn()) {
            columns.put(column.getName(), column);
        }
        DB db = mongoTemplate.getDb();
        log.debug("Adding data to collection {}", data.getName());
        DBCollection dbCollection = db.getCollection(data.getName());
        // Map the data to the schema
        Map<String, Object> doc = new HashMap<String, Object>();
        for (Row row : data.getRows().getRow()) {
            for (ColumnValue value : row.getColumnvalue()) {
                Column column = columns.get(value.getName());
                ColumnDataType type = column.getDataType();
                doc.put(value.getName(), type.convert((String) value.getValue()));
            }
        }

        DBObject dbObject = new BasicDBObject(doc);
        dbCollection.save(dbObject);
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
    //    @Override
    //    public List<ChartItem> getChartItems(String collectionName, String dimension, String measure, String aggregation) {
    //        final List<ChartItem> result = new ArrayList<ChartItem>();
    //        log.debug("Getting data for collection {}", collectionName);
    //        DBCollection collection = mongoTemplate.getDb().getCollection(collectionName);
    //        DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$" + dimension).append(aggregation + measure, new BasicDBObject("$" + aggregation, "$" + measure)));
    //        DBObject sort = new BasicDBObject("$sort", new BasicDBObject("_id", 1));
    //        log.debug("Group : {}", group);
    //        AggregationOutput output = collection.aggregate(group, sort);
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
}
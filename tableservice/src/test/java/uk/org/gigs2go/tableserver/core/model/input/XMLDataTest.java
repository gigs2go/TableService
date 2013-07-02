/**
 * 
 */
package uk.org.gigs2go.tableserver.core.model.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uk.org.gigs2go.tableserver.core.data.Column;
import uk.org.gigs2go.tableserver.core.data.ColumnDataType;
import uk.org.gigs2go.tableserver.core.data.ColumnType;
import uk.org.gigs2go.tableserver.core.data.JaxbUtils;
import uk.org.gigs2go.tableserver.core.data.Row;
import uk.org.gigs2go.tableserver.core.data.Schema;
import uk.org.gigs2go.tableserver.core.data.SchemaType;
import uk.org.gigs2go.tableserver.core.data.Table;
import uk.org.gigs2go.tableserver.core.data.TransferException;
import uk.org.gigs2go.tableserver.core.data.Schema.Columns;
import uk.org.gigs2go.tableserver.core.data.Table.Rows;
import uk.org.gigs2go.tableserver.core.test.TestUtils;


/**
 * @author tim
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath*:config/repo.xml", "classpath*:config/mvc.xml" })
public class XMLDataTest extends AbstractJUnit4SpringContextTests {
    private Logger log = LoggerFactory.getLogger(XMLDataTest.class);

    @Autowired
    private JaxbUtils jaxbUtils;

    @Autowired
    private TestUtils testUtils;

    @Test
    public void testGetSchemaError1() {
        InputStream inputStream = testUtils.getInputStream("TestSchemaError.xml");
        assertNotNull(inputStream);
        Schema schema = null;
        try {
            schema = (Schema) jaxbUtils.getObject(inputStream, SchemaType.SCHEMA);
            fail("Should have thrown an exception");
        } catch (TransferException e) {
            ;
        }
        assertNull(schema);
    }

    @Test
    public void testGetSchema() {
        InputStream inputStream = testUtils.getInputStream("TestSchema.xml");
        assertNotNull(inputStream);
        Schema schema = null;
        try {
            schema = (Schema) jaxbUtils.getObject(inputStream, SchemaType.SCHEMA);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(schema);
        log.debug("{}", schema);
        Columns columns = schema.getColumns();
        assertNotNull(columns);
        List<Column> schemaColumns = columns.getColumn();
        assertNotNull(schemaColumns);
        assertEquals(13, schemaColumns.size());

        // Check first schema column
        Column column = schemaColumns.get(0);
        assertNotNull(column);
        assertEquals(ColumnDataType.STRING, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("String", column.getName());
        assertEquals(false, column.isIndexed());
        column = schemaColumns.get(1);
        assertNotNull(column);
        assertEquals(ColumnDataType.EMAIL, column.getDataType());
        assertEquals(ColumnType.TEXT, column.getType());
        assertEquals("Email", column.getName());
        assertEquals(true, column.isIndexed());
        column = schemaColumns.get(2);
        assertNotNull(column);
        assertEquals(ColumnDataType.URL, column.getDataType());
        assertEquals(ColumnType.TEXT, column.getType());
        assertEquals("Url", column.getName());
        column = schemaColumns.get(3);
        assertNotNull(column);
        assertEquals(ColumnDataType.INTEGER, column.getDataType());
        assertEquals(ColumnType.MEASURE, column.getType());
        assertEquals("Integer", column.getName());
        column = schemaColumns.get(4);
        assertNotNull(column);
        assertEquals(ColumnDataType.CURRENCY, column.getDataType());
        assertEquals(ColumnType.MEASURE, column.getType());
        assertEquals("Currency", column.getName());
        column = schemaColumns.get(5);
        assertNotNull(column);
        assertEquals(ColumnDataType.PERCENT, column.getDataType());
        assertEquals(ColumnType.MEASURE, column.getType());
        assertEquals("Percent", column.getName());
        column = schemaColumns.get(6);
        assertNotNull(column);
        assertEquals(ColumnDataType.DATE, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("Date", column.getName());
        column = schemaColumns.get(7);
        assertNotNull(column);
        assertEquals(ColumnDataType.DATETIME, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("DateTime", column.getName());
        column = schemaColumns.get(8);
        assertNotNull(column);
        assertEquals(ColumnDataType.TIMESTAMP, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("TimeStamp", column.getName());
        column = schemaColumns.get(9);
        assertNotNull(column);
        assertEquals(ColumnDataType.BOOLEAN, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("Boolean", column.getName());
        column = schemaColumns.get(10);
        assertNotNull(column);
        assertEquals(ColumnDataType.VERSION, column.getDataType());
        assertEquals(ColumnType.DIMENSION, column.getType());
        assertEquals("Version", column.getName());
        column = schemaColumns.get(11);
        assertNotNull(column);
        assertEquals(ColumnDataType.DECIMAL, column.getDataType());
        assertEquals(ColumnType.MEASURE, column.getType());
        assertEquals("Decimal", column.getName());
        column = schemaColumns.get(12);
        assertNotNull(column);
        assertEquals(ColumnDataType.DOUBLE, column.getDataType());
        assertEquals(ColumnType.MEASURE, column.getType());
        assertEquals("Double", column.getName());

    }

    @Test
    public void testGetDataError() {
        InputStream inputStream = testUtils.getInputStream("TestDataError.xml");
        assertNotNull(inputStream);
        Table table = null;
        try {
            table = (Table) jaxbUtils.getObject(inputStream, SchemaType.TABLE);
            fail("Should have thrown an exception");
        } catch (TransferException e) {
            ;
        }
        assertNull(table);
    }

    @Test
    public void testGetData() {
        InputStream inputStream = testUtils.getInputStream("TestData0.xml");
        assertNotNull(inputStream);
        Table table = null;
        try {
            table = (Table) jaxbUtils.getObject(inputStream, SchemaType.TABLE);
        } catch (TransferException e) {
            fail("Exception thrown");
        }
        assertNotNull(table);
        Rows rowHolder = table.getRows();
        assertNotNull(rowHolder);
        List<Row> rows = rowHolder.getRow();
        assertNotNull(rows);
        assertEquals(1, rows.size());

        //rows.get(0).

    }

}

package uk.org.gigs2go.tableserver.core.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Table", propOrder = { "schemaName", "name", "rows" })
@XmlRootElement(name = "table")
public class Table {

    @XmlElement(required = false)
    private String schemaName;

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    protected Table.Rows rows;

    /**
     * @return the schemaName
     */
    public String getSchemaName() {
        return this.schemaName;
    }

    /**
     * @param schemaName the schemaName to set
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the value of the rows property.
     * 
     * @return
     *     possible object is
     *     {@link Table.Rows }
     *     
     */
    public Table.Rows getRows() {
        return rows;
    }

    /**
     * Sets the value of the rows property.
     * 
     * @param value
     *     allowed object is
     *     {@link Table.Rows }
     *     
     */
    public void setRows(Table.Rows value) {
        this.rows = value;
    }

    /**
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "row" })
    public static class Rows {

        @XmlElement(required = true)
        protected List<Row> row;

        /**
         * Gets the value of the row property.
         * 
         */
        public List<Row> getRow() {
            if (row == null) {
                row = new ArrayList<Row>();
            }
            return this.row;
        }

    }

}

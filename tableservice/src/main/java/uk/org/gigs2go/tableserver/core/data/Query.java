/**
 * 
 */
package uk.org.gigs2go.tableserver.core.data;

/**
 * @author tim
 *
 */
public class Query {
    private String collectionName;
    private String dimension;
    private AggregationType aggregation;
    private String measure;

    /**
     * @return the collectionName
     */
    public String getCollectionName() {
        return this.collectionName;
    }

    /**
     * @param collectionName the collectionName to set
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return the dimension
     */
    public String getDimension() {
        return this.dimension;
    }

    /**
     * @param dimension the dimension to set
     */
    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    /**
     * @return the aggregation
     */
    public AggregationType getAggregation() {
        return this.aggregation;
    }

    /**
     * @param aggregation the aggregation to set
     */
    public void setAggregation(AggregationType aggregation) {
        this.aggregation = aggregation;
    }

    /**
     * @return the measure
     */
    public String getMeasure() {
        return this.measure;
    }

    /**
     * @param measure the measure to set
     */
    public void setMeasure(String measure) {
        this.measure = measure;
    }
}

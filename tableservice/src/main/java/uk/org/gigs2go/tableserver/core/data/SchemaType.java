/**
 * 
 */
package uk.org.gigs2go.tableserver.core.data;

/**
 * @author tim
 *
 */
public enum SchemaType {
    SCHEMA() {

        @Override
        public Class getTargetClass() {
            return Schema.class;
        }

        @Override
        public String toString() {
            return "schema";
        }
    },
    TABLE() {

        @Override
        public Class getTargetClass() {
            return Table.class;
        }

        @Override
        public String toString() {
            return "table";
        }
    },
    RESULT() {

        @Override
        public Class getTargetClass() {
            return Result.class;
        }

        @Override
        public String toString() {
            return "result";
        }
    };

    public abstract Class getTargetClass();
}

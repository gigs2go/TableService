/**
 * 
 */
package net.gigs2go.tableserver.core.data;

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
    DATA() {

        @Override
        public Class getTargetClass() {
            return Data.class;
        }

        @Override
        public String toString() {
            return "data";
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

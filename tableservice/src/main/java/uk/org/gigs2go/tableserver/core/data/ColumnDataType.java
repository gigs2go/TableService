//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.23 at 08:56:30 AM BST 
//

package uk.org.gigs2go.tableserver.core.data;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import org.joda.money.Money;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Java class for ColumnDataType.
 * 
 */
@XmlType(name = "ColumnDataType")
@XmlEnum
public enum ColumnDataType {
    STRING {
        @Override
        public String convert(String value) {
            return value;
        }
    },
    INTEGER {
        @Override
        public Integer convert(String value) {
            Integer result = null;
            try {
                result = Integer.parseInt(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    DECIMAL {
        @Override
        public Double convert(String value) {
            Double result = null;
            try {
                result = Double.parseDouble(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    DOUBLE {
        @Override
        public Double convert(String value) {
            Double result = null;
            try {
                result = Double.parseDouble(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    DATE {
        @Override
        public LocalDate convert(String value) {
            LocalDate result = null;
            try {
                result = LocalDate.parse(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    YEAR {
        @Override
        public LocalDate convert(String value) {
            LocalDate result = null;
            try {
                result = yearFormatter.parseLocalDate(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    DATETIME {
        @Override
        public LocalDateTime convert(String value) {
            LocalDateTime result = null;
            try {
                result = LocalDateTime.parse(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    EMAIL {
        @Override
        public String convert(String value) {
            return value;
        }
    },
    URL {
        @Override
        public String convert(String value) {
            return value;
        }
    },
    CURRENCY {
        @Override
        public Money convert(String value) {
            Money result = Money.parse(value);
            return result;
        }
    },
    PERCENT {
        @Override
        public BigDecimal convert(String value) {
            BigDecimal result = null;
            try {
                result = new BigDecimal(value);
            } catch (Exception e) {
                log.warn("Unable to convert {} to {}", value, this.name());
            }
            return result;
        }
    },
    TIMESTAMP {
        @Override
        public Long convert(String value) {
            return new Long(value);
        }
    },
    BOOLEAN {
        @Override
        public Boolean convert(String value) {
            return new Boolean(value);
        }
    },
    VERSION {
        @Override
        public String convert(String value) {
            return value;
        }
    };
    private static Logger log = LoggerFactory.getLogger(ColumnType.class);
    private static final DateTimeFormatter yearFormatter = DateTimeFormat.forPattern("YYYY");

    public abstract Object convert(String value);

    public String value() {
        return name();
    }

    public static ColumnDataType fromValue(String v) {
        return valueOf(v);
    }

}

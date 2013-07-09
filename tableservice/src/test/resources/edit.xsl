<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="row">
        <row>
            <xsl:for-each select="value">
                <columnvalue>
                    <name>
                        <xsl:choose>
                            <xsl:when test="position() = 1">
                                Code
                            </xsl:when>
                            <xsl:when test="position() = 2">
                                Name
                            </xsl:when>
                            <xsl:when test="position() = 3">
                                Continent
                            </xsl:when>
                            <xsl:when test="position() = 4">
                                Region
                            </xsl:when>
                            <xsl:when test="position() = 5">
                                SurfaceArea
                            </xsl:when>
                            <xsl:when test="position() = 6">
                                IndepYear
                            </xsl:when>
                            <xsl:when test="position() = 7">
                                Population
                            </xsl:when>
                            <xsl:when test="position() = 8">
                                LifeExpectancy
                            </xsl:when>
                            <xsl:when test="position() = 9">
                                GNP
                            </xsl:when>
                            <xsl:when test="position() = 10">
                                GNPOld
                            </xsl:when>
                            <xsl:when test="position() = 11">
                                LocalName
                            </xsl:when>
                            <xsl:when test="position() = 12">
                                GovernmentForm
                            </xsl:when>
                            <xsl:when test="position() = 13">
                                HeadOfState
                            </xsl:when>
                            <xsl:when test="position() = 14">
                                Capital
                            </xsl:when>
                            <xsl:when test="position() = 15">
                                Code2
                            </xsl:when>
                        </xsl:choose>
                    </name>
                    <value>
                        <!-- xsl:number value="position()" format="1"/ -->
                        <xsl:value-of select="."/>
                    </value>
                </columnvalue>
            </xsl:for-each>
        </row>
    </xsl:template>
    <!-- standard copy template -->
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>
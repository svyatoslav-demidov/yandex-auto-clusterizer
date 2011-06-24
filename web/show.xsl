<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" indent="yes" encoding="utf-8"/>

    <xsl:template match="/">
        <html>
            <style type="text/css">
                    h2          { padding: 10px; width : 400px; padding-width: 100%; background-color: orange ; border-radius: 10px}
                    hr          {padding: 1px;background-color: silver; padding-width: 100%; border-radius: 7px;}
                    table       {padding: 10px; background-color: silver; border-radius: 10px}
                    BODY        {background: silver; color: black;}
            </style>

            <head>
                <title>
                    <xsl:text>Тестовое задание</xsl:text>
                </title>
            </head>
            <body>
                <form method="get" action="/main.xml">
                    <input type="submit" value="Назад"/>
                </form>
                <hr/>
                <xsl:apply-templates select="page/data/answer"/>
                <xsl:apply-templates select="page/data/car"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="answer">
            <h3><xsl:value-of select="ans"/></h3>
            <hr/>
     </xsl:template>

    <xsl:template match="car">
        <h2>Mодель: <xsl:value-of select="model"/></h2>
        <!--<image src = "{picture}"></image> -->
                    <br></br>

            <h3>Описание: </h3>
            <h4>Год выпуска: <xsl:value-of select="year"/></h4>
            <h4>Цена: <xsl:value-of select="price"/></h4>
            <h4><xsl:value-of select="auction"/></h4>
            <h4><xsl:value-of select="description"/></h4>
            <a href="{link}" target = "_blank">Исходное объявление</a>

            <form method="get" action="/clusters.xml">
                <input type="hidden" name="cluster-id"  value="{cluster-id}"/>
                <input type="submit" value="Просмотреть кластер: {cluster-id}"/>
            </form>
        <!--<p>Кластер: <xsl:value-of select="cluster-id"/></p>-->
        <hr/>
    </xsl:template>


</xsl:stylesheet>
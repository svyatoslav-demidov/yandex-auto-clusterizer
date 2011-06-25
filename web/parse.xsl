<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" indent="yes" encoding="utf-8"/>

    <xsl:template match="/">
        <html>
            <style type="text/css">
                h2 { padding: 10px; width : 400px; padding-width: 100%; background-color: orange ; border-radius: 10px}
                hr {padding: 1px;background-color: silver; padding-width: 100%; border-radius: 7px;}
                table {padding: 10px; background-color: silver; border-radius: 10px}
                BODY {background: silver; color: black;}
            </style>

            <head>
                <title>
                    <xsl:text>Сбор данных</xsl:text>
                </title>
            </head>
            <body>
                <form method="get" action="main.xml">
                    <input type="submit" value="Назад"/>
                </form>
                <form method="get" action="/show.xml">
                <input type="submit" value="Просмотреть все объявления"/>
                </form>
                <form method="get" action="/show.xml">
                    <input type="hidden" name="query-type" value="unique"/>
                    <input type="submit" value="Просмотреть уникальные объявления"/>
                </form>
                <form method="get" action="/show.xml">
                    <input type="hidden" name="query-type" value="copies"/>
                    <input type="submit" value="Просмотреть копии"/>
                 </form>
                 <hr/>
                <xsl:apply-templates select="page/data/answer"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="answer">
        <h3>
            <xsl:value-of select="ans"/>
        </h3>
        <hr/>
    </xsl:template>


</xsl:stylesheet>
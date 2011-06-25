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
                    <xsl:text>Тестовое задание</xsl:text>
                </title>
            </head>
            <body>
                <form method="get" action="/parse.xml">
                    <input type="text" name="query" size="20"/>
                    <input type="text" name="loops" size="5" value="5"/>
                    <input type="submit" value="Добавить запрос в базу данных"/>
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
                <xsl:apply-templates select="page/data/car"/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="answer">

        <h3>Всего объявлений:
            <xsl:value-of select="number"/>
        </h3>
        <hr/>
    </xsl:template>

    <xsl:template match="car">
        <h2>Mодель:
            <xsl:value-of select="model"/>
        </h2>
        <image src="{picture}"></image>
        <br></br>

        <h3>Описание:</h3>
        <h4>Год выпуска:
            <xsl:value-of select="year"/>
        </h4>
        <h4>Цена:
            <xsl:value-of select="price"/>
        </h4>
        <h4>
            <xsl:value-of select="auction"/>
        </h4>
        <h4>
            <xsl:value-of select="description"/>
        </h4>
        <a href="{link}" target="_blank">Исходное объявление</a>


        <p>Кластер:
            <xsl:value-of select="cluster-id"/>
        </p>
        <hr/>
    </xsl:template>


</xsl:stylesheet>
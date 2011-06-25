package ru.yandex.auto.clusterizer.util;

import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 17.06.11
 * @Description: Класс-обертка над web-harvest, собирающий объявления с Яедекс.авто
 */
public class Parser {
    private ScraperConfiguration scraperConfiguration;
    private Scraper scraper;

    public Parser(String configFileName, String workingDir) {
        try {
            scraperConfiguration = new ScraperConfiguration(configFileName);
            scraper = new Scraper(scraperConfiguration, workingDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(String query, int loops) {
        try {
            scraper.addVariableToContext("query", query);
            scraper.addVariableToContext("loops", loops);
            scraper.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

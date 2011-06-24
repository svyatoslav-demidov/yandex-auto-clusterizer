package ru.yandex.auto.clusterizer.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.db.AbstractDbYalet;
import ru.yandex.auto.clusterizer.datastore.DataStore;
import ru.yandex.auto.clusterizer.model.Car;
import ru.yandex.auto.clusterizer.util.DataLoader;
import ru.yandex.auto.clusterizer.util.Parser;

import java.util.List;

import static ru.yandex.auto.clusterizer.util.Clusterizer.makeClusters;
import static ru.yandex.auto.clusterizer.util.ViewProcessor.prepareAnswer;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 21.06.11
 */
public class ParserYalet extends AbstractDbYalet{
    public void process(InternalRequest req, InternalResponse res) {
        int loops = req.getIntParameter("loops", 0);
        String query = req.getParameter("query");
        if (loops == 0 || query == null || query.length() == 0) {
            res.add(prepareAnswer("Ошибочный запрос"));
            return;
        }
        //Parser parser = new Parser("resources/wh-config-template.xml", "resources/");
        //parser.parse(query, loops);
        List<Car> carsAds = new DataLoader("resources/cars-ad.xml").getListOfCarsAds();
        carsAds = makeClusters(carsAds);
        DataStore dataStore = new DataStore(jdbcTemplate);
        dataStore.cleanDataStore();
        dataStore.writeListOfCarsToDataStore(carsAds);
        res.add(prepareAnswer("Загружено " + carsAds.size() + " объявлений по запросу '" + query + "'."));
    }
}

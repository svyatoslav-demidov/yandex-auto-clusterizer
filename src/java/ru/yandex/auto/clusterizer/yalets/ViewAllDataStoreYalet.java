package ru.yandex.auto.clusterizer.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.db.AbstractDbYalet;
import ru.yandex.auto.clusterizer.datastore.DataStore;
import ru.yandex.auto.clusterizer.model.Car;

import java.util.HashSet;
import java.util.List;

import static ru.yandex.auto.clusterizer.util.ViewProcessor.convertCarToTag;
import static ru.yandex.auto.clusterizer.util.ViewProcessor.prepareAnswer;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 22.06.11
 */
public class ViewAllDataStoreYalet extends AbstractDbYalet {

    @Override
    public void process(InternalRequest req, InternalResponse res) {
        DataStore dataStore = new DataStore(jdbcTemplate);
        String queryType = req.getParameter("query-type");

        List<Car> cars = dataStore.getAllCarsFromDataStore();
        res.add(prepareAnswer("Всего объявлений в базе: " + Integer.toString(cars.size())));
        HashSet<Integer> clusters = new HashSet<Integer>();
        for (Car car : cars) {
            clusters.add(car.getClusterId());
        }
        res.add(prepareAnswer("Выделено кластеров: " + Integer.toString(clusters.size()) + "; Найдено копий:" +
                Integer.toString(cars.size() - clusters.size())));
        int lastCluster = -1;
        for (Car car : cars) {
            if ("unique".equals(queryType)) {
                if (lastCluster != car.getClusterId()) res.add(convertCarToTag(car));
            } else if ("copies".equals(queryType)) {
                if (lastCluster == car.getClusterId() && clusters.contains(car.getClusterId())){
                    res.add(convertCarToTag(car));
                    clusters.remove(car.getClusterId());
                    lastCluster = car.getClusterId();
                }

            } else {
                res.add(convertCarToTag(car));
            }
            lastCluster = car.getClusterId();
        }
    }
}

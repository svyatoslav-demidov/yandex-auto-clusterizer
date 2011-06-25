package ru.yandex.auto.clusterizer.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.db.AbstractDbYalet;
import ru.yandex.auto.clusterizer.datastore.DataStore;
import ru.yandex.auto.clusterizer.model.Car;

import java.util.List;

import static ru.yandex.auto.clusterizer.util.ViewProcessor.convertCarToTag;
import static ru.yandex.auto.clusterizer.util.ViewProcessor.prepareAnswer;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 22.06.11
 * @Description: Ялет, отвечающий за вывод кластеров
 */
public class ViewClustersYalet extends AbstractDbYalet {

    public void process(InternalRequest req, InternalResponse res) {
        int clusterId = req.getIntParameter("cluster-id", -1);
        if (clusterId == -1) {
            res.add(prepareAnswer("Ошибочный запрос"));
        }
        DataStore dataStore = new DataStore(jdbcTemplate);
        List<Car> cars = dataStore.getClusterByIdFromDataStore(clusterId);
        res.add(prepareAnswer("Кластер (id = " + Integer.toString(clusterId) +
                ") содержит " + Integer.toString(cars.size()) + " копий"));
        for (Car car : cars) {
            res.add(convertCarToTag(car));
        }
    }
}

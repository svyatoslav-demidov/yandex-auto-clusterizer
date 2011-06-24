package ru.yandex.auto.clusterizer.util;

import ru.yandex.auto.clusterizer.model.Car;

import java.util.List;

import static ru.yandex.auto.clusterizer.util.Metrics.getDistance;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 21.06.11
 */
public class Clusterizer {
    static final double BORDER = 0.2;

    public static List<Car> makeClusters(List<Car> data) {
        DisjointSets disjointSets = new DisjointSets(data.size());
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = i + 1; j < data.size(); j++) {
                if (getDistance(data.get(i), data.get(j)) <= BORDER) {
                    disjointSets.unite(i, j);
                }
            }
        }
        for (int i = 0; i < data.size(); i++){
            data.get(i).setClusterId(disjointSets.get_root(i));
        }
        return data;
    }

}

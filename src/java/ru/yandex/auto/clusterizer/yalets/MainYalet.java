package ru.yandex.auto.clusterizer.yalets;

import net.sf.xfresh.core.InternalRequest;
import net.sf.xfresh.core.InternalResponse;
import net.sf.xfresh.core.xml.Xmler;
import net.sf.xfresh.db.AbstractDbYalet;
import ru.yandex.auto.clusterizer.model.Car;
import ru.yandex.auto.clusterizer.util.DataLoader;
import ru.yandex.auto.clusterizer.util.Parser;

import java.util.Arrays;
import java.util.List;

import static ru.yandex.auto.clusterizer.util.ViewProcessor.convertCarToTag;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 11.06.11
 */
public class MainYalet extends AbstractDbYalet {

    public void process(InternalRequest req, InternalResponse res) {
        //new Parser("resources/wh-config-template.xml", "resources/").parse("Renault Logan", 3);
        //List<Car> ar = new DataLoader("resources/cars-ad.xml").getListOfCarsAds();
        //res.add(Xmler.tag("answer", Arrays.asList(Xmler.tag("number", Integer.toString(ar.size())))));
        //for (Car car : ar) {
        //    res.add(convertCarToTag(car));
        //}
    }
}

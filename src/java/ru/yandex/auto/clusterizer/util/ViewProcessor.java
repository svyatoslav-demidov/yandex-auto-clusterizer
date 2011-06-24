package ru.yandex.auto.clusterizer.util;

import net.sf.xfresh.core.xml.Xmler;
import ru.yandex.auto.clusterizer.model.Car;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 19.06.11
 * @Description: Класс, содержащий методы для представления информации в виде тэгов
 */
public class ViewProcessor {


    public static Xmler.Tag convertCarToTag(Car car) {
        return Xmler.tag("car", Arrays.asList(Xmler.tag("model", car.getModel()), Xmler.tag("year", car.getYear()),
                Xmler.tag("price", car.getPrice()), Xmler.tag("auction", car.getAuction()), Xmler.tag("description", car.getDescription()),
                Xmler.tag("link", car.getLink()),
                Xmler.tag("picture", car.getPicture()), Xmler.tag("cluster-id", Integer.toString(car.getClusterId()))));
    }

    public static Xmler.Tag prepareAnswer(String answer) {
        return Xmler.tag(Xmler.tag("answer", Arrays.asList(Xmler.tag("ans", answer))));
    }


}

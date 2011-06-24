package ru.yandex.auto.clusterizer.datastore;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import ru.yandex.auto.clusterizer.model.Car;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 17.06.11
 * @Description: Класс, отвечающий за работу с базой данных
 */
public class DataStore {
    private SimpleJdbcTemplate jdbcTemplate;
    private CarsMapper carsMapper;

    public DataStore(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        carsMapper = new CarsMapper();
    }

    public void cleanDataStore() {
        jdbcTemplate.update("TRUNCATE TABLE cars_ads");
    }

    public void writeCarToDataStore(Car car) {
        jdbcTemplate.update("INSERT INTO cars_ads (model, price, auction, year, description," +
                " picture, link, cluster_id) VALUES(?, ?, ?, ?, ?, ?, ?, ?);", car.getModel(), car.getPrice(), car.getAuction(),
                car.getYear(), car.getDescription(), car.getPicture(), car.getLink(), car.getClusterId());
    }

    public void writeListOfCarsToDataStore(List<Car> cars) {
        for (Car car : cars) {
            writeCarToDataStore(car);
        }
    }

    public List<Car> getAllCarsFromDataStore() {
        return jdbcTemplate.getJdbcOperations().query("select * from cars_ads order by cluster_id", carsMapper);
    }

    public List<Car> getClusterByIdFromDataStore(int id) {
        return jdbcTemplate.getJdbcOperations().query("select * from cars_ads where cluster_id = '"
                + Integer.toString(id) + "' order by price", carsMapper);
    }


}

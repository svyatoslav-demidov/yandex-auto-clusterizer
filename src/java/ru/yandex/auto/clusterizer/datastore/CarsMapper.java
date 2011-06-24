package ru.yandex.auto.clusterizer.datastore;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import ru.yandex.auto.clusterizer.model.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 22.06.11
 */

public class CarsMapper implements ParameterizedRowMapper<Car> {

    public Car mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Car(resultSet.getString("model"), resultSet.getString("price"), resultSet.getString("auction"),
                resultSet.getString("year"), resultSet.getString("description"), resultSet.getString("picture"),
                resultSet.getString("link"), resultSet.getInt("cluster_id"));
    }
}

package ru.yandex.auto.clusterizer.model;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 17.06.11
 * @Description: Класс, отвечающий за представление объявления о продаже автомобиля
 * model : название модели автомобиля
 * price : цена автомобиля
 * year : год выпуска
 * description : описание
 * picture : ссылка на изображение автомобиля
 * link : ссылка на исходное объявление о продаже автомобиля
 * clusterId : идентификатор кластера, к которому принадлежит данное объяевление
 */
public class Car {
    private String model;
    private String price;
    private String year;
    private String description;
    private String picture;
    private String link;
    private int clusterId;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public Car(String model, String price, String year, String description, String picture, String link, int clusterId) {

        this.model = model;
        this.price = price;
        this.year = year;
        this.description = description;
        this.picture = picture;
        this.link = link;
        this.clusterId = clusterId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", link='" + link + '\'' +
                ", clusterId=" + clusterId +
                '}';
    }
}

package ru.yandex.auto.clusterizer.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import ru.yandex.auto.clusterizer.model.Car;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 19.06.11
 * @Description: Класс, отвечающий за подгрузку данных из .xml файла в память
 */
public class DataLoader {
    private Document sourceXML;

    public DataLoader(String fileName) {
        File sourceFile = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            sourceXML = documentBuilder.parse(sourceFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Car> getListOfCarsAds() {
        NodeList root = sourceXML.getElementsByTagName("car");
        ArrayList<Car> result = new ArrayList<Car>();
        for (int i = 0; i < root.getLength(); i++) {
            String model = "", price = "", auction = "", year = "", description = "", link = "", picture = "";
            NodeList currentTag = root.item(i).getChildNodes();
            for (int j = 0; j < currentTag.getLength(); j++) {
                String nodeName = currentTag.item(j).getNodeName();
                if ("model".equals(nodeName)) {
                    model = currentTag.item(j).getTextContent();
                }
                if ("price".equals(nodeName)) {
                    price = currentTag.item(j).getTextContent();
                }

                if ("auction".equals(nodeName)) {
                    auction = currentTag.item(j).getTextContent();
                }

                if ("year".equals(nodeName)) {
                    year = currentTag.item(j).getTextContent();
                }
                if ("description".equals(nodeName)) {
                    description = currentTag.item(j).getTextContent();
                }

                if ("link".equals(nodeName)) {
                    link = currentTag.item(j).getTextContent();
                }
                if ("picture".equals(nodeName)) {
                    picture = currentTag.item(j).getTextContent();
                }

            }
            result.add(new Car(model, price, auction, year, description, picture, link, 0));
        }
        return result;
    }

}

package ru.yandex.auto.clusterizer.util;

import ru.yandex.auto.clusterizer.model.Car;

import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by IntelliJ IDEA.
 *
 * @user: svyd
 * @date: 20.06.11
 * @Description: Класс, определяющий метрику
 */
public class Metrics {

    private static String extractColorFromDescription(String description) {
        String result = "";
        if (description.indexOf("цвет") >= 0)
            result = description.substring(description.indexOf("цвет") + "цвет ".length(),
                    description.indexOf(",", description.indexOf("цвет")));
        return result.trim();
    }

    private static String deleteSubstring(String str, String substr) {
        if (str.indexOf(substr) < 0) {
            return str;
        }
        int start = str.indexOf(substr);
        int end = start + substr.length();
        return new StringBuffer(str).delete(start, end).toString();
    }

    private static String extractConditionFromDescription(String description) {
        String result = "";
        if (description.indexOf("сост.") >= 0) {
            int end = description.indexOf("сост.") - 1;
            int start = 0;
            for (int i = end; i >= 0; i--) {
                if (description.charAt(i) == ',') {
                    start = i + 1;
                    break;
                }
            }
            result = description.substring(start, end);
        }

        return result.trim();
    }


    private static String extractMileageFromDescription(String description) {
        int end = description.indexOf(" км");
        if (end < 0) {
            return "";
        }
        int start = 0;
        for (int i = end - 1; i >= 0; i--) {
            if (description.charAt(i) == ' ') {
                start = i + 1;
                break;
            }
        }
        return description.substring(start, end);
    }


    private static int extractYear(String year) {
        if (year.length() < 3) {
            return 0;
        }
        return Integer.parseInt(year.substring(0, 4));
    }

    private static double getValueOfCondition(String condition) {
        if ("среднее".equals(condition)) {
            return 1.0;
        }
        if ("хорошее".equals(condition)) {
            return 2.0;
        }
        if ("отличное".equals(condition)) {
            return 3.0;
        }
        return 0.0;
    }

    private static double getDistanceByCondition(String conditionA, String conditionB) {
        double valueA = getValueOfCondition(conditionA);
        double valueB = getValueOfCondition(conditionB);
        if (valueA == valueB) {
            return 0.0;
        }
        return (valueA * valueB == 0 ? 2.0 / 3.0 : Math.abs(valueA - valueB) / 2.0);
    }

    private static double getDistanceByColor(String colorA, String colorB) {
        if (colorA.equals(colorB)) {
            return 0.0;
        }
        if (colorA.length() == 0 || colorB.length() == 0) {
            return 1.0 - 1.0 / 7.0;
        }
        return 1;
    }

    private static double getDistanceByYear(int yearA, int yearB) {
        if (yearA == yearB) {
            return 0.0;
        }
        if (yearA == 0 || yearB == 0) {
            return 0.3;
        }
        return (Math.abs(yearA - yearB) <= 1 ? 0.05 : 1.0);
    }

    private static int extractPrice(String price) {
        String resultPrice = "0";
        for (int i = 0; i < price.length(); i++) {
            if (price.charAt(i) >= '0' && price.charAt(i) <= '9') {
                resultPrice += price.charAt(i);
            }
        }
        return Integer.parseInt(resultPrice);
    }

    private static double getDistanceByPrice(int priceA, int priceB) {
        if (priceA == priceB) {
            return 0.0;
        }
        if (priceA == 0 || priceB == 0) {
            return 0.3;
        }
        if (((double) Math.abs(priceA - priceB)) / Math.max(priceA, priceB) <= 0.1) {
            return 0.1;
        }
        return 1.0;
    }

    private static double getDistanceByMileage(String mileageA, String mileageB) {
        if (mileageA.equals(mileageB)) {
            return 0.0;
        }
        if (mileageA.length() == 0 || mileageB.length() == 0) {
            return 0.2;
        }
        int valueA = Integer.parseInt(mileageA);
        int valueB = Integer.parseInt(mileageB);
        if (valueA < 1000) {
            valueA *= 1000;
        }
        if (valueB < 1000) {
            valueB *= 1000;
        }
        if (((double) Math.abs(valueA - valueB)) / Math.max(valueA, valueB) < 0.8) {
            return 0.2;
        }
        return 1.0;
    }

    private static String extractBodytypeFromDescription(String description) {
        if (description.indexOf("седан") >= 0) {
            return "седан";
        }
        if (description.indexOf("универсал") >= 0) {
            return "универсал";
        }
        if (description.indexOf("минивэн") >= 0) {
            return "минивэн";
        }
        if (description.indexOf("купе") >= 0) {
            return "купе";
        }
        if (description.indexOf("пикап") >= 0) {
            return "пикап";
        }
        if (description.indexOf("хетчбек") >= 0) {
            return "хетчбек";
        }
        if (description.indexOf("вседорожник") >= 0) {
            return "вседорожник";
        }
        if (description.indexOf("кроссовер") >= 0) {
            return "кроссовер";
        }
        if (description.indexOf("кабриолет") >= 0) {
            return "кабриолет";
        }
        if (description.indexOf("фургон") >= 0) {
            return "фургон";
        }
        return "";
    }

    private static double getDistanceByBodytype(String bodytepeA, String bodytypeB) {
        if (bodytepeA.equals(bodytypeB)) {
            return 0.0;
        }
        if (bodytepeA.length() == 0 || bodytypeB.length() == 0) {
            return 0.6;
        }
        return 1.0;
    }

    private static double getDistanceByDescription(String descriptionA, String descriptionB) {
        if (descriptionA.equals(descriptionB)) {
            return 0.0;
        }
        StringTokenizer stA = new StringTokenizer(descriptionA, "\n ,-!@#$%^&*()/\t");
        HashSet<String> hsA = new HashSet<String>();
        while (stA.hasMoreTokens()) {
            String word = stA.nextToken();
            if (word.length() >= 2) {
                hsA.add(word);
            }
        }

        StringTokenizer stB = new StringTokenizer(descriptionB, "\n ,-!@#$%^&*()/\t");
        HashSet<String> hsB = new HashSet<String>();
        int duplicates = 0;
        while (stB.hasMoreTokens()) {
            String word = stB.nextToken();
            if (word.length() >= 2) {
                hsB.add(word);
                if (hsA.contains(word)) {
                    duplicates++;
                }
            }
        }


        return 1.0 - ((double) duplicates) / ((double) (Math.max(hsA.size(), hsB.size())));
    }


    public static double getDistance(Car carA, Car carB) {
        String descriptionA = carA.getDescription().toLowerCase();
        String descriptionB = carB.getDescription().toLowerCase();
        //Extract condition
        String carACondition = extractConditionFromDescription(descriptionA);
        String carBCondition = extractConditionFromDescription(descriptionB);
        descriptionA = deleteSubstring(descriptionA, carACondition);
        descriptionA = deleteSubstring(descriptionA, "сост.");
        descriptionB = deleteSubstring(descriptionB, carBCondition);
        descriptionB = deleteSubstring(descriptionB, "сост.");
        double distanceByCondition = getDistanceByCondition(carACondition, carACondition);
        //Extract color
        String carAColor = extractColorFromDescription(descriptionA);
        String carBColor = extractColorFromDescription(descriptionB);
        descriptionA = deleteSubstring(descriptionA, carAColor);
        descriptionA = deleteSubstring(descriptionA, "цвет");
        descriptionB = deleteSubstring(descriptionB, carBColor);
        descriptionB = deleteSubstring(descriptionB, "цвет");
        double distanceByColor = getDistanceByColor(carAColor, carBColor);
        //Extract year
        int carAYear = extractYear(carA.getYear());
        int carBYear = extractYear(carB.getYear());
        double distanceByYear = getDistanceByYear(carAYear, carAYear);
        //Extract price
        int priceA = extractPrice(carA.getPrice());
        int priceB = extractPrice(carB.getPrice());
        double distanceByPrice = getDistanceByPrice(priceA, priceB);
        //Extract mileage
        String mileageA = extractMileageFromDescription(descriptionA);
        String mileageB = extractMileageFromDescription(descriptionB);
        descriptionA = deleteSubstring(descriptionA, mileageA + " км");
        descriptionB = deleteSubstring(descriptionB, mileageB + " км");
        double distanceByMileage = getDistanceByMileage(mileageA, mileageB);
        //Extract bodytype
        String bodytypeA = extractBodytypeFromDescription(descriptionA);
        String bodytypeB = extractBodytypeFromDescription(descriptionB);
        double distanceByBodytype = getDistanceByBodytype(bodytypeA, bodytypeB);

        //Compare descriptions
        double distanceByDescription = getDistanceByDescription(descriptionA, descriptionB);

        //prepearing distance
        double distance = distanceByColor*1.1 + distanceByBodytype*1.1 + distanceByPrice*1.1 +
                +distanceByMileage*1.1 + distanceByYear*1.1 + distanceByCondition +
                +distanceByDescription;

        return (distance);
    }

}

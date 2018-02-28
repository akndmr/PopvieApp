package io.github.akndmr.popvieapp.utils;

import java.util.ArrayList;
import java.util.List;

import io.github.akndmr.popvieapp.model.Genre;
import io.github.akndmr.popvieapp.model.ProductionCompany;
import io.github.akndmr.popvieapp.model.ProductionCountry;
import io.github.akndmr.popvieapp.model.SpokenLanguage;

/**
 * Created by AKIN on 25.02.2018.
 */

public class ConvertUtil {


    public static String listToString(List<?> list) {
        List<String> convertedList = new ArrayList<>();
        for (Object j : list) {
            if (j instanceof Genre) {
                convertedList.add(((Genre) j).getName());
            }
            if (j instanceof ProductionCompany) {
                convertedList.add(((ProductionCompany) j).getName());
            }
            if (j instanceof ProductionCountry) {
                convertedList.add(((ProductionCountry) j).getName());
            }
            if (j instanceof SpokenLanguage) {
                convertedList.add(((SpokenLanguage) j).getName());
            }
        }
        return convert(convertedList);
    }

    private static String convert(List<String> list) {
        String genres = "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                //genres += list.get(i);
                stringBuilder.append(list.get(i));
            } else {
                // genres += list.get(i) + " | ";
                stringBuilder.append(list.get(i));
                stringBuilder.append(" | ");
            }
        }
        return stringBuilder.toString();
    }

}

package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        List<String> listText = getWords(text);
        return listText.stream().reduce(0, (sum, s) -> sum += s.length(), Integer::sum);
    }

    @Override
    public int countNumberOfWords(String text) {
        return  (int)getWords(text).stream().count();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return  getUniqueWords(text).size();
    }

    @Override
    public List<String> getWords(String text) {
        String[] string = text.split("\\W+");
        return Arrays.stream(string).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        Set<String> set = getUniqueWords(text);
        List<String> list = getWords(text);
        Map<String, Integer> map = new HashMap<>();
        set.forEach(word -> {
            long count = list.stream().filter(word::equals).count();
            map.put(word, (int) count);
        });
        return map;
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);
        if (direction.equals(Direction.ASC)) {
            list.sort(Comparator.comparingInt(String::length));
        } else {
            list.sort(Comparator.comparingInt(String::length).reversed());
        }
        return list;
    }
}

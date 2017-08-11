package com.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class PeriodsValidation {
    private static final String startPeriod = "НачПериода";
    private static final String endPeriod = "КонПериода";

    public String findIntersections(String input) {
        List<String> periodList = Arrays.asList(input.split(",")).stream().map(String::trim).collect(toList());
        String checkInfinity = checkInfinity(periodList);

        if(checkInfinity != null)
            return checkInfinity;
        List<String> intersections = new ArrayList<>(periodList.size());
        int isOpened = 0;
        int isIntersection = 0;

        for(String period : periodList){
            if(period.contains(startPeriod)){
                if(isOpened > 0) {
                    intersections.add(period);
                    isIntersection++;
                }
                isOpened++;
            } else {
                if (period.contains(endPeriod)){
                    if(isIntersection > 0) {
                        intersections.add(period);
                        isIntersection--;
                    }
                    isOpened--;
                }
            }
        }

        if(intersections.isEmpty())
            return "Пересечения нет";
        else
            return intersections.stream().collect(Collectors.joining(", "));
    }

    private String checkInfinity(List<String> periodList) {
        Map<Boolean, Long> groupedBeginEndMap = periodList.stream()
                .filter(el -> el.contains(startPeriod) || el.contains(endPeriod))
                .collect(Collectors.groupingBy(str -> str.contains(startPeriod), Collectors.counting()));
        String isInfinite = null;
        Long beginCount = groupedBeginEndMap.get(Boolean.TRUE) == null? 0L: groupedBeginEndMap.get(Boolean.TRUE);
        Long endCount = groupedBeginEndMap.get(Boolean.FALSE) == null? 0L: groupedBeginEndMap.get(Boolean.FALSE);
        if(beginCount > endCount) {
            isInfinite = "+бесконечность";
        } else if(beginCount < endCount) {
            isInfinite = "-бесконечность";
        }

        return isInfinite;
    }
}

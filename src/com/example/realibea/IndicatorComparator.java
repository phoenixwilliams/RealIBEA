package com.example.realibea;

import java.util.Comparator;

public class IndicatorComparator implements Comparator<Solution> {
    @Override
    public int compare(Solution o1, Solution o2) {
        return o1.getIndicatorFitness().compareTo(o2.getIndicatorFitness());
    }
}

package com.example.realibea;

import java.util.Comparator;

public class FitnessComparator implements Comparator<Solution> {

    int objective;

    public FitnessComparator(int o)
    {
        this.objective = o;
    }

    @Override
    public int compare(Solution o1, Solution o2) {
        return o1.getFitness(this.objective).compareTo(o2.getFitness(this.objective));
    }
}

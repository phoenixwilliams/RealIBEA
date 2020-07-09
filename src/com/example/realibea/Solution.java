package com.example.realibea;

import java.util.ArrayList;

public class Solution {

    private ArrayList<Double> fitness;
    private ArrayList<Double> normalizedFitness;
    private Double indicatorValue;
    private ArrayList<Double> genotype;

    public Solution(ArrayList<Double> genotype)
    {
        this.genotype = genotype;

    }

    public ArrayList<Double> getGenotype(){return this.fitness;}
    public void setFitness(ArrayList<Double> fitness_){this.fitness=fitness_;}
    public void setNormalizedFitness(ArrayList<Double> fitness_){this.normalizedFitness=fitness_;}
    public void setNormalizedFitness(int i, Double f){this.normalizedFitness.set(i,f);}

    public Double getFitness(int i){return this.fitness.get(i);}
    public void initializeNormalizedFitness(int objectiveNume)
    {
        for (int i=0;i<objectiveNume;i++)
        {
            this.normalizedFitness.add(0.0);
        }
    }
}

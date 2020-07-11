package com.example.realibea;

import java.util.ArrayList;

public class Solution {

    private ArrayList<Double> fitness;
    private ArrayList<Double> normalizedFitness;
    private ArrayList<Double> indicatorValues;
    private Double indicatorFitness;
    private ArrayList<Double> genotype;
    private Double c;

    public Solution(ArrayList<Double> genotype_)
    {
        this.genotype = genotype_;

    }

    public ArrayList<Double> getGenotype(){return this.genotype;}
    public void setFitness(ArrayList<Double> fitness_){this.fitness=fitness_;}
    public void setNormalizedFitness(int i, Double f){this.normalizedFitness.set(i,f);}
    public void setIndicatorValues(ArrayList<Double> iv){this.indicatorValues = iv;}
    public void setIndicatorFitness(Double iF){this.indicatorFitness = iF;}

    public void appendIndicatorFitness(Double iF) { this.indicatorFitness += iF; }

    public ArrayList<Double> getIndicatorValues(){return this.indicatorValues;}
    public Double getFitness(int i){return this.fitness.get(i);}
    public Double getIndicatorFitness(){return  this.indicatorFitness;}
    public ArrayList<Double> getFitness(){return this.fitness;}
    public ArrayList<Double> getNormalizedFitness(){return this.normalizedFitness;}
    public void initializeNormalizedFitness(int objectiveNum)
    {
        this.normalizedFitness = new ArrayList<>();
        for (int i=0;i<objectiveNum;i++)
        {
            this.normalizedFitness.add(0.0);
        }
    }
}

package com.example.realibea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class PopulationUtils {

    public static Solution InitialSolution(int variableNum)
    {
        ArrayList<Double> genotype = new ArrayList<>();
        Random random = new Random();

        for (int i=0; i<variableNum; i++)
        {
            genotype.add(random.nextDouble());
        }

        return new Solution(genotype);
    }

    public static ArrayList<Solution> InitialPopulation(int variableNum, int popSize)
    {
        ArrayList<Solution> population = new ArrayList<>();
        for (int i=0; i<popSize; i++)
        {
            population.add(InitialSolution(variableNum));
        }
        return population;
    }

    public static void EvaluatePopulationZDT1(ArrayList<Solution> population)
    {
        for (Solution sol:population)
        {
            sol.setFitness(ZDTProblem.ZDT1(sol.getGenotype()));
        }
    }

    public static void NormalizePopulationFitness(ArrayList<Solution> population, int objectiveNum)
    {
        Double bmin,bmax, fitnessNorm;

        //empty normalized solutions
        for (Solution sol: population)
        {
            sol.initializeNormalizedFitness(objectiveNum);
        }

        for (int i=0;i<objectiveNum;i++)
        {
            Collections.sort(population, new FitnessComparator(i));

            bmin = population.get(0).getFitness(i);
            population.get(0).setNormalizedFitness(i, 0.0);
            bmax = population.get(population.size()-1).getFitness(i);
            population.get(population.size()-1).setNormalizedFitness(i, 1.0);

            for (int j=1;j<population.size()-1;j++)
            {
                fitnessNorm = (population.get(j).getFitness(i)-bmin)/(bmax-bmin);
                population.get(j).setNormalizedFitness(i, fitnessNorm);

            }
        }

    }

    public static void SetIndicatorFitness(ArrayList<Solution> population, Double c, Double k)
    {
        Double indicatorFitness;
        ArrayList<Double> indicators;

        for (Solution sol: population)
        {
            indicatorFitness = 0.0;
            indicators = sol.getIndicatorValues();

            for (Double i:indicators)
            {
                indicatorFitness += -1.0*Math.exp(-1.0*(i/(c*k)));
            }
            sol.setIndicatorFitness(indicatorFitness);
        }
    }


}

package com.example.realibea;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RealIBEA {


    public static ArrayList<Solution> IBEASelection(ArrayList<Solution> combinedPopulation, double k)
    {
        double c;
        int targetSize = combinedPopulation.size()/2;

        //System.out.println(targetSize+":"+combinedPopulation.size());

        ArrayList<Solution> newGeneration = new ArrayList<>();

        while(combinedPopulation.size()>targetSize)
        {
            PopulationUtils.NormalizePopulationFitness(combinedPopulation, ZDTProblem.objectiveNum());
            c = IndicatorUtils.SetPopulationBinaryIndicator(combinedPopulation);
            PopulationUtils.SetIndicatorFitness(combinedPopulation, c,k);

            Collections.sort(combinedPopulation, new IndicatorComparator());

            for (int i=1;i<combinedPopulation.size();i++)
            {
                newGeneration.add(combinedPopulation.get(i));
            }
            combinedPopulation = newGeneration;
            newGeneration = new ArrayList<>();
            //System.out.println(targetSize+":"+combinedPopulation.size());


        }

        return combinedPopulation;



    }


    public static ArrayList<Solution> generateOffspringPopulation(ArrayList<Solution> parentPopulation, double mutProb,
                                                                  double nc, double nm, double lowerBound,
                                                                  double upperBound, double reproductionProb)
    {
        ArrayList<Solution> childPopulation = new ArrayList<>();
        Solution offspring1,offspring2, parent1,parent2;
        int p1,p2, rand, temp;
        ArrayList<Solution> offsprings;
        Random random = new Random();

        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();

        for (int i=0; i<parentPopulation.size();i++)
        {
            a1.add(i);
            a2.add(i);
        }

        for (int i=0; i<parentPopulation.size();i++)
        {
            rand = random.nextInt(parentPopulation.size()-1);
            temp = a1.get(rand);
            a1.set(rand, a1.get(i));
            a1.set(i, temp);
            temp = a2.get(rand);
            a2.set(rand,a2.get(i));
            a2.set(i, temp);
        }

        for (int i=0;i<parentPopulation.size(); i+=4) {
            parent1 = SelectionUtils.MinCrowdedBinaryTournament(parentPopulation.get(a1.get(i)), parentPopulation.get(a1.get(i + 1)));
            parent2 = SelectionUtils.MinCrowdedBinaryTournament(parentPopulation.get(a1.get(i + 2)), parentPopulation.get(a1.get(i + 3)));
            //System.out.println(parent1.getGenotype()+":"+parent2.getGenotype());

            offsprings = OperatorUtils.BoundedSBXCrossover(parent1, parent2, nc, upperBound, lowerBound, reproductionProb);
            childPopulation.add(offsprings.get(0));
            childPopulation.add(offsprings.get(0));

            //System.out.println(offsprings.get(0).getGenotype()+":"+offsprings.get(1).getGenotype());
            parent1 = SelectionUtils.MinCrowdedBinaryTournament(parentPopulation.get(a2.get(i)), parentPopulation.get(a2.get(i + 1)));
            parent2 = SelectionUtils.MinCrowdedBinaryTournament(parentPopulation.get(a2.get(i + 2)), parentPopulation.get(a2.get(i + 3)));
            offsprings = OperatorUtils.BoundedSBXCrossover(parent1, parent2, nc, upperBound, lowerBound, reproductionProb);
            childPopulation.add(offsprings.get(0));
            childPopulation.add(offsprings.get(0));
            //System.out.println(offsprings.get(0).getGenotype()+":"+offsprings.get(1).getGenotype());
            //System.out.println();

        }





        return childPopulation;
    }


    public static void IBEA(String problem)
    {
        int variableNum = 30;
        int populationSize = 100;
        int iterations = 250;
        ArrayList<Solution> population = PopulationUtils.InitialPopulation(variableNum, populationSize);
        ArrayList<Solution> childPopulation;
        Double c;
        Double k = 0.05;
        Double reproductionProbability = 0.1;
        Double mutationProbability = (double)1/variableNum;

        double nc = 20.0;
        double nm = 20.0;
        double upperBound = 1.0;
        double lowerBound = 0.0;

        for (int i=0; i<iterations;i++) {
            switch (problem){
                case "ZDT1":
                    PopulationUtils.EvaluatePopulationZDT1(population);
                    break;

                default:
                    System.out.println("PROBLEM NOT RECOGNISED");
                    return;
            }
            System.out.println(i);
            PopulationUtils.NormalizePopulationFitness(population, ZDTProblem.objectiveNum());

            //Set indicator value using normalized fitness
            //Issues may be here

            c = IndicatorUtils.SetPopulationBinaryIndicator(population);
            PopulationUtils.SetIndicatorFitness(population, c, k);

            /**
             System.out.println(c);
             for (Solution sol:population)
             {
             System.out.println(sol.getNormalizedFitness());
             System.out.println(sol.getIndicatorValues());
             System.out.println(sol.getIndicatorFitness());
             System.out.println();
             }
             */

            //Environmental Selection
            childPopulation = generateOffspringPopulation(population, mutationProbability, nc, nm, lowerBound,
                    upperBound, reproductionProbability);

            population.addAll(childPopulation);
            //System.out.println(population.size());
            switch (problem){
                case "ZDT1":
                    PopulationUtils.EvaluatePopulationZDT1(population);
                    break;

                default:
                    System.out.println("PROBLEM NOT RECOGNISED");
                    return;
            }
            population = IBEASelection(population, k);
            //System.out.println(population.size());
        }


        for (Solution sol:population)
        {
            System.out.println(sol.getFitness());
        }

        AnalysisUtils.generateDatFile(population, "solutions", ZDTProblem.objectiveNum());


    }




    public static void main(String[] args)
    {
        IBEA("ZDT1");




    }
}

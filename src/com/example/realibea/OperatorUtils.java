package com.example.realibea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public final class OperatorUtils {

    public static ArrayList<Solution> BoundedSBXCrossover(Solution parent1, Solution parent2, double nc, Double upperBound,
                                                          Double lowerBound,
                                                          double repoProb)
    {
        ArrayList<Double> offspring1Genotype = new ArrayList<>();
        ArrayList<Double> offspring2Genotype = new ArrayList<>();

        ArrayList<Double> parent1Genotype = parent1.getGenotype();
        ArrayList<Double> parent2Genotype = parent2.getGenotype();
        Random random = new Random();

        Double x1,x2,beta,alpha,c1,c2, rand,xl,xu,beta_q, trial;

        xl = lowerBound;
        xu = upperBound;


        if (random.nextDouble() >= repoProb) {
            for (int i = 0; i < parent1Genotype.size(); i++) {

                trial = random.nextDouble();
                //System.out.println(xu);
                if (trial <= 0.5) {
                    //System.out.println(Math.abs(parent1Genotype.get(i)-parent2Genotype.get(i))>1e-14);
                    if (Math.abs(parent1Genotype.get(i) - parent2Genotype.get(i)) > 1e-9) //put this value into the method arguments
                    {
                        if (parent1Genotype.get(i) < parent2Genotype.get(i)) {
                            x1 = parent1Genotype.get(i);
                            x2 = parent2Genotype.get(i);
                        } else {
                            x1 = parent2Genotype.get(i);
                            x2 = parent1Genotype.get(i);
                        }

                        rand = random.nextDouble();
                        beta = 1.0 + (2.0 * (x1 - xl) / (x2 - x1));
                        alpha = 2.0 - Math.pow(beta, -1.0 / (nc + 1.0));

                        if (rand <= 1.0 / alpha) {
                            beta_q = Math.pow((rand * alpha), 1.0 / (nc + 1));
                        } else {
                            beta_q = Math.pow((1.0 / (2.0 - rand * alpha)), 1.0 / (nc + 1.0));
                        }
                        c1 = 0.5 * (x1 + x2 - beta_q * (x2 - x1));
                        beta = 1.0 + (2.0 * (xu - x2) / (x2 - x1));
                        alpha = 2.0 - Math.pow(beta, -(nc + 1.0));
                        if (rand <= 1.0 / alpha) {
                            beta_q = Math.pow(rand * alpha, 1.0 / (nc + 1.0));
                        } else {
                            beta_q = Math.pow(1.0 / (2.0 - rand * alpha), 1.0 / (nc + 1.0));
                        }
                        c2 = 0.5 * ((x1 + x2) + beta_q * (x2 - x1));

                        if (c1 < xl) c1 = xl;
                        if (c2 < xl) c2 = xl;
                        if (c1 > xu) c1 = xu;
                        if (c2 > xu) c2 = xu;


                        if (random.nextDouble() <= 0.5) {
                            offspring1Genotype.add(c2);
                            offspring2Genotype.add(c1);
                        } else {
                            offspring1Genotype.add(c1);
                            offspring2Genotype.add(c2);
                        }

                    } else {
                        offspring1Genotype.add(parent1Genotype.get(i));
                        offspring2Genotype.add(parent2Genotype.get(i));
                    }


                } else {
                    offspring1Genotype.add(parent1Genotype.get(i));
                    offspring2Genotype.add(parent2Genotype.get(i));
                }


            }
        }
        else {
            for (int j=0; j<parent1Genotype.size();j++)
            {
                offspring1Genotype.add(parent1Genotype.get(j));
                offspring2Genotype.add(parent2Genotype.get(j));
            }
        }

        return new ArrayList<>(Arrays.asList(new Solution(offspring1Genotype), new Solution(offspring2Genotype)));
    }


    public static Solution BoundedPolynomialMutation(Solution individual, double nm, ArrayList<Double> lowerBounds,
                                                     ArrayList<Double> upperBounds, double mutProb)
    {
        ArrayList<Double> individualGenotype = individual.getGenotype();
        ArrayList<Double> mutatedIndvGenotype = new ArrayList<>();
        Random random = new Random();

        double y,yl,yu,val,xy,mut;
        double rnd, delta1, delta2, mut_pow, deltaq;


        //System.out.println(individualGenotype.size()+":"+lowerBounds.size());


        for (int i=0;i<individualGenotype.size();i++)
        {
            mut = random.nextDouble();

            if (mut < mutProb)
            {
                y = individualGenotype.get(i);
                yl = lowerBounds.get(i);
                yu = upperBounds.get(i);

                delta1  = (y - yl) / (yu - yl);
                delta2  = (yu - y) / (yu - yl);
                rnd = random.nextDouble();
                mut_pow = 1.0/ (nm+1.0);

                if (rnd<=0.5)
                {
                    xy = 1.0 - delta1;
                    val = 2.0*rnd + (1.0-2.0*rnd) * Math.pow(xy, (nm+1.0));
                    deltaq = Math.pow(val, mut_pow)-1.0;
                }
                else
                {
                    xy = 1.0 - delta2;
                    val    = 2.0 * (1.0 - rnd) + 2.0 * (rnd - 0.5) * (Math.pow(xy, (nm + 1.0)));
                    deltaq = 1.0 - Math.pow(val, mut_pow);
                }

                y = y + deltaq * (yu - yl);
                if (y < yl)
                    y = yl;
                if (y > yu)
                    y = yu;
                mutatedIndvGenotype.add(y);
            }
            else
            {
                mutatedIndvGenotype.add(individualGenotype.get(i));
            }
        }
        return new Solution(mutatedIndvGenotype);
    }



}

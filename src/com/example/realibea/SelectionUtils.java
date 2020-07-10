package com.example.realibea;

import java.util.Random;

public class SelectionUtils {

    public static Solution MinCrowdedBinaryTournament(Solution parent1, Solution parent2)
    {
        if (parent1.getIndicatorFitness()>parent2.getIndicatorFitness())
        {
            return parent1;
        }

        if (parent1.getIndicatorFitness()<parent2.getIndicatorFitness())
        {
            return parent2;
        }

        else
        {
            Random random = new Random();
            if (random.nextDouble()<0.5)
            {
                return parent1;
            }else{
                return parent2;
            }
        }
    }


}

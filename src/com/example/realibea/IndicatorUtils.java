package com.example.realibea;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.cert.CertPathBuilderSpi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class IndicatorUtils {

    public static Double SetPopulationBinaryIndicator(ArrayList<Solution> population)
    {
        Double tempIndicator;
        ArrayList<Double> Indicators;
        Double c = Double.NEGATIVE_INFINITY;
        for (int i=0;i<population.size();i++)
        {
            Indicators = new ArrayList<>();

            for (int j=0;j<population.size();j++)
            {
                if (j!=i)
                {
                    tempIndicator = BinaryIndicator(population.get(j).getNormalizedFitness(),
                            population.get(i).getNormalizedFitness());

                    Indicators.add(tempIndicator);

                    if (tempIndicator>c)
                    {
                        c = tempIndicator;
                    }
                }
            }
            population.get(i).setIndicatorValues(Indicators);

        }
        return c;

    }
    public static Double BinaryIndicator(ArrayList<Double> A, ArrayList<Double> B)
    {
        Double tempEpsilon;
        ArrayList<Double> epsilons = new ArrayList<>();

        for (int i=0;i<A.size();i++)
        {
            tempEpsilon = A.get(i)-B.get(i);
            tempEpsilon = (double)Math.round(tempEpsilon * 100000d) / 100000d;

            epsilons.add(tempEpsilon);

            }

        return Collections.max(epsilons);
        }
}

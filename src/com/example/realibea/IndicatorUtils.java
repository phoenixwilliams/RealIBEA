package com.example.realibea;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.cert.CertPathBuilderSpi;
import java.util.ArrayList;
import java.util.Arrays;

public final class IndicatorUtils {

    public static Double BinaryIndicatorDouble(ArrayList<Double> A, ArrayList<Double> B)
    {
        Double epsilon = Double.POSITIVE_INFINITY;
        Double tempEpsilon;

        for (int i=0;i<A.size();i++)
        {
            tempEpsilon = A.get(i)-B.get(i);

            System.out.println(B.get(i)-A.get(i));
            if (tempEpsilon<epsilon)
            {
                if (tempEpsilon<0)
                {
                    tempEpsilon = -1.0*(B.get(i)-A.get(i));
                }
                epsilon = tempEpsilon;
            }
        }
        return (double)Math.round(epsilon * 100000d) / 100000d;
    }

}

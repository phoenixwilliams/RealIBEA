package com.example.realibea;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AnalysisUtils {


    public static void generateDatFile(ArrayList<Solution> population, String filename, int objectives)
    {
        try{
            FileWriter myWriter = new FileWriter(filename+".dat");

            for (Solution sol:population)
            {
                for (int i=0;i<objectives;i++)
                {
                    myWriter.write(Double.toString(sol.getFitness(i))+"  ");
                }
                myWriter.write("\n");
            }
            myWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

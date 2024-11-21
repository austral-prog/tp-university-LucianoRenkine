package com.university;

import com.university.readers.Creators;
import com.university.readers.CreatorA;
import com.university.readers.CreatorB;
import com.university.readers.CreatorC;
import com.university.readers.Writers;
import com.university.readers.WriterA;
import com.university.readers.WriterB;
import com.university.readers.WriterC;
import com.university.csv.Reader;
import com.university.csv.Writer;
import com.university.evaluation.criteria.Criteria;

public class App {
     public static void main(String[] args) {
         try {
             University university = new University();
             Creators creator1 = new CreatorA();
             Creators creator2 = new CreatorB();
             Creators creator3 = new CreatorC();
             Writers writer1 = new WriterA();
             Writers writer2 = new WriterB();
             Writers writer3 = new WriterC();
             Criteria criteriaProcessor = new Criteria();
             Reader.processCSV("src/main/resources/input.csv", university, creator1, criteriaProcessor);
             Writer.writeCSV("src/main/resources/solution.csv", university, criteriaProcessor, writer1);
             Reader.processCSV("src/main/resources/input_2.csv", university, creator2, criteriaProcessor);
             Writer.writeCSV("src/main/resources/solution_2.csv", university, criteriaProcessor, writer2);
             Reader.processCSV("src/main/resources/input_3.csv", university, creator3, criteriaProcessor);
             Writer.writeCSV("src/main/resources/solution_3.csv", university, criteriaProcessor, writer3);
             System.out.println("Archivos CSV procesados y escritos exitosamente.");
         } catch (Exception e) {
             System.err.println("Error en la generación de los archivos " + e.getMessage());
             e.printStackTrace();
         }
     }
}

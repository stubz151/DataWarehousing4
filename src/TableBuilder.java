

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TableBuilder {
    ArrayList<String[]> table;
    String[] columnLabels;
    String fileName;
    private int threshold;
    public TableBuilder(String fileName) {
        table = new ArrayList<>();
        this.fileName=fileName;
        Setup();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is threshold");
        threshold=scanner.nextInt();

    }

    private void Setup() {
        Path pathToFile = Paths.get(fileName);
        String line = "";
        System.out.println("Trying to read file");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            columnLabels = br.readLine().split(",");
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                table.add(attributes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("finished reading file");
    }

    public ArrayList<String[]> partitionedTable(ArrayList<String[]> table , Integer[] columnNums)
    {
        ArrayList<String[]> curTable = (ArrayList<String[]>) table.clone();
        for (int i =0 ; i < columnNums.length-1; i ++)
        {
            HashMap<String, ArrayList<Integer>> hmap = new HashMap<String, ArrayList<Integer>>();
            for (int j=0; j < curTable.size() ; j++)
            {
                String key = curTable.get(j)[columnNums[i]];
                if (hmap.containsKey(key))
                {
                    ArrayList<Integer> positions = hmap.get(curTable.get(j)[columnNums[i]]);
                    positions.add(j);
                    hmap.replace(key,positions);
                }
                else
                {
                    hmap.put(key,new ArrayList<Integer>());
                }
            }
            ArrayList<String[]> newList = new ArrayList<>();
            for (String key: hmap.keySet()) {
                ArrayList<Integer> positions = hmap.get(key);
                if (positions.size() >= threshold )
                {
                    for (int j=0 ; j < positions.size() ; j++)
                    {
                        newList.add(curTable.get(positions.get(j)));
                    }
                }
            }
            curTable =newList;
        }
        return curTable;
    }
    public void display5(ArrayList<String[]> tab)
    {
        System.out.println("Display 5 ");
        for (int i =0 ; i < 5; i++)
        {
            for (int j =0 ; j <7; j++)
            {
                System.out.print(tab.get(i)[j]+ " ");
            }
            System.out.println("");
        }
    }
    public void displayAll(ArrayList<String[]> tab)
    {
        System.out.println("Displaying all ");
        for (int i =0 ; i < tab.size(); i++)
        {
            for (int j =0 ; j <7; j++)
            {
                System.out.print(tab.get(i)[j]+ ",");
            }
            System.out.println("");
        }
    }
    public ArrayList<String[]> getTable() {
        return table;
    }

    public void setTable(ArrayList<String[]> table) {
        this.table = table;
    }

    public String[] getColumnLabels() {
        return columnLabels;
    }

    public void setColumnLabels(String[] columnLabels) {
        this.columnLabels = columnLabels;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }
}
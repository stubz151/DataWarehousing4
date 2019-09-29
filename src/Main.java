import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    TableBuilder tableBuilder = new  TableBuilder("Source Data.csv");
    ArrayList<String[]> table = tableBuilder.getTable();
    tableBuilder.display5(table);
    Integer[] apexColumns = new Integer[7];
    for (int i =0 ; i <=6 ; i++)
    {
        apexColumns[i] = i;
    }
    ArrayList<String[]> apexTable = tableBuilder.partitionedTable(table,apexColumns);
    tableBuilder.displayAll(apexTable);
    }


}

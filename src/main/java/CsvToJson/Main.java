package CsvToJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        List<Employee> list = parseCSV(columnMapping, fileName);
        listToJson(list);
    }


    public static List<Employee> parseCSV(String[] columnMapping, String fileName){
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(reader)
                    .withMappingStrategy(strategy)
                    .build();
            return csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> void listToJson(List<T> list){
        Type listType = new TypeToken<List<T>>() {}.getType();
        GsonBuilder builder = new GsonBuilder()
                .setPrettyPrinting();
        Gson gson = builder.create();
        writeString(gson.toJson(list,listType));
    }

    public static void writeString(String jsonList){
        try (FileWriter file = new
                FileWriter("newData.json")) {
            file.write(jsonList);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ReadJson;

import CsvToJson.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainReadJson {
    public static void main(String[] args) {
        String json = readString("dataj.json");
        List<EmployeeRead> list = jsonToList(json);
        System.out.println(list);
    }

    public static String readString(String fileName){
        String line;
        StringBuilder json = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            while ((line = reader.readLine()) != null) {
                    json.append(line);
            }
        } catch (IOException e){
            return e.getMessage();
        }
        return json.toString();
    }
    public static List<EmployeeRead> jsonToList(String jsonText){
        List<EmployeeRead> employeeReads = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(jsonText);
            JSONArray arrayJson = (JSONArray) obj;
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for(Object object : arrayJson){
                employeeReads.add(gson.fromJson(object.toString(), EmployeeRead.class));
            }
        } catch (ParseException e){
            e.getMessage();
        }
        return employeeReads;
    }
}
package XmlToJson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainXML {
    public static void main(String[] args) {
        String fileName = "datax.xml";
        List<EmployeeXML> list = parseXML(fileName);
        listToJson(list);
    }


    public static List<EmployeeXML> parseXML(String fileName) {
        List <EmployeeXML> list = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = element.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = element.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = element.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                    EmployeeXML employee = new EmployeeXML(id, firstName, lastName, country, age);
                    list.add(employee);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e){
            e.getMessage();
        }
        return list;
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
                FileWriter("dataj.json")) {
            file.write(jsonList);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

import static io.restassured.http.ContentType.JSON;
import static resource.constants.KeyWords.GLOBAL_PROPERTIES_PATH;

public class Utils {

    private static RequestSpecification req;

    public static String extracField(String response, String field) {
        JsonPath js = new JsonPath(response); //for parsing Json
        String data = js.getString(field);
        return data;
    }

    public static String getJsonPath(Response response, String key) {
        String strResponse = response.asString();
        JsonPath js = new JsonPath(strResponse);
        return js.get(key).toString();
    }

    public static Integer extractIntData(Response response, String key) {
        String strResponse = response.asString();
        JsonPath js = new JsonPath(strResponse);
        return js.getInt(key);
    }

    public RequestSpecification requestSpecification() throws IOException {

        if (req == null){
            PrintStream log = new PrintStream(new FileOutputStream("loggin.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(JSON).build();
            return req;
        }
        return req ;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(GLOBAL_PROPERTIES_PATH);
        prop.load(file);
        return prop.getProperty(key);

    }
}

package Parsing;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Data
public class ParserApi {
    public static double getLatestPrice() {
        return latestPrice;
    }

    private static double latestPrice;

    public static void connect() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://openexchangerates.org/api/latest.json?app_id=60da2bd9b3064714b2c5f2e8b00fbd40%22")).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(ParserApi::parse)
                    .join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String parse(String responseBody) {

        try {
            JsonElement fileElement = JsonParser.parseString(responseBody);
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray symbols = fileObject.get("rates").getAsJsonArray();
            for (int i = 0; i < symbols.size(); i++) {
                JsonObject rates = symbols.get(i).getAsJsonObject();
                JsonElement price = rates.get("RUB");
                latestPrice = rates;
                latestPrice = Double.parseDouble(String.valueOf((symbol.get("RUB"))));

//                JsonElement symbolN = symbol.get("symbol");
//                String symbolName = symbolN.toString().replace("\"", "");
//                JsonArray filters = symbol.get("filters").getAsJsonArray();
//                JsonObject filter = filters.get(1).getAsJsonObject();
//                double stepSize = Double.parseDouble(filter.get("stepSize").toString().replace("\"", ""));
//                stepSizeList.put(symbolName, stepSize);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

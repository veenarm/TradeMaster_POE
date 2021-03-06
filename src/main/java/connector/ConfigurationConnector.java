package connector;

import app.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class ConfigurationConnector extends BaseConnector {

    private static final String CONF_ENDPOINT = "https://www.redwoodit.com.au/trademaster/api/configure";

    private static ConfigurationConnector instance;

    public static void configure() {
        if (instance == null) {
            instance = new ConfigurationConnector();
        }

        JSONObject data = instance.retrieveData();
        Config.get().setBulkCurrencies((JSONObject) data.get("BulkCurrency"));
        Config.get().setPoeTradeCurrencies((JSONObject) data.get("AllCurrency"));
        Config.get().setLeagueSelection(data.get("DefaultLeagueSelection").toString());
        Config.get().setLeagues(mapJSONArrayToStringArray(data.getJSONArray("Leagues")));
        Config.get().setAllMaps(mapJSONArrayToStringArray(data.getJSONArray("AllMaps")));
        Config.get().setTieredMaps((JSONObject) data.get("TieredMaps"));
        Config.get().setShapedMaps(mapJSONArrayToStringArray(data.getJSONArray("ShapedMaps")));
        Config.get().setElderMaps(mapJSONArrayToStringArray(data.getJSONArray("ElderMaps")));
        Config.get().setBulkBuyWithCurrencies(mapJSONArrayToStringArray(data.getJSONArray("BulkBuyWithCurrencies")));
        log.debug(data.toString());

        // Clean up
        instance = null;
    }

    private static String[] mapJSONArrayToStringArray(JSONArray jsonArray) {
        String[] result = new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            result[i] = (String) jsonArray.get(i);
        }
        return result;
    }

    private JSONObject retrieveData() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            log.debug("Sending 'GET' request to URL : " + CONF_ENDPOINT);
            HttpGet httpGet = new HttpGet(CONF_ENDPOINT);
            httpGet.addHeader("Accept", "application/json");
            HttpResponse response = client.execute(httpGet);
            log.debug("Response Code : " + response.getStatusLine().getStatusCode());
            String stringResult = convertStreamToString(response.getEntity().getContent());
            return new JSONObject(stringResult);
        } catch (Exception e) {
            throw new RuntimeException("Killing Application - Configuration failed to load content.", e);
        }
    }

    @Override
    public Logger getLogger() {
        return log;
    }
}
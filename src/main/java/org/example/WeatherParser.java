package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class WeatherParser {
    public static double parser(HttpResponse<String> response) {
        double t = 0;
        String responseBody = response.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);
// get the first time series
        JSONArray timeSeriesArray = jsonObject.getJSONArray("timeSeries");
        JSONObject firstTimeSeries = timeSeriesArray.getJSONObject(0);
// get the value of t from the first time series
        JSONArray parametersArray = firstTimeSeries.getJSONArray("parameters");
        for (int i = 0; i < parametersArray.length(); i++) {
            JSONObject parameter = parametersArray.getJSONObject(i);
            if (parameter.getString("name").equals("t")) {
                JSONArray tValues = parameter.getJSONArray("values");
                t = tValues.getDouble(0);
                break;
            }
        }
        return t;
    }
}


package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    HashMap<String, Json> jsonPairs = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs){
            this.jsonPairs.put(pair.key, pair.value);
        }
    }

    @Override
    public String toJson() {
        String out = "";

        List<String> strOut = new ArrayList<>();
        for (Map.Entry<String, Json> pair : this.jsonPairs.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue().toJson();
            strOut.add("'" + key + "': " + value);
        }

        if (strOut.size() > 0) {
            out = String.join(", ", strOut);
        }

        return "{" + out + "}";
    }

    public void add(JsonPair jsonPair) {
        this.jsonPairs.put(jsonPair.key, jsonPair.value);
    }

    public boolean contains(String name) {
        return this.jsonPairs.containsKey(name);
    }

    public Json find(String name) {
        if (contains(name)) return jsonPairs.get(name);
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonObject = new JsonObject();
        for(String name: names){
            if(contains(name)) jsonObject.add(new JsonPair(name, jsonPairs.get(name)));
        }
        return jsonObject;
    }
}

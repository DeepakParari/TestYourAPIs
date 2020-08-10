package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONHelper.class);

    /**
     * To retrieve any node value from the response.
     * @param response Response
     * @param key JSON path
     * @return Value
     */
    public String getValueFromJSON(Response response, String key) {
        JsonPath js = new JsonPath(response.asString());
        LOGGER.info("Value being retrieved: " + key + " : " + js.get(key).toString());
        return js.get(key).toString();

    }

    /**
     * To modify the payload. This reads the sample body and based on the Key and Value substitutes in the payload.
     * @param jsonObject Payload read from the file(JSON)
     * @param key JSON Path to be replaced
     * @param value Value to be replaced.
     * @return jsonObject Payload after modification.
     */
    //TODO Make it generic enough to handle different data types for value.
    public Object setValueInJSON(Object jsonObject, String key, Double value) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(jsonObject);
            JsonNode rootNode = mapper.readTree(jsonString);
            ((ObjectNode) rootNode).put(key, value);
            return mapper.treeToValue(rootNode,Object.class);


        } catch (Exception e) {
            LOGGER.error("Problem while setting the value in JSON ", e);
        }
        return jsonObject;
    }
}

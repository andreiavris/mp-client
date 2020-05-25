package ro.andrei.microprofile.demo.secure;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Represent the MP Auth token
 */

@Getter
@Setter
public class MPJWTToken {

    private String iss; // issuer
    private String aud; // audience
    private String jti; // Unique identifier
    private Long exp; // expiration time
    private Long iat; // issued at
    private String sub; // subject
    private String upn; // value for name in Principal
    private String preferredUsername;  // value for name in Principal
    private List<String> groups = new ArrayList<>();
        /*
    "iss": "https://server.example.com",
            "aud": "s6BhdRkqt3",
            "jti": "a-123",
            "exp": 1311281970,
            "iat": 1311280970,
            "sub": "24400320",
            "upn": "jdoe@server.example.com",
            "groups": ["red-group", "green-group", "admin-group", "admin"],
    */

    private List<String> roles;
    private Map<String, String> additionalClaims;

    public void addAdditionalClaims(String key, String value) {
        if (additionalClaims == null) {
            additionalClaims = new HashMap<>();
        }
        additionalClaims.put(key, value);
    }

    public JsonObject toJSONString() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.put("iss", iss);
        jsonObject.put("aud", aud);
        jsonObject.put("jti", jti);
        jsonObject.put("exp", exp / 1000);
        jsonObject.put("iat", iat / 1000);
        jsonObject.put("sub", sub);
        jsonObject.put("upn", upn);
        jsonObject.put("preferred_username", preferredUsername);

        if (additionalClaims != null) {
            for (Map.Entry<String, String> entry : additionalClaims.entrySet()) {
                jsonObject.put(entry.getKey(), entry.getValue());
            }
        }

        JsonArray jsonArray = new JsonArray();
        for (String group : groups) {
            jsonArray.add(group);
        }
        jsonObject.put("groups", jsonArray);

        return jsonObject;
    }

}

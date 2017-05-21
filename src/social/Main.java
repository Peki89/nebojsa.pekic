package social;

import java.io.*;
import java.util.*;
import org.json.*;

public class Main {

    public static void main(String[] args) throws IOException, JSONException {

        InputStream in = new FileInputStream(new File("data.json"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        /*
        System.out.println(out.toString());
        reader.close();
         */
        
        JSONArray arr = new JSONArray(out.toString());
        JSONObject obj = null;

        Map<Integer, String> allNames = new HashMap<Integer, String>();
        for (int i = 0; i < arr.length(); i++) {
            obj = arr.getJSONObject(i);
            allNames.put(i + 1, obj.getString("firstName") + " " + obj.getString("surname"));
        }

        for (int i = 0; i < arr.length(); i++) {
            obj = arr.getJSONObject(i);
            System.out.println("User: " + obj.getString("firstName") + " " + obj.getString("surname"));
            System.out.print("Friend list: ");
            JSONArray myFriends = obj.getJSONArray("friends");

            for (int j = 0; j < myFriends.length(); j++) {
                if (allNames.containsKey(myFriends.get(j))) {
                    System.out.print(allNames.get(myFriends.get(j)) + ", ");
                }
            }
            
            System.out.println('\n');
        }
    }
}

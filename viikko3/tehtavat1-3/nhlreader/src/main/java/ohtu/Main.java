
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        Date date = new Date();
        ArrayList<Player> playerList = new ArrayList<>();
        
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        System.out.println("Players from Finland, " + date);
        System.out.println("");
        
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                playerList.add(player);
            }
        }
        
        for (Player player: playerList) {
            System.out.println(player);
        }
    }
  
}

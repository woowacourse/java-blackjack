package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

   public static Map<Player, Integer> calculateResult(Dealer dealer, List<Player> players) {
       Map<Player, Integer> result = new HashMap<>();
       players.forEach(player -> result.put(player, dealer.isWin(player)));
       return result;
   }

}

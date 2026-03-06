package domain;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Players  implements Iterable<Player> {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
//
//    public Map<String, Boolean> createPlayerResult(int dealerTotal, boolean isDealerBurst) {
//        BlackjackResult blackjackResult = new BlackjackResult;
//        for (Player player : players) {
//            boolean isWinning = false;
//            // TODO : 딜러와 플레이어 비교해서 결과 판정
//            blackjackResult.add(player.getName(),isWinning);
//        }
//
//    }
}

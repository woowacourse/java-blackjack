package domain;

import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    private GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public int giveCardsToDealer(){
        int count = 0;
        while(dealer.isLessThen(16)){
            dealer.receiveCard();
            count++;
        }
        return count;
    }

    public static GameManager create(Dealer dealer, List<Player> players) {
        validateDuplicatePlayer(players);
        return new GameManager(dealer, players);
    }

    private static void validateDuplicatePlayer(final List<Player> players) {
        if (isDuplicate(players)) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 중복될 수 없습니다.");
        }
    }

    private static boolean isDuplicate(final List<Player> players) {
        return players.stream()
                .distinct()
                .count() != players.size();
    }


    public boolean isAbleToHit(final Gamer gamer) {
        return !gamer.isBust();
    }


    public void giveCardToGamer(final Gamer gamer) {
        gamer.receiveCard();
    }

    public GameResult calculateResult(final int index) {
        final Player player = players.get(index);
        if(dealer.isBust() && player.isBust()) return GameResult.DRAW;
        if(dealer.isBust()) return GameResult.WIN;
        return player.calculateGameResult(dealer.calculateScore());
    }


    public int calculatePlayerSize() {
        return players.size();
    }

    public Player findPlayerByIndex(final int index) {
        return players.get(index);
    }

    public Map<GameResult, Integer> calculateDealerGameResult() {
        Map<GameResult, Integer> result = new HashMap<>();
        for(int i=0; i<players.size(); i++){
            final GameResult gameResult = calculateResult(i);
            if (gameResult == GameResult.DRAW) {
                result.merge(GameResult.DRAW, 1, Integer::sum);
            }
            if (gameResult == GameResult.WIN) {
                result.merge(GameResult.LOSE, 1, Integer::sum);
            }
            if (gameResult == GameResult.LOSE) {
                result.merge(GameResult.WIN, 1, Integer::sum);
            }
        }
        return result;
    }

    public Map<String, GameResult> calculatePlayerGameResult() {
        Map<String, GameResult> resultMap = new HashMap<>();
        for(int i=0; i<players.size(); i++){
            resultMap.put(players.get(i).getName(),calculateResult(i));
        }
        return resultMap;
    }
}

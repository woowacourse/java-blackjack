package model;

import model.card.Card;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private static final int CAN_RECEIVE_MAX_NUMBER = 21;
    private final Map<Player, List<Card>> scoreBoards;

    public Result(final Players players) {
        this.scoreBoards = createScoreBoards(players);
    }

    private Map<Player, List<Card>> createScoreBoards(final Players players) {
        Map<Player, List<Card>> scoreBoards = new LinkedHashMap<>();
        players.getPlayers().forEach(player -> {
            List<Card> cards = new ArrayList<>();
            scoreBoards.put(player, cards);
        });
        return scoreBoards;
    }

    public void addCard(final Player player, final Card card) {
        scoreBoards.get(player).add(card);
    }

    public boolean canPlayerReceiveCard(final Player player) {
        return CAN_RECEIVE_MAX_NUMBER >= scoreBoards.get(player).stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    public Map<Player, List<Card>> getScoreBoards() {
        return scoreBoards;
    }
}

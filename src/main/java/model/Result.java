package model;

import model.card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private final Map<Player, List<Card>> scoreBoards;

    public Result(final Players players) {
        this.scoreBoards = createScoreBoards(players);
    }

    private Map<Player, List<Card>> createScoreBoards(final Players players) {
        Map<Player, List<Card>> scoreBoards = new HashMap<>();
        players.getPlayers().forEach(player -> {
            List<Card> cards = new ArrayList<>();
            scoreBoards.put(player, cards);
        });
        return scoreBoards;
    }

    public Map<Player, List<Card>> getScoreBoards() {
        return scoreBoards;
    }

    public void addCard(final Player player, final Card card) {
        scoreBoards.get(player).add(card);
    }
}

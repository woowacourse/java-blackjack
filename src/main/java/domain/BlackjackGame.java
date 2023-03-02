package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private List<Player> players;
    private Deck deck;

    public BlackjackGame(List<String> playerNames, String dealerName, NumberGenerator numberGenerator) {
        initPlayers(playerNames, dealerName);
        initDeck(numberGenerator);
    }

    private void initDeck(NumberGenerator numberGenerator) {
        deck = new Deck(numberGenerator);
    }

    private void initPlayers(List<String> playerNames, String dealerName) {
        players = playerNames.stream()
                .map(playerName -> new Player(playerName, new CardPool(Collections.emptyList())))
                .collect(Collectors.toList());
        players.add(new Dealer(dealerName, new CardPool(Collections.emptyList())));
    }

    public void letPlayersToHit() {
        for (Player player : players) {
            player.draw(deck.serve());
        }
    }
}

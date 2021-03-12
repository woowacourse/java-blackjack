package blackjack.domain;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    public static final int BLACKJACK_NUMBER = 21;
    public static final int BLACKJACK_CONDITION_CARDS_SIZE = 2;

    private final Players players;
    private final Dealer dealer;

    private Game(Players players) {
        this.players = players;
        this.dealer = new Dealer();
        setUpTwoCardsAndState();
    }

    private void setUpTwoCardsAndState() {
        for (Player player : players.getPlayers()) {
            player.setUpTwoCardsAndState();
        }
        dealer.setUpTwoCardsAndState();
    }

    public static Game of(Players players) {
        return new Game(players);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public void giveCardToPlayer(Player player) {
        player.addCard(Deck.draw());
    }

    public void giveCardToDealer(Dealer dealer) {
        dealer.addCard(Deck.draw());
        dealer.doStayIfPossible();
    }

    public Map<String, Integer> getPlayersProfitResult() {
        return players.getPlayersProfitResult(dealer.getCardsScore());
    }

    public Map<String, Integer> getDealerProfitResult(Map<String, Integer> playersProfitResult) {
        int playersTotalProfit = playersProfitResult.values().stream()
                .mapToInt(value -> value)
                .sum();

        Map<String, Integer> dealerProfitResult = new HashMap<>();
        dealerProfitResult.put(dealer.getName(), playersTotalProfit * (-1));
        return dealerProfitResult;
    }
}

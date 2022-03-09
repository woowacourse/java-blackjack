package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Player dealer;
    private final List<Player> gamers;


    public BlackJackGame(final Player dealer, final List<Player> gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public void giveFirstCards(final Deck deck) {
        giveTwoCards(dealer, deck);

        for (Player gamer : gamers) {
            giveTwoCards(gamer, deck);
        }
    }

    private void giveTwoCards(final Player player, final Deck deck) {
        for (int i = 0; i < 2; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Player, Result> calculateResult() {
        Map<Player, Result> gameResult = new LinkedHashMap<>();

        int dealerResult = dealer.calculateResult();
        for (Player gamer : gamers) {
            gameResult.put(gamer, Result.findResult(dealerResult, gamer.calculateResult()));
        }
        return gameResult;
    }
}

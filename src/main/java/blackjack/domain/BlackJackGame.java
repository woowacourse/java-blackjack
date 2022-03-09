package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        int dealerResult = dealer.calculateResult();
        return gamers.stream()
                .collect(Collectors.toMap(gamer -> gamer,
                        gamer -> Result.findResult(dealerResult,
                                gamer.calculateResult()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
}

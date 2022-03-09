package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {

    private final Player dealer;
    private final List<Gamer> gamers;

    public BlackJackGame(final Player dealer, final List<Gamer> gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public void giveFirstCards(final Deck deck) {
        giveTwoCards(dealer, deck);

        for (Gamer gamer : gamers) {
            giveTwoCards(gamer, deck);
        }
    }

    private void giveTwoCards(final Player player, final Deck deck) {
        for (int i = 0; i < 2; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Gamer, Result> calculateResultBoard() {
        int dealerResult = dealer.calculateResult();
        return gamers.stream()
                .collect(Collectors.toMap(gamer -> gamer,
                        gamer -> Result.findResult(dealerResult,
                                gamer.calculateResult()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Gamer> getGamers() {
        return List.copyOf(gamers);
    }
}

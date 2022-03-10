package blackjack.domain;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Gamers;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Player dealer;
    private final Gamers gamers;

    public BlackJackGame(final Player dealer, final Gamers gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public void handOutStartingCards(final Deck deck) {
        dealStartingCards(dealer, deck);
        for (Player gamer : gamers.getGamers()) {
            dealStartingCards(gamer, deck);
        }
    }

    private void dealStartingCards(final Player player, final Deck deck) {
        for (int i = 0; i < Card.START_CARD_COUNT; i++) {
            player.receiveCard(deck.draw());
        }
    }

    public Map<Player, Result> calculateResultBoard() {
        return gamers.compareResult(dealer.calculateResult());
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Gamer> getGamers() {
        return gamers.getGamers();
    }
}

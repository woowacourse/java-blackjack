package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private final Players players;
    private final Dealer dealer;
    private final Deck deck;

    private BlackJackGame(Players players, Dealer dealer, Deck deck) {
        this.players = players;
        this.dealer = dealer;
        this.deck = deck;
    }

    public static BlackJackGame create(Players players) {
        Dealer dealer = new Dealer();
        Deck deck = Deck.create(CardFactory.createShuffledCard());
        return new BlackJackGame(players, dealer, deck);
    }

    public void setUp() {
        for (Player player : getPlayers()) {
            drawCardTo(player);
            drawCardTo(player);
        }
        drawCardTo(dealer);
        drawCardTo(dealer);
    }

    public Map<Player, Result> calculateResult() {
        Map<Player, Result> result = new HashMap<>();

        for (Player player : players.getPlayers()) {
            result.put(player, Result.calculate(player, dealer));
        }
        return result;
    }

    public void drawCardTo(Participant participant) {
        if (participant.canReceive()) {
            Card card = deck.draw();
            participant.addCard(card);
        }
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

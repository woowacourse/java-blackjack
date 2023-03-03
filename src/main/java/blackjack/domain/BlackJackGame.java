package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

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
        Deck deck = Deck.create(CardFactory.createCard());
        return new BlackJackGame(players, dealer, deck);
    }

    public void setUp() {
        List<Player> players = this.players.getPlayers();
        for (Player player : players) {
            drawCard(player, INIT_CARD_COUNT);
        }
        drawCard(dealer, INIT_CARD_COUNT);
    }

    public void addCard(Participant participant) {
        if (participant.canReceive()) {
            Card card = deck.draw();
            participant.addCard(card);
        }
    }

    public Map<Player, Result> calculateResult() {
        Map<Player, Result> result = new HashMap<>();

        for (Player player : players.getPlayers()) {
            result.put(player, Result.calculate(player, dealer));
        }
        return result;
    }

    private void drawCard(Participant participant, int count) {
        for (int i = 0; i < count; i++) {
            Card card = deck.draw();
            passCard(participant, card);
}
    }

    private void passCard(Participant participant, Card card) {
        if (participant.canReceive()) {
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

package domain.game;

import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import strategy.CardGenerator;

public class BlackjackGame {

    private static final int STARTING_CARDS_AMOUNT = 2;

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private BlackjackGame(Dealer dealer, Players players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame of(Players players, CardGenerator cardGenerator) {
        return new BlackjackGame(Dealer.withNoCards(), players, Deck.from(cardGenerator));
    }

    public void distributeStartingCards() {
        dealer.tryReceive(deck.drawCards(STARTING_CARDS_AMOUNT));
        for (Player player : players.getPlayers()) {
            player.tryReceive(deck.drawCards(STARTING_CARDS_AMOUNT));
        }
    }

    public void giveOneCardTo(Player player) {
        player.tryReceive(deck.drawCard());
    }

    public void giveOneCardTo(Dealer dealer) {
        dealer.tryReceive(deck.drawCard());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}

package second.domain;

import second.domain.card.provider.CardDeck;
import second.domain.gamer.Dealer;
import second.domain.gamer.Gamer;
import second.domain.gamer.Player;
import second.domain.answer.PlayerDecisions;
import second.domain.result.Results;

import java.util.Collections;
import java.util.List;

public class BlackJackGame {
    public static final int INITIAL_CARD_AMOUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;
    private final CardDeck cardDeck;

    public BlackJackGame(List<Player> players) {
        this.players = players;
        dealer = new Dealer();
        cardDeck = new CardDeck();
    }

    public void drawFirstPhase() {
        for (Player player : players) {
            drawInitialCardToEach(player);
        }

        drawInitialCardToEach(dealer);
    }

    private void drawInitialCardToEach(final Gamer gamer) {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            gamer.draw(cardDeck.pickCard());
        }
    }

    public void doDrawMorePhase(final PlayerDecisions playerDecisions) {
        for (final Player player : players) {
            turn(playerDecisions, player);
            playerDecisions.hands(player);
        }
        draw(dealer);
    }

    private void turn(final PlayerDecisions playerDecisions, final Gamer gamer) {
        while (gamer.canDrawMore()) {
            if (!playerDecisions.isHit(gamer))
                break;
            draw(gamer);
            playerDecisions.hands(gamer);
        }
    }

    private void draw(Gamer gamer) {
        gamer.draw(cardDeck.pickCard());
    }

    public Results calculateResults() {
        return Results.of(players, dealer);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}

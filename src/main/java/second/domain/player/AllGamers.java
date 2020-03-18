package second.domain.player;

import second.domain.card.CardProviable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllGamers {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public AllGamers(final List<Player> players) {
        this(new Dealer(), players);
    }

    public AllGamers(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawFirstPhase(final CardProviable cardProviable) {
        final List<Gamer> gamers = joinGamers();

        for (Gamer gamer : gamers) {
            drawInitialCardToEach(gamer, cardProviable);
        }
    }

    private List<Gamer> joinGamers() {
        final List<Gamer> gamers = new ArrayList<>(players);
        gamers.add(dealer);

        return gamers;
    }

    private void drawInitialCardToEach(final Gamer gamer, final CardProviable cardProvidable) {
        for (int i = 0; i < INITIAL_CARD_AMOUNT; i++) {
            gamer.drawCard(cardProvidable);
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Dealer getDealer() {
        return dealer;
    }
}


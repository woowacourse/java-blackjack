package second.domain.player;

import second.domain.ICardDeck;
import second.domain.result.PlayerResult;
import second.domain.result.ResultType;
import second.domain.result.Results;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AllGamers {
    private static final int INITIAL_CARD_AMOUNT = 2;

    private final Dealer dealer;
    private final List<Player> players;

    public AllGamers(List<Player> players) {
        this(new Dealer(), players);
    }

    public AllGamers(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void drawFirstPhase(ICardDeck iCardDeck) {
        List<Gamer> gamers = joinGamers();

        for (Gamer gamer : gamers) {
            drawInitialCardToEach(gamer, iCardDeck);
        }
    }

    private List<Gamer> joinGamers() {
        List<Gamer> gamers = new ArrayList<>(players);
        gamers.add(dealer);

        return gamers;
    }

    private void drawInitialCardToEach(Gamer gamer, ICardDeck cardProvidable) {
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


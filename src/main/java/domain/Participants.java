package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Participants {

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(List<String> playerNames, CardDistributor cardDistributor) {
        Dealer dealer = new Dealer(cardDistributor.distributeInitialCard());
        Players players = Players.of(playerNames, cardDistributor);

        return new Participants(dealer, players);
    }

    public Players getPlayers() {
        return players;
    }

    public List<Name> getPlayerNames() {
        return players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Name getDealerName() {
        return dealer.getName();
    }

    public Card showOneCardOfDealerCards() {
        return dealer.showOneCard();
    }


    public int getDealerCardsCount() {
        return dealer.getCards()
                .size();
    }

}

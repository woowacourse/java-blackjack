package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Users {
    private final Dealer dealer;
    private final List<Player> players;

    public Users(Dealer dealer, List<String> names) {
        this.dealer = dealer;
        this.players = names.stream()
                .map(Player::new)
                .collect(toList());
    }

    public void distributeToPlayer(Deck deck) {
        this.players.forEach(player -> player.distribute(deck.popTwo()));
    }

    public List<Cards> showCardsByPlayers() {
        List<Cards> cards = new ArrayList<>();
        this.players
                .forEach(player -> cards.add(new Cards(player.getCards())));
        return cards;
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(this.players.stream()
                .map(Player::getName)
                .collect(toList()));
    }

    public String getDealerName() {
        return this.dealer.getName();
    }

    public Dealer getDealer() {
        return this.dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}

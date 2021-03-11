package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Users {
    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();

    public Users(Dealer dealer, List<String> names, List<Integer> moneyGroup) {
        this.dealer = dealer;
        for (int i = 0; i < names.size(); i++) {
            this.players.add(new Player(names.get(i), moneyGroup.get(i)));
        }
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

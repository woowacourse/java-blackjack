package blackjack.domain;

import blackjack.domain.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Users {
    private final List<User> users = new ArrayList<>();

    public Users(Dealer dealer, List<String> players) {
        this.users.add(dealer);
        this.users.addAll(players.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public void initialHit(CardDeck cardDeck) {
        gerUsers()
                .forEach(user -> user.hit(cardDeck.drawCard(), cardDeck.drawCard()));
    }

    public List<Player> getPlayers() {
        return users.stream()
                .filter(User::isPlayer)
                .map(user -> (Player) user)
                .collect(Collectors.toList());
    }

    public List<User> gerUsers() {
        return Collections.unmodifiableList(this.users);
    }

    public Dealer getDealer() {
        return (Dealer) this.users.stream()
                .filter(User::isDealer)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("딜러가 존재하지 않습니다."));
    }

    public void stay() {
        users.stream().forEach(user -> user.state.stay());
    }
}

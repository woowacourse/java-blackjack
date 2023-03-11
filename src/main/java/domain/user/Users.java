package domain.user;

import domain.card.Card;
import domain.name.Names;
import java.util.List;
import java.util.stream.Collectors;

public class Users {


    private final Dealer dealer;
    private final Players players;

    private Users(final Dealer dealer, final Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Users from(final Names names) {
        Players players = Players.of(names);
        Dealer dealer = new Dealer();
        return new Users(dealer, players);
    }

    public void hitCardByName(final String name, final Card card) {
        findByName(name).hit(card);
    }

    private Player findByName(final String name) {
        return getPlayers().stream()
                .filter(player -> player.isRightName(name))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isHittableDealer() {
        return dealer.isHittable();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public List<Player> getHittablePlayers() {
        return players.getPlayers().stream()
                .filter(Player::isHittable)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

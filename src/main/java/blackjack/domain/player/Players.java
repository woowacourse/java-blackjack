package blackjack.domain.player;

import blackjack.domain.card.Deck;
import blackjack.domain.strategy.HitStrategy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "참가자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players fromNames(List<String> names, HitStrategy hitStrategy) {
        validate(names);
        List<Player> allPlayers = new ArrayList<>(toPlayers(names, hitStrategy));
        allPlayers.add(new Dealer());
        return new Players(allPlayers);
    }

    private static void validate(List<String> names) {
        Set<String> nameSet = new HashSet<>(names);
        if (names.size() != nameSet.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    private static List<Player> toPlayers(List<String> names, HitStrategy hitStrategy) {
        return names.stream()
                .map(name -> new Guest(name, hitStrategy))
                .collect(Collectors.toList());
    }

    public void initHit(Deck deck, int initDrawCount) {
        for (Player player : players) {
            hitCount(deck, initDrawCount, player);
        }
    }

    private void hitCount(Deck deck, int initDrawCount, Player player) {
        for (int i = 0; i < initDrawCount; i++) {
            player.hit(deck.draw());
        }
    }

    public Player findDealer() {
        return players.stream()
                .filter(this::isDealer)
                .findAny()
                .orElseThrow(() -> new RuntimeException("딜러가 없음..."));
    }

    public List<Player> getGuests() {
        return players.stream()
                .filter(player -> !isDealer(player))
                .collect(Collectors.toList());
    }

    private boolean isDealer(Player player) {
        return player instanceof Dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}

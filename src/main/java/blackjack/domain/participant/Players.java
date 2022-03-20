package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> value;

    public Players(final List<Player> value) {
        this.value = value;
    }

    public void initCards(final Deck deck) {
        value.forEach(player -> player.initCards(deck));
    }

    public Optional<Player> findDrawablePlayer() {
        return value.stream()
                .filter(Player::isDrawable)
                .findFirst();
    }

    public Player findByName(final String playerName) {
        return value.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow();
    }

    public List<String> getNames() {
        return value.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> getValue() {
        return new ArrayList<>(value);
    }
}

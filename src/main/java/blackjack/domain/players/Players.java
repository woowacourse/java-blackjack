package blackjack.domain.players;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.players.participant.Dealer;
import blackjack.domain.players.participant.Player;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class Players {
    private static final String NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";

    private final List<Player> value;

    public Players(final List<Player> value) {
        this.value = value;
        validateNames(getNames());
    }

    private void validateNames(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public List<String> getNames() {
        return value.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public void start(CardDeck cardDeck, BiConsumer<Player, CardDeck> startPlayer) {
        value.forEach(player -> startPlayer.accept(player, cardDeck));
    }

    public void printHand(Consumer<Player> playerConsumer) {
        value.forEach(playerConsumer);
    }

    public Map<Player, Integer> getPayouts(final Dealer dealer) {
        return value.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.getProfit(dealer)));
    }
}

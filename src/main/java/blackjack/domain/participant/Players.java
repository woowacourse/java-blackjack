package blackjack.domain.participant;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.result.PayoutCalculator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Players {
    private static final int INIT_CARD_COUNT = 2;
    private static final String NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";

    private final List<Player> value;

    public Players(final List<Player> rawValue) {
        this.value = rawValue;
        validateNames(getNames());
    }

    private void validateNames(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public Players initCard(CardDeck cardDeck) {
        value.forEach(player -> player.addCards(cardDeck.popCards(INIT_CARD_COUNT)));
        return this;
    }

    public void giveCard(CardDeck cardDeck) {
        value.forEach(player -> player.addCard(cardDeck.pop()));
    }

    public Map<Player, Integer> getPayouts(final Dealer dealer) {
        return value.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> PayoutCalculator.compute(player, dealer)));
    }

    public List<String> getNames() {
        return value.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    public List<Player> get() {
        return List.copyOf(value);
    }
}

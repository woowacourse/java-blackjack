package blackjack.domain.participant;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.participant.human.Dealer;
import blackjack.domain.participant.human.Player;
import blackjack.domain.participant.human.name.Name;
import blackjack.domain.result.Result;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {
    public static final int INIT_CARD_COUNT = 2;
    private static final String NAMES_DUPLICATED_ERROR_MESSAGE = "이름은 중복될 수 없습니다.";
    private final List<Player> value;

    private Players(final List<Player> rawValue) {
        this.value = rawValue;
        validateNames(getNames());
    }

    private static void validateNames(final List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new IllegalArgumentException(NAMES_DUPLICATED_ERROR_MESSAGE);
        }
    }

    public static Players fromRawValue(final List<Player> rawValue) {
        return new Players(rawValue);
    }

    public static Players fromNames(final List<Name> names) {
        return new Players(convertNamesToValue(names));
    }

    private static List<Player> convertNamesToValue(final List<Name> names) {
        return names.stream()
                .map(Player::fromName)
                .collect(Collectors.toList());
    }

    public Players initCard(CardDeck cardDeck) {
        value.forEach(player -> player.addCards(cardDeck.popCards(INIT_CARD_COUNT)));
        return this;
    }

    public void giveCard(CardDeck cardDeck) {
        value.forEach(player -> player.addCard(cardDeck.pop()));
    }

    public void calculateBettingMoney(Dealer dealer) {
        for (Player player : value) {
            Result result = player.calculateResult(dealer);
        }
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

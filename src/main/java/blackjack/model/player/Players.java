package blackjack.model.player;

import blackjack.model.card.CardDeck;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Participant> values;

    private Players(final List<Participant> values) {
        this.values = values;
    }

    public static Players from(final List<String> names) {
        checkDuplicateIn(names);
        List<Participant> values = names.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(values);
    }

    private static void checkDuplicateIn(final List<String> names) {
        if (names.size() != names.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
        }
    }

    public Players drawCardsBy(final CardDeck cardDeck) {
        List<Participant> newValues = values.stream()
                .map(player -> player.drawCardsBy(cardDeck))
                .collect(Collectors.toUnmodifiableList());
        return new Players(newValues);
    }

    public List<Participant> getValues() {
        return values;
    }
}


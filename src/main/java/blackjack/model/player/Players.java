package blackjack.model.player;

import blackjack.model.card.CardDeck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Participant> values;

    private Players(final List<Participant> values) {
        this.values = new ArrayList<>(values);
    }

    public static Players from(final List<String> names) {
        checkDuplicateIn(names);
        List<Participant> values = names.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(values);
    }

    private static void checkDuplicateIn(final List<String> names) {
        if (countDuplicateRemove(names) != names.size()) {
            throw new IllegalArgumentException("[ERROR] 참가자 이름 중 중복되는 이름이 있습니다.");
        }
    }

    private static long countDuplicateRemove(List<String> names) {
        return names.stream()
                .distinct()
                .count();
    }

    public Players drawCardsBy(final CardDeck cardDeck) {
        List<Participant> copyOfValues = values.stream()
                .map(player -> player.drawCardsBy(cardDeck))
                .collect(Collectors.toUnmodifiableList());
        return new Players(copyOfValues);
    }

    public List<Participant> getPlayerGroup() {
        return values.stream()
                .map(Participant::getCopyInstance)
                .collect(Collectors.toUnmodifiableList());
    }
}


package blackjack.model.player;

import blackjack.model.card.CardDeck;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Players {
    private final List<Participant> values;

    private Players(final List<Participant> values) {
        this.values = values;
    }

    public static Players from(final List<String> names) {
        List<Participant> values = names.stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(values);
    }

    public Players drawCardsBy(final CardDeck cardDeck) {
        List<Participant> newValues = values.stream()
                .map(player -> player.drawCardsBy(cardDeck))
                .collect(Collectors.toUnmodifiableList());
        return new Players(newValues);
    }

    public void hitOrStayBy(CardDeck cardDeck, Predicate<String> predicate, BiConsumer<String, List<String>> consumer) {
        for (Participant player : values) {
            player.hitOrStayBy(cardDeck, predicate, consumer);
        }
    }

    public List<Participant> getValues() {
        return values;
    }
}


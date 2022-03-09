package model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void popCard() {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = IntStream.range(0, 52)
                .mapToObj(i -> cardDeck.drawCard())
                .collect(Collectors.toList());
        assertThat(cards.size()).isEqualTo(52);
        assertThat(cards.size()).isEqualTo(Set.copyOf(cards).size());
    }
}

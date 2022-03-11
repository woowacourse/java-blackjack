package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class CardDeckTest {

    @Test
    void drawCard() {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = drawAllCards(cardDeck);
        assertThat(cards.size()).isEqualTo(52);
        assertThat(cards.size()).isEqualTo(Set.copyOf(cards).size());
    }

    @Test
    void drawEmptyDeck() {
        CardDeck cardDeck = new CardDeck();
        drawAllCards(cardDeck);
        assertThatThrownBy(() -> cardDeck.drawCard())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("카드를 모두 소진했습니다!!");
    }

    private List<Card> drawAllCards(CardDeck cardDeck) {
        return IntStream.range(0, 52)
                .mapToObj(i -> cardDeck.drawCard())
                .collect(Collectors.toList());
    }
}

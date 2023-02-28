package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeckTest {

    private List<Card> cards;

    @BeforeEach
    void createCards() {
        cards = new ArrayList<>();
        for (Symbol symbol : Symbol.values()) {
            for (Number number : Number.values()) {
                cards.add(new Card(symbol, number));
            }
        }
    }

    @DisplayName("카드 덱은 52장의 카드가 아니면 예외를 발생시킨다. (51장)")
    @Test
    void should_ThrowException_When_51Cards() {
        cards.remove(0);
        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드들의 갯수는 52장이어야 합니다.");
    }

    @DisplayName("카드 덱은 52장의 카드가 아니면 예외를 발생시킨다. (53장)")
    @Test
    void should_ThrowException_When_53Cards() {
        cards.add(new Card(Symbol.DIAMOND, Number.JACK));
        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드들의 갯수는 52장이어야 합니다.");
    }

    @DisplayName("카드 덱은 52장의 카드를 가진다.")
    @Test
    void should_NotThrowException_When_52Cards() {
        assertThatCode(() -> new Deck(cards))
                .doesNotThrowAnyException();
    }
}

package blackjack.domain;

import static blackjack.domain.Number.*;
import static blackjack.domain.Symbol.CLUB;
import static blackjack.domain.Symbol.DIAMOND;
import static blackjack.domain.Symbol.SPADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
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
            for (Number number : values()) {
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
        cards.add(new Card(DIAMOND, JACK));
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

    @DisplayName("카드 덱은 중복을 허용하지 않는다.")
    @Test
    void should_ThrowException_When_Duplicated() {
        cards.remove(0);
        cards.add(new Card(CLUB, JACK));
        assertThatThrownBy(() -> new Deck(cards))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드 덱은 중복될 수 없습니다.");
    }

    @DisplayName("카드 덱에서 주어진 장수만큼 카드를 뽑는다.")
    @Test
    void should_DrawCards_As_Count() {
        Deck deck = new Deck(cards);
        assertThat(deck.draw(2))
                .containsExactly(new Card(SPADE, ACE), new Card(SPADE, TWO));
    }
}

package blakjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CardDeckTest {
    @Test
    @DisplayName("모든 카드 소진시 예외 발생")
    void exception() {
        final CardDeck deck = new CardDeck();

        assertThatThrownBy(() -> {
            while (true) {
                deck.draw();
            }
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("중복없이 생성됐는지 테스트")
    void checkDuplication() {
        final List<Card> cards = new ArrayList<>();
        final CardDeck deck = new CardDeck();

        try {
            while (true) {
                cards.add(deck.draw());
            }
        } catch (final NoSuchElementException ignored) {
        } finally {
            final var before = cards.size();
            final var after = cards.stream().distinct().count();

            assertThat(before).isEqualTo(after);
        }
    }

    @Test
    @DisplayName("52 장 생성됐는지 사이즈 확인")
    void checkSize() {
        final List<Card> cards = new ArrayList<>();
        final CardDeck deck = new CardDeck();

        try {
            while (true) {
                cards.add(deck.draw());
            }
        } catch (final NoSuchElementException ignored) {
        } finally {
            assertThat(cards.size()).isEqualTo(52);
        }
    }
}

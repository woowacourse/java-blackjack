package blackjack.domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DeckTest {
    private static final int DECK_SIZE = 52;

    @DisplayName("덱에서 52장의 카드를 생성하는지")
    @Test
    void testCreateDeck() {
        //given
        Deck deck = Deck.createDeck();
        //when //then
        assertThat(deck.size()).isEqualTo(DECK_SIZE);
    }

    @DisplayName("덱에서 카드 한장을 발급하는 지 테스트")
    @Test
    void testDraw() {
        //given
        Deck deck = Deck.createDeck();
        //when //then
        assertThat(deck.draw()).isInstanceOf(Card.class);
    }

    @DisplayName("덱에 남은 카드가 없을 경우 예외를 던지는지 테스트")
    @Test
    void whenDeckEmpty() {
        //given
        Deck deck = Deck.createDeck();
        //when
        for (int i = 0; i < DECK_SIZE; i++) {
            deck.draw();
        }
        //then
        assertThatThrownBy(deck::draw)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("덱에 더 이상 남은 카드가 없습니다.");
    }
}

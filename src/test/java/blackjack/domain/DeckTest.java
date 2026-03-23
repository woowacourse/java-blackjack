package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    @Test
    @DisplayName("52장의 중복 없는 트럼프 카드로 구성된 덱을 생성한다.")
    void createDeck() {
        // given & when
        Deck deck = Deck.createdDeck();
        List<Card> cards = deck.getCards();

        // then
        assertAll(
            () -> assertThat(cards).hasSize(52),
            () -> assertThat(cards).doesNotHaveDuplicates()
        );
    }

    @Test
    @DisplayName("빈 카드 뭉치에서 카드를 뽑으려 하면 예외가 발생한다.")
    void drawFromEmptyDeckThrowsException() {
        // given
        Deck emptyDeck = Deck.from(List.of());

        // when, then
        assertThatThrownBy(emptyDeck::draw)
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("뽑을 카드가 남아있지 않습니다.");
    }
}

package domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomCardGenerator;

class DeckCardsTest {

    @Test
    @DisplayName("성공: 객체 생성 시 52장의 카드를 가진다")
    void from_NoException() {
        assertThatCode(() -> DeckCards.from(new RandomCardGenerator()))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공: 덱에서 카드 한 장 빼기")
    void draw_NoException() {
        DeckCards deckCards = DeckCards.from(new SequentialCardGenerator());
        assertThat(deckCards.drawCard()).isEqualTo(new Card(Rank.KING, Symbol.CLUB));
    }

    @Test
    @DisplayName("실패: 덱에 카드가 없는 상태에서 카드 한 장 빼기")
    void draw_Exception_NoCardsLeft() {
        DeckCards deckCards = DeckCards.from(new RandomCardGenerator());
        deckCards.drawCards(52);
        assertThatThrownBy(deckCards::drawCard)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 모두 사용하였습니다.");
    }
}

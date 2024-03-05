package domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomCardGenerator;

class DeckCardsTest {

    @Test
    @DisplayName("성공: 객체 생성 시 52장의 카드를 가진다")
    void from_NoException() {
        Assertions.assertThatCode(() -> DeckCards.from(RandomCardGenerator.initialize()))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공: 덱에서 카드 한 장 빼기")
    void draw_NoException() {
        DeckCards deckCards = DeckCards.from(SequentialCardGenerator.initialize());
        Card drawnCard = deckCards.draw();
        Assertions.assertThat(drawnCard.getRank()).isEqualTo(Rank.KING);
        Assertions.assertThat(drawnCard.getSymbol()).isEqualTo(Symbol.CLUB);
    }

    @Test
    @DisplayName("실패: 덱에 카드가 없는 상태에서 카드 한 장 빼기")
    void draw_Exception_NoCardsLeft() {
        DeckCards deckCards = DeckCards.from(SequentialCardGenerator.initialize());
        for (int i = 0; i < 52; i++) {
            deckCards.draw();
        }
        Assertions.assertThatThrownBy(deckCards::draw)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 모두 사용하였습니다.");
    }
}

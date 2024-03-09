package domain.card;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import strategy.RandomCardGenerator;

class DeckCardsTest {

    @Test
    @DisplayName("성공: 객체 생성 시 52장의 카드를 가진다")
    void from_NoException() {
        Assertions.assertThatCode(() -> DeckCards.from(new RandomCardGenerator()))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("성공: 덱에서 카드 한 장 빼기")
    void drawCard_NoException() {
        DeckCards deckCards = DeckCards.from(new SequentialCardGenerator());
        Card drawnCard = deckCards.drawCard();
        Assertions.assertThat(drawnCard.getRank()).isEqualTo(Rank.ACE);
        Assertions.assertThat(drawnCard.getSymbol()).isEqualTo(Symbol.SPADE);
    }

    @Test
    @DisplayName("성공: 덱에서 카드 여러 장 빼기")
    void drawCards_NoException() {
        DeckCards deckCards = DeckCards.from(new SequentialCardGenerator());
        List<Card> drawnCards = deckCards.drawCards(2);
        Card firstCard = drawnCards.get(0);
        Card secondCard = drawnCards.get(1);

        Assertions.assertThat(firstCard.getRank()).isEqualTo(Rank.ACE);
        Assertions.assertThat(firstCard.getSymbol()).isEqualTo(Symbol.SPADE);
        Assertions.assertThat(secondCard.getRank()).isEqualTo(Rank.TWO);
        Assertions.assertThat(secondCard.getSymbol()).isEqualTo(Symbol.SPADE);
    }

    @Test
    @DisplayName("실패: 덱에 카드가 없는 상태에서 카드 한 장 빼기")
    void drawCard_Exception_NoCardsLeft() {
        DeckCards deckCards = DeckCards.from(new OneCardGenerator());
        deckCards.drawCard();
        Assertions.assertThatThrownBy(deckCards::drawCard)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("[ERROR] 카드를 모두 사용하였습니다.");
    }

    @Test
    @DisplayName("실패: 덱에 카드가 없는 상태에서 카드 여러 장 빼기")
    void drawCards_Exception_NoCardsLeft() {
        DeckCards deckCards = DeckCards.from(new OneCardGenerator());
        deckCards.drawCard();
        Assertions.assertThatThrownBy(() -> deckCards.drawCards(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]덱에 남은 카드 수가 요청한 카드 수보다 적습니다.");
    }
}

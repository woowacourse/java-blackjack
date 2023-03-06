package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.util.Lists.newArrayList;

class DeckTest {

    @Test
    @DisplayName("트럼프 기본 카드 생성 테스트")
    void generateTrumpSuccess() {
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(newArrayList(3, 51, 30));

        assertThatCode(() -> new Deck(testNumberGenerator)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("올바른 인덱스에 있는 카드가 반환되는지 확인")
    void checkRightCardReturned() {
        TestNumberGenerator testNumberGenerator = new TestNumberGenerator(newArrayList(3, 50, 30));
        Deck deck = new Deck(testNumberGenerator);

        Card card1 = deck.getCard();
        Assertions.assertThat(card1.getTrumpShape()).isEqualTo(CardShape.SPADE);
        Assertions.assertThat(card1.getTrumpNumber()).isEqualTo(CardNumber.FOUR);
        Card card2 = deck.getCard();
        Assertions.assertThat(card2.getTrumpShape()).isEqualTo(CardShape.CLOVER);
        Assertions.assertThat(card2.getTrumpNumber()).isEqualTo(CardNumber.KING);
        Card card3 = deck.getCard();
        Assertions.assertThat(card3.getTrumpShape()).isEqualTo(CardShape.HEART);
        Assertions.assertThat(card3.getTrumpNumber()).isEqualTo(CardNumber.SIX);
    }
}

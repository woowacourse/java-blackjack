package blackjack.model;

import blackjack.exception.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HandCardsTest {

    @Test
    @DisplayName("카드의 숫자 합을 계산한다")
    void calculateScoreTest() {
        // given
        HandCards handCards = new HandCards();
        Card cardTen = new Card(Figure.SPADE, Number.TEN);
        Card cardTwo = new Card(Figure.SPADE, Number.TWO);

        // when
        handCards.addCard(cardTen);
        handCards.addCard(cardTwo);

        Score score = handCards.calculate();
        // then
        assertThat(score.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스가 2개일 때, 합이 21을 넘지 않도록 하나는 1로, 하나는 11로 계산한다")
    void calculateAceTwoTest() {
        // given
        HandCards handCards = new HandCards();
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        Card cardAce2 = new Card(Figure.DIAMOND, Number.ACE);
        // when
        handCards.addCard(cardAce);
        handCards.addCard(cardAce2);
        Score score = handCards.calculate();
        // then
        assertThat(score.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("에이스가 2개여도 다른 카드와의 합이 21을 넘으면 둘 다 1로 계산한다")
    void calculateAceTwo() {
        // given
        HandCards handCards = new HandCards();
        Card cardAce = new Card(Figure.SPADE, Number.ACE);
        Card cardAce2 = new Card(Figure.DIAMOND, Number.ACE);
        Card cardTen = new Card(Figure.DIAMOND, Number.TEN);
        // when
        handCards.addCard(cardAce);
        handCards.addCard(cardAce2);
        handCards.addCard(cardTen);
        Score score = handCards.calculate();
        // then
        assertThat(score.getScore()).isEqualTo(12);
    }

    @Test
    @DisplayName("카드 한장을 뽑을 때 비어있으면 예외처리한다.")
    void getFirstCardIsEmptyTest() {
        // given
        HandCards handCards = new HandCards();
        // when
        // then
        assertThatThrownBy(handCards::getFirstCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.CARD_EMPTY.getMessage());
    }
}

package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {
    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 없는 경우)")
    void calculateCardsSum() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.THREE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.THREE);
        List<Card> cards = List.of(card1, card2);

        // when
        int actual = Score.from(cards).getValue();

        // then
        assertThat(actual).isEqualTo(6);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (에이스가 있는 경우)")
    void calculateCardsSumWithACE() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.ACE);
        Card card2 = new Card(Pattern.CLOVER, Denomination.ACE);
        Card card3 = new Card(Pattern.HEART, Denomination.ACE);
        Card card4 = new Card(Pattern.SPADE, Denomination.ACE);
        List<Card> cards = List.of(card1, card2, card3, card4);

        // when
        int actual = Score.from(cards).getValue();

        // then
        assertThat(actual).isEqualTo(14);
    }

    @Test
    @DisplayName("카드의 점수 총합을 계산한다. (총 합이 21이 넘는 경우)")
    void calculateCardsSumOver21() {
        // given
        Card card1 = new Card(Pattern.DIAMOND, Denomination.TEN);
        Card card2 = new Card(Pattern.CLOVER, Denomination.TEN);
        Card card3 = new Card(Pattern.HEART, Denomination.TWO);
        List<Card> cards = List.of(card1, card2, card3);

        // when
        int actual = Score.from(cards).getValue();

        // then
        assertThat(actual).isEqualTo(22);
    }
}

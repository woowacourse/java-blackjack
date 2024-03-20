package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFixture;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Score;
import blackjack.domain.card.Suit;
import blackjack.domain.state.Hand;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HandTest {

    @DisplayName("핸드에 에이스가 두 장 이상이면 한 장만 11점으로 계산한다")
    @Test
    public void calculateTotalScore() {
        Hand hand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.ACE));

        assertThat(hand.sumScores()).isEqualTo(Score.from(12));
    }

    @DisplayName("다른 카드와 ACE를 11로 계산했을 때 21이 넘는다면 ACE를 1로 계산한다")
    @Test
    public void calculateAceToOne() {
        Hand hand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.ACE),
                CardFixture.fromSuitCloverWith(Denomination.JACK),
                CardFixture.fromSuitCloverWith(Denomination.KING));

        assertThat(hand.sumScores()).isEqualTo(Score.from(21));
    }

    @DisplayName("핸드에 카드를 추가하면 해당 카드가 추가된 새로운 핸드가 반환된다")
    @Test
    public void add() {
        Hand hand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.ACE));

        Hand newHand = hand.add(CardFixture.fromSuitCloverWith(Denomination.ACE));
        List<Card> cards = newHand.getCards();
        Card addedCard = cards.get(cards.size() - 1);

        assertThat(cards.size()).isEqualTo(3);
        assertThat(addedCard.getSuit()).isEqualTo(Suit.CLOVER);
        assertThat(addedCard.getDenomination()).isEqualTo(Denomination.ACE);
    }

    @DisplayName("초기화된 2장의 카드가 들어있는 핸드의 합이 21이면 블랙잭이다")
    @Test
    public void blackJack() {
        Hand initialHand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.ACE));

        assertThat(initialHand.isBlackJack()).isTrue();
    }

    @DisplayName("핸드가 21점 이하면 버스트가 아니다")
    @Test
    public void isNotBustTrue() {
        Hand hand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.ACE));

        assertThat(hand.isNotBust()).isTrue();
    }

    @DisplayName("핸드가 21점 초과면 버스트다")
    @Test
    public void isBustTrue() {
        Hand hand = Hand.of(CardFixture.fromSuitCloverWith(Denomination.TEN),
                CardFixture.fromSuitCloverWith(Denomination.KING),
                CardFixture.fromSuitCloverWith(Denomination.TWO));

        assertThat(hand.isBust()).isTrue();
    }
}

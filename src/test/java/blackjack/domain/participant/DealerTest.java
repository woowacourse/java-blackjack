package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Kind;
import blackjack.domain.card.Value;
import blackjack.domain.participant.Dealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DealerTest {

    @DisplayName("딜러가 아직 가진 패가 없다면 첫장 공개를 요청할 수 없다")
    @Test
    void should_ThrowIllegalStateException_When_RequestShowCards_But_DealerHasNoCards() {
        Dealer dealer = new Dealer();
        assertThatThrownBy(dealer::getFirstCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 지니고 있는 카드가 없습니다");
    }

    @DisplayName("딜러는 자신의 패가 16이하이면 한장을 더 받는다")
    @Test
    void should_AddCard_When_HandsScoreBelowThreshold() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Kind.SPADE, Value.JACK));
        dealer.addCard(new Card(Kind.HEART, Value.SIX));

        assertTrue(dealer::canAddCard);
    }

    @DisplayName("딜러는 자신의 패가 17이상이면 한장을 더 받을 수 없다")
    @Test
    void should_NotAddCard_When_HandsScoreOverThreshold() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Kind.SPADE, Value.JACK));
        dealer.addCard(new Card(Kind.HEART, Value.SEVEN));

        assertFalse(dealer::canAddCard);
    }

    @DisplayName("딜러는 첫번째 패의 첫장을 보여준다")
    @Test
    void should_ShowFirstCard() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Kind.SPADE, Value.JACK));
        dealer.addCard(new Card(Kind.HEART, Value.SIX));

        assertThat(dealer.getFirstCard())
                .isEqualTo(new Card(Kind.SPADE, Value.JACK));
    }
}

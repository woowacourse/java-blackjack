package blackjack.domain.participant;

import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_SEVEN;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.participant.state.HitState;
import blackjack.domain.participant.state.StandState;

class DealerTest {

    @DisplayName("딜러는 2장의 카드를 가진채 게임을 시작해야 한다.")
    @Test
    void readyToPlayTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_ACE, SPADE_TWO));
        final List<Card> actualCards = dealer.getCards();
        assertThat(actualCards).isEqualTo(List.of(SPADE_ACE, SPADE_TWO));
    }

    @DisplayName("딜러는 카드를 뽑을 수 있어야 한다.")
    @Test
    void drawCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_ACE, SPADE_TWO));
        dealer.drawCard(SPADE_THREE);

        final List<Card> actualCards = dealer.getCards();
        final List<Card> expectedCards = List.of(SPADE_ACE, SPADE_TWO, SPADE_THREE);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    @DisplayName("딜러는 카드의 합이 21 이상이 아니어도 17 이상이면 Stand 상태로 변해야 한다.")
    @Test
    void cannotDrawCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_TEN, SPADE_SEVEN));
        assertThat(dealer.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("딜러는 카드를 뽑았을 때의 합이 17 미만이면 Hit 상태를 유지해야 한다.")
    @Test
    void keepHitStateWhenDrawCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_TEN, SPADE_FIVE));
        dealer.drawCard(SPADE_ACE);
        assertThat(dealer.getState()).isInstanceOf(HitState.class);
    }

    @DisplayName("딜러는 카드를 뽑았을 때의 합이 21 미만 17 이상이면 Stand 상태로 변해야 한다.")
    @Test
    void turnToStandStateWhenDrawCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_TEN, SPADE_FIVE));
        dealer.drawCard(SPADE_TWO);
        assertThat(dealer.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("첫번째 카드 한장을 반환할 수 있어야 한다.")
    @Test
    void getFirstCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_TEN, SPADE_FIVE));
        assertThat(dealer.getFirstCard()).isEqualTo(SPADE_TEN);
    }

}

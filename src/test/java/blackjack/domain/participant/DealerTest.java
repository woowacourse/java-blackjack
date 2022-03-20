package blackjack.domain.participant;

import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_TEN;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.participant.state.HitState;
import blackjack.domain.participant.state.StandState;

class DealerTest {

    @DisplayName("딜러는 2장의 카드를 가진채 게임을 시작해야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForReaderToPlayTest")
    void readyToPlayTest(final List<Card> expectedCards) {
        final Dealer dealer = Dealer.readyToPlay(expectedCards);
        final List<Card> actualCards = dealer.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    @DisplayName("딜러는 카드를 뽑을 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 초기 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForDrawCardTest")
    void drawCardTest(final List<Card> cards, final Card drewCard) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        dealer.drawCard(drewCard);

        final List<Card> actualCards = dealer.getCards();
        final List<Card> expectedCards = concatCards(cards, drewCard);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    @DisplayName("딜러의 초기 카드의 합이 17 이상 21 미만이면 Stand 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 초기 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForChangeToStandWhenInitializedTest")
    void changeToStandWhenInitializedTest(final List<Card> cards) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        assertThat(dealer.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("딜러는 카드를 뽑았을 때의 합이 17 이상 21 이하면 Stand 상태로 변해야 한다.")
    @ParameterizedTest(name = "[{index}] 초기 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForChangeToStandWhenDrawCardTest")
    void changeToStandWhenDrawCardTest(final List<Card> cards, final Card drewCard) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        dealer.drawCard(drewCard);
        assertThat(dealer.getState()).isInstanceOf(StandState.class);
    }

    @DisplayName("딜러는 카드를 뽑았을 때의 합이 17 미만이면 Hit 상태를 유지해야 한다.")
    @ParameterizedTest(name = "[{index}] 초기 카드 : {0}, 뽑은 카드 : {1}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForKeepHitStateWhenDrawCardTest")
    void keepHitStateWhenDrawCardTest(final List<Card> cards, final Card drewCard) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        dealer.drawCard(drewCard);
        assertThat(dealer.getState()).isInstanceOf(HitState.class);
    }

    @DisplayName("첫번째 카드 한장을 반환할 수 있어야 한다.")
    @Test
    void getFirstCardTest() {
        final Dealer dealer = Dealer.readyToPlay(List.of(SPADE_TEN, SPADE_FIVE));
        assertThat(dealer.getFirstCard()).isEqualTo(SPADE_TEN);
    }
    @DisplayName("카드의 합계를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 합계 : {1}, 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForGetScoreTest")
    void getScoreTest(final List<Card> cards, final Card drewCard, final int expectedScore) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        dealer.drawCard(drewCard);

        final int actualScore = dealer.getScore();
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @DisplayName("보유한 카드를 확인할 수 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 카드 : {0}")
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForGetCardsTest")
    void getCardsTest(final List<Card> cards, final Card drewCard) {
        final Dealer dealer = Dealer.readyToPlay(cards);
        dealer.drawCard(drewCard);

        final List<Card> actualCards = dealer.getCards();
        final List<Card> expectedCards = concatCards(cards, drewCard);
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private List<Card> concatCards(final List<Card> cards1, final Card card) {
        final List<Card> cards = new ArrayList<>(cards1);
        cards.add(card);
        return cards;
    }


}

package domain.cardtable;

import domain.card.Card;
import domain.deck.CardDeck;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardShape.HEART;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardTableTest {

    Participant participant;

    Dealer dealer;

    CardDeck cardDeck;

    CardTable cardTable;

    @BeforeEach
    void initCardArea() {
        cardDeck = CardDeck.shuffledFullCardDeck();
        cardTable = CardTable.readyToPlayBlackjack(cardDeck);

        participant = new Participant(new Name("name"), Money.wons(10000));
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(CLOVER, NINE));

        dealer = new Dealer();
        dealer.hit(new Card(DIAMOND, TEN));
        dealer.hit(new Card(DIAMOND, NINE));
    }

    @Test
    @DisplayName("determineBettingMoney() : 참여자가 bust 일 경우에는 참여자가 무조건 게임에서 진다.")
    void test_matchBetween_bust_participant_must_lose_participant() throws Exception {
        //given
        participant.hit(new Card(SPADE, TEN));

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineBettingMoney(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(Money.wons(-10000))
        );
    }

    @Test
    @DisplayName("determineBettingMoney() : 딜러는 bust 이면서 참여자가 bust 가 아니면 참여자가 무조건 게임에서 이긴다.")
    void test_matchBetween_bust_dealer_must_lose_dealer() throws Exception {
        //given
        dealer.hit(new Card(SPADE, TEN));

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineBettingMoney(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(Money.wons(10000))
        );
    }

    @ParameterizedTest
    @MethodSource("makeBothNotBust")
    @DisplayName("determineBettingMoney() : 딜러, 참여자 모두 버스트가 아닐 때 점수가 높은 쪽이 이기고, 같으면 무승부이다.")
    void test_matchBetween_not_bust_win_higher_score_or_draw_same_score(
            final Participant participant, final Dealer dealer,
            final Money resultBettingMoney) throws Exception {

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineBettingMoney(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(resultBettingMoney)
        );
    }

    static Stream<Arguments> makeBothNotBust() {

        //무승부
        final Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.hit(new Card(DIAMOND, TEN));
        participant1.hit(new Card(SPADE, TEN));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(HEART, TEN));
        dealer1.hit(new Card(CLOVER, TEN));

        //참여자가 이길 경우
        final Participant participant2 = new Participant(new Name("name2"), Money.wons(1000));
        participant2.hit(new Card(SPADE, TEN));
        participant2.hit(new Card(DIAMOND, TEN));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(HEART, TEN));
        dealer2.hit(new Card(CLOVER, NINE));

        //딜러가 이길 경우
        final Participant participant3 = new Participant(new Name("name3"), Money.wons(4000));
        participant3.hit(new Card(SPADE, TEN));
        participant3.hit(new Card(DIAMOND, NINE));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(HEART, TEN));
        dealer3.hit(new Card(CLOVER, TEN));

        return Stream.of(
                Arguments.of(participant1, dealer1, Money.wons(10000)),
                Arguments.of(participant2, dealer2, Money.wons(1000)),
                Arguments.of(participant3, dealer3, Money.wons(-4000))
        );
    }

    @Test
    @DisplayName("dealCardTo() : Player에게 카드를 나눠줄 수 있다.")
    void test_dealCardTo() throws Exception {
        //when & then
        assertAll(
                () -> assertTrue(cardTable.dealCardTo(participant)),
                () -> assertFalse(cardTable.dealCardTo(dealer)),
                () -> assertThat(participant.cardArea().cards()).hasSize(3),
                () -> assertThat(dealer.cardArea().cards()).hasSize(2)
        );
    }

    @ParameterizedTest
    @MethodSource("afterFirstDeal")
    @DisplayName("matchAfterFirstDeal() : 처음 두장씩 받고 블랙잭 여부에 따라 승부가 결정날 수 있다.")
    void test_matchAfterFirstDeal(final Participant participant,
                                  final Dealer dealer,
                                  final BetResultState betResultState) throws Exception {
        //when
        cardTable.matchAfterFirstDeal(List.of(participant), dealer);

        //then
        assertThat(participant).extracting("betResultState")
                               .isNotNull()
                               .isInstanceOf(betResultState.getClass());
    }

    @Test
    @DisplayName("matchAfterFirstDeal() : 딜러, 참여자가 모두 블랙잭이 아닐 경우 승부가 결정나지 않는다.")
    void test_test_matchAfterFirstDeal_stateNull() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name4"), Money.wons(0));
        participant.hit(new Card(HEART, TEN));
        participant.hit(new Card(CLOVER, NINE));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(DIAMOND, TEN));
        dealer.hit(new Card(DIAMOND, NINE));

        //when
        cardTable.matchAfterFirstDeal(List.of(participant), dealer);

        //then
        assertThat(participant).extracting("betResultState")
                               .isNull();
    }

    static Stream<Arguments> afterFirstDeal() {

        /**
         * 참여자 : 블랙잭
         * 딜러 : 블랙잭
         * 결과 : 무승부
         */
        final Participant participant1 = new Participant(new Name("name1"), Money.wons(0));
        participant1.hit(new Card(DIAMOND, TEN));
        participant1.hit(new Card(DIAMOND, ACE));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(HEART, TEN));
        dealer1.hit(new Card(HEART, ACE));

        /**
         * 참여자 : 블랙잭
         * 딜러 : 블랙잭 X
         * 결과 : 참여자 승리
         */
        final Participant participant2 = new Participant(new Name("name2"), Money.wons(0));
        participant2.hit(new Card(DIAMOND, TEN));
        participant2.hit(new Card(DIAMOND, ACE));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(HEART, TEN));
        dealer2.hit(new Card(CLOVER, NINE));

        /**
         * 참여자 : 블랙잭 X
         * 딜러 : 블랙잭
         * 결과 : 딜러 승
         */
        final Participant participant3 = new Participant(new Name("name3"), Money.wons(0));
        participant3.hit(new Card(HEART, TEN));
        participant3.hit(new Card(CLOVER, NINE));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(DIAMOND, TEN));
        dealer3.hit(new Card(DIAMOND, ACE));

        return Stream.of(
                Arguments.of(participant1, dealer1, new BreakEvenState()),
                Arguments.of(participant2, dealer2, new WinState()),
                Arguments.of(participant3, dealer3, new LoseState())
        );
    }
}

package domain.player.participant;

import domain.card.Card;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.participant.betresult.BetResultState;
import domain.player.participant.betresult.BreakEvenState;
import domain.player.participant.betresult.LoseState;
import domain.player.participant.betresult.NotYetState;
import domain.player.participant.betresult.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardShape.HEART;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.EIGHT;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.TEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Participant 은")
class ParticipantTest {

    final Participant participant = new Participant(new Name("name"), Money.wons(0));

    @BeforeEach
    void makeCardScoreTwenty() {
        participant.hit(new Card(CLOVER, TEN));
        participant.hit(new Card(SPADE, TEN));
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 미만일 경우 카드를 더 받을 수 있다.")
    void test_canHit_underScore21() {
        // when & then
        assertTrue(participant.canHit());
    }

    @Test
    @DisplayName("canHit() : 참여자는 21점 이상일 경우 카드를 더 받을 수 없다.")
    void test_canHit_overScore21() {
        // when
        participant.hit(new Card(CLOVER, ACE));

        // then
        assertFalse(participant.canHit());
    }

    @Test
    @DisplayName("determineBetState() : 참여자는 베팅 결과를 결정할 수 있다.")
    void test_determineBetState() throws Exception {
        //given
        assertThat(participant).extracting("betResultState")
                               .isNull();

        //when
        participant.determineBetState(new BreakEvenState());

        //then
        assertThat(participant).extracting("betResultState")
                               .isInstanceOf(BetResultState.class);
    }

    @ParameterizedTest
    @MethodSource("betResultState")
    @DisplayName("determineBetMoney() : 참여자는 베팅 결과에 따라 베팅 금액을 반환할 수 있다.")
    void test_determineBetMoney(final Participant participant, final Money bettingResultMoney,
                                final BetResultState betResultState) throws Exception {
        //when
        Money money = participant.determineBetMoney(betResultState);

        //then
        assertEquals(bettingResultMoney, money);
    }

    static Stream<Arguments> betResultState() {

        Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.determineBetState(new WinState());

        Participant participant2 = new Participant(new Name("name2"), Money.wons(5000));
        participant2.determineBetState(new LoseState());

        Participant participant3 = new Participant(new Name("name3"), Money.wons(1000));
        participant3.determineBetState(new BreakEvenState());

        return Stream.of(
                Arguments.of(participant1, Money.wons(15000), new WinState()),
                Arguments.of(participant2, Money.wons(-5000), new LoseState()),
                Arguments.of(participant3, Money.wons(1000), new BreakEvenState())
        );
    }

    @Test
    @DisplayName("hasNotBetState() : 배팅금액상태가 결정되지 않을 수 있다.")
    void test_hasNotBetState() throws Exception {
        //when & then
        assertTrue(participant.hasNotBetState());
    }

    @ParameterizedTest
    @MethodSource("firstMatch")
    @DisplayName("firstMatchWith() : 처응 두 장 카드를 받고나서 딜러와 블랙젹 여부를 비교하여 배팅 결정 상태를 결정할 수 있다.")
    void test_firstMatchWith(final Participant participant,
                             final Dealer dealer,
                             final BetResultState resultState) throws Exception {

        //when
        BetResultState betResultState = participant.firstMatchWith(dealer);

        //then
        assertThat(betResultState).isInstanceOf(resultState.getClass());
    }

    static Stream<Arguments> firstMatch() {

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

        /**
         * 참여자 : 블랙잭 X
         * 딜러 : 블랙잭 X
         * 결과 : 승부가 아직 안난 상태
         */
        final Participant participant4 = new Participant(new Name("name3"), Money.wons(0));
        participant4.hit(new Card(HEART, TEN));
        participant4.hit(new Card(CLOVER, NINE));

        final Dealer dealer4 = new Dealer();
        dealer4.hit(new Card(DIAMOND, TEN));
        dealer4.hit(new Card(DIAMOND, EIGHT));

        return Stream.of(
                Arguments.of(participant1, dealer1, new BreakEvenState()),
                Arguments.of(participant2, dealer2, new WinState()),
                Arguments.of(participant3, dealer3, new LoseState()),
                Arguments.of(participant4, dealer4, new NotYetState())
        );
    }

    @ParameterizedTest
    @MethodSource("finalMatch")
    @DisplayName("finalMatchWith() : 플레이어와 딜러가 카드를 전부 다 받고 나서 승부 결정 상태를 결정할 수 있다.")
    void test_finalMatchWith(final Participant participant,
                             final Dealer dealer,
                             final BetResultState resultState) throws Exception {
        //when
        BetResultState betResultState = participant.finalMatchWith(dealer);

        //then
        assertThat(betResultState).isInstanceOf(resultState.getClass());
    }

    static Stream<Arguments> finalMatch() {

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
                Arguments.of(participant1, dealer1, new BreakEvenState()),
                Arguments.of(participant2, dealer2, new BreakEvenState()),
                Arguments.of(participant3, dealer3, new LoseState())
        );
    }
}

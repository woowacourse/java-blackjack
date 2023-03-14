package domain.player.participant.betresult.resultfinder;

import domain.card.Card;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
import domain.player.participant.betresult.resultstate.BetResultState;
import domain.player.participant.betresult.resultstate.BreakEvenState;
import domain.player.participant.betresult.resultstate.LoseState;
import domain.player.participant.betresult.resultstate.WinState;
import org.assertj.core.api.Assertions;
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
import static domain.card.CardValue.FIVE;
import static domain.card.CardValue.KING;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.QUEEN;
import static domain.card.CardValue.TEN;
import static domain.card.CardValue.THREE;
import static domain.card.CardValue.TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BetResultFinderTest {

    BetResultFinder betResultFinder = new BetResultFinder();

    @Test
    @DisplayName("[ParticipantLose] findStateOf() : 첫 두장 카드에서 딜러만 블랙잭일 경우 참여자는 진다")
    void test_findStateOf_participantLose() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(DIAMOND, TEN));
        participant.hit(new Card(SPADE, TEN));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, ACE));
        dealer.hit(new Card(CLOVER, TEN));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(LoseState.class);
    }

    @Test
    @DisplayName("[PlayerDraw] findStateOf() : 첫 두장 카드에서 딜러, 참여자 모두 블랙잭이면 무승부다.")
    void test_findStateOf_playerDraw() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(DIAMOND, ACE));
        participant.hit(new Card(SPADE, TEN));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, ACE));
        dealer.hit(new Card(CLOVER, TEN));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(BreakEvenState.class);
    }

    @Test
    @DisplayName("[PlayerDraw] findStateOf() : 마지막에 딜러와 참여자의 점수가 같으면 무승부다.")
    void test_findStateOf_playerDraw_finalMatch() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(DIAMOND, TEN));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, ACE));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, TEN));
        dealer.hit(new Card(CLOVER, TEN));
        dealer.hit(new Card(CLOVER, ACE));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(BreakEvenState.class);
    }

    @Test
    @DisplayName("[ParticipantBust] findStateOf() : 참여자가 버스트면 무조건 진다.")
    void test_findStateOf_participantBust() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(DIAMOND, NINE));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, NINE));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, ACE));
        dealer.hit(new Card(CLOVER, TWO));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(LoseState.class);
    }

    @Test
    @DisplayName("[ParticipantBlackjack] findStateOf() : 참여자만 블랙잭일 경우 참여자가 이긴다.")
    void test_findStateOf_participantBlackjack() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, ACE));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, ACE));
        dealer.hit(new Card(CLOVER, TWO));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(WinState.class);
    }

    @Test
    @DisplayName("[DealerBust] findStateOf() : 딜러만 버스트일 경우 참여자가 이긴다.")
    void test_findStateOf_dealerBust() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, NINE));
        participant.hit(new Card(SPADE, TWO));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, KING));
        dealer.hit(new Card(CLOVER, TWO));
        dealer.hit(new Card(CLOVER, QUEEN));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(BreakEvenState.class);
    }

    @Test
    @DisplayName("[ParticipantBust] findStateOf() : 딜러, 참여자가 버스트 일 경우에는 딜러가 이긴다.")
    void test_findStateOf_both_bust() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, NINE));
        participant.hit(new Card(SPADE, TWO));
        participant.hit(new Card(CLOVER, TWO));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, KING));
        dealer.hit(new Card(CLOVER, TWO));
        dealer.hit(new Card(CLOVER, QUEEN));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(LoseState.class);
    }

    @Test
    @DisplayName("[DealerBlackjack] findStateOf() : 딜러만 블랙잭일 경우 딜러가 이긴다.")
    void test_findStateOf_dealerBlackjack() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(SPADE, NINE));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(HEART, KING));
        dealer.hit(new Card(CLOVER, ACE));

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        Assertions.assertThat(betResultState).isInstanceOf(LoseState.class);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("betBlackjack")
    @DisplayName("findStateOf() : 첫 두장에서 승부가 날 수 있다.")
    void test_findStateOf_blackjack(final Participant participant,
                                    final Dealer dealer,
                                    final BetResultState resultState) throws Exception {

        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        //then
        assertEquals(resultState.getClass(), betResultState.getClass());
    }

    static Stream<Arguments> betBlackjack() {

        //첫 두장에서 참여자 블랙잭 O, 딜러 블랙잭 O
        Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.hit(new Card(DIAMOND, TEN));
        participant1.hit(new Card(DIAMOND, ACE));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(SPADE, TEN));
        dealer1.hit(new Card(SPADE, ACE));

        //첫 두장에서 참여자 블랙잭 O, 딜러 블랙잭 X
        Participant participant2 = new Participant(new Name("name2"), Money.wons(5000));
        participant2.hit(new Card(DIAMOND, TEN));
        participant2.hit(new Card(DIAMOND, ACE));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(SPADE, TEN));
        dealer2.hit(new Card(DIAMOND, TEN));

        //첫 두장에서 참여자 블랙잭 X, 딜러 블랙잭 O
        Participant participant3 = new Participant(new Name("name3"), Money.wons(1000));
        participant3.hit(new Card(DIAMOND, TEN));
        participant3.hit(new Card(SPADE, TEN));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(SPADE, TEN));
        dealer3.hit(new Card(DIAMOND, ACE));

        return Stream.of(
                Arguments.of(participant1, dealer1, new BreakEvenState()),
                Arguments.of(participant2, dealer2, new WinState()),
                Arguments.of(participant3, dealer3, new LoseState())
        );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("betNotBlackjack")
    @DisplayName("findStateOf() : 첫 두장에서 승부가 결정되지 않고, 마지막에 승부 상태가 결정될 수 있다.")
    void test_findStateOf_not_blackjack(final Participant participant,
                                        final Dealer dealer,
                                        final BetResultState resultState) throws Exception {
        //when
        final BetResultState betResultState = betResultFinder.findStateOf(participant, dealer);

        System.out.println("participant = " + participant.name().value());
        //then
        assertEquals(resultState.getClass(), betResultState.getClass());
    }

    static Stream<Arguments> betNotBlackjack() {

        //참여자 승
        Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.hit(new Card(DIAMOND, TEN));
        participant1.hit(new Card(DIAMOND, TEN));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(SPADE, TEN));
        dealer1.hit(new Card(SPADE, TWO));

        //참여자 패
        Participant participant2 = new Participant(new Name("name2"), Money.wons(5000));
        participant2.hit(new Card(DIAMOND, THREE));
        participant2.hit(new Card(DIAMOND, ACE));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(SPADE, TEN));
        dealer2.hit(new Card(DIAMOND, TEN));

        //무승부
        Participant participant3 = new Participant(new Name("name3"), Money.wons(1000));
        participant3.hit(new Card(DIAMOND, TEN));
        participant3.hit(new Card(SPADE, TEN));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(SPADE, TEN));
        dealer3.hit(new Card(DIAMOND, TEN));

        //딜러 버스트, 참여자 버스트 X
        Participant participant4 = new Participant(new Name("name4"), Money.wons(1000));
        participant4.hit(new Card(DIAMOND, TEN));
        participant4.hit(new Card(SPADE, TEN));

        final Dealer dealer4 = new Dealer();
        dealer4.hit(new Card(SPADE, TEN));
        dealer4.hit(new Card(DIAMOND, TEN));
        dealer4.hit(new Card(DIAMOND, KING));

        //참여자 버스트
        Participant participant5 = new Participant(new Name("name5"), Money.wons(5000));
        participant5.hit(new Card(DIAMOND, TEN));
        participant5.hit(new Card(SPADE, TEN));
        participant5.hit(new Card(SPADE, KING));

        final Dealer dealer5 = new Dealer();
        dealer5.hit(new Card(DIAMOND, ACE));
        dealer5.hit(new Card(SPADE, TEN));

        return Stream.of(
                Arguments.of(participant1, dealer1, new BreakEvenState()),
                Arguments.of(participant2, dealer2, new LoseState()),
                Arguments.of(participant3, dealer3, new BreakEvenState()),
                Arguments.of(participant4, dealer4, new BreakEvenState()),
                Arguments.of(participant5, dealer5, new LoseState())
        );
    }
}

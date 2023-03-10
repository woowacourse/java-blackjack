package domain.player.participant;

import domain.card.Card;
import domain.card.CardShape;
import domain.hand.Hand;
import domain.player.Name;
import domain.player.dealer.Dealer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.card.CardShape.CLOVER;
import static domain.card.CardShape.DIAMOND;
import static domain.card.CardShape.SPADE;
import static domain.card.CardValue.ACE;
import static domain.card.CardValue.KING;
import static domain.card.CardValue.SEVEN;
import static domain.card.CardValue.TEN;
import static domain.card.CardValue.THREE;
import static domain.card.CardValue.TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @ParameterizedTest
    @MethodSource("betBlackjack")
    @DisplayName("determineBetMoney() : 첫 두장에서 승부가 결정되어, 승부에 따라 배당액을 계산할 수 있다.")
    void test_determineBetMoney_blackjack(final Participant participant,
                                          final Dealer dealer,
                                          final Money bettingResultMoney) throws Exception {

        //given
        participant.firstMatchWith(dealer);

        //when
        Money money = participant.determineBetMoney();

        //then
        assertEquals(bettingResultMoney, money);
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
                Arguments.of(participant1, dealer1, Money.wons(10000)),
                Arguments.of(participant2, dealer2, Money.wons(7500)),
                Arguments.of(participant3, dealer3, Money.wons(-1000))
        );
    }

    @ParameterizedTest
    @MethodSource("betNotBlackjack")
    @DisplayName("determineBetMoney() : 첫 두장에서 승부가 결정되지 않고, 마지막에 승부에 따라 배당액을 계산할 수 있다.")
    void test_determineBetMoney_not_blackjack(final Participant participant,
                                              final Dealer dealer,
                                              final Money bettingResultMoney) throws Exception {
        //given
        participant.firstMatchWith(dealer);

        //when
        participant.finalMatchWith(dealer);
        final Money money = participant.determineBetMoney();

        //then
        assertEquals(bettingResultMoney, money);
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
                Arguments.of(participant1, dealer1, Money.wons(10000)),
                Arguments.of(participant2, dealer2, Money.wons(-5000)),
                Arguments.of(participant3, dealer3, Money.wons(1000)),
                Arguments.of(participant4, dealer4, Money.wons(1000)),
                Arguments.of(participant5, dealer5, Money.wons(-5000))
        );
    }

    @Test
    @DisplayName("faceUpFirstDeal() : participant는 처음 받은 카드 두 장을 모두 보여줘야한다.")
    void test_faceUpFirstDeal() throws Exception {
        // given
        final Participant participant = new Participant(new Name("name1"), Money.wons(0));
        participant.hit(new Card(CardShape.CLOVER, TEN));
        participant.hit(new Card(CardShape.CLOVER, SEVEN));

        // when
        final List<Card> cards = participant.faceUpFirstDeal();

        // then
        Assertions.assertThat(cards).containsExactly(
                new Card(CardShape.CLOVER, TEN),
                new Card(CardShape.CLOVER, SEVEN)
        );
    }
}

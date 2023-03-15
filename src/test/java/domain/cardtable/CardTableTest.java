package domain.cardtable;

import domain.card.Card;
import domain.deck.CardDeck;
import domain.player.Name;
import domain.player.dealer.Dealer;
import domain.player.participant.Money;
import domain.player.participant.Participant;
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
import static domain.card.CardValue.JACK;
import static domain.card.CardValue.KING;
import static domain.card.CardValue.NINE;
import static domain.card.CardValue.QUEEN;
import static domain.card.CardValue.TEN;
import static domain.card.CardValue.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTableTest {

    CardDeck cardDeck;

    CardTable cardTable;

    @BeforeEach
    void initHand() {
        cardDeck = CardDeck.shuffledFullCardDeck();
        cardTable = CardTable.readyToPlayBlackjack(cardDeck);
    }

    @Test
    @DisplayName("[final match] determineParticipantsBettingMoney() : 참여자가 bust 일 경우에는 참여자는 무조건 돈을 잃는다.")
    void test_determineParticipantsBettingMoney_lose() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(DIAMOND, KING));
        participant.hit(new Card(DIAMOND, JACK));

        final Dealer dealer = new Dealer();

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineParticipantsBettingMoney(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(Money.wons(-10000))
        );
    }

    @Test
    @DisplayName("[final match] determineParticipantsBettingMoney() : 딜러가 bust 일 때, 참여자가 bust가 아니면 돈을 그대로 돌려받는다.")
    void test_determineParticipantsBettingMoney_dealer_bust() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(10000));
        participant.hit(new Card(SPADE, TEN));
        participant.hit(new Card(DIAMOND, KING));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(SPADE, KING));
        dealer.hit(new Card(SPADE, JACK));
        dealer.hit(new Card(SPADE, QUEEN));

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineParticipantsBettingMoney(List.of(participant), dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(Money.wons(10000))
        );
    }

    @ParameterizedTest
    @MethodSource("makeBothNotBust")
    @DisplayName("[final match] determineParticipantsBettingMoney() : 딜러, 참여자 모두 버스트가 아닐 때 참여자가 점수가 높거나 같으면 돈을 그대로 돌려받는다.")
    void test_determineParticipantsBettingMoney_both_not_bust(
            final Participant participant, final Dealer dealer,
            final Money resultBettingMoney) throws Exception {

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineParticipantsBettingMoney(List.of(participant), dealer);

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

    @ParameterizedTest
    @MethodSource("determineFirstMatchResult")
    @DisplayName("[first match] determineParticipantsBettingMoney() : 첫 두장에서 결과가 결정되면, 다시 계산하지 않고 그 결과 그대로 배당을 받는다.")
    void test_determineParticipantsBettingMoney_finish_before_final_match(final List<Participant> participants,
                                                                          final Dealer dealer,
                                                                          final Money resultMoney) throws Exception {

        //when
        final Map<Participant, Money> gameResultMoney =
                cardTable.determineParticipantsBettingMoney(participants, dealer);

        //then
        assertAll(
                () -> assertThat(gameResultMoney).hasSize(1),
                () -> assertThat(gameResultMoney).containsValue(resultMoney)
        );
    }

    static Stream<Arguments> determineFirstMatchResult() {

        // 딜러, 참여자 모두 블랙잭 O
        final Participant participant1 = new Participant(new Name("name1"), Money.wons(10000));
        participant1.hit(new Card(DIAMOND, TEN));
        participant1.hit(new Card(SPADE, ACE));

        final Dealer dealer1 = new Dealer();
        dealer1.hit(new Card(HEART, ACE));
        dealer1.hit(new Card(CLOVER, TEN));

        // 참여자 블랙잭 O, 딜러 블랙잭 X
        final Participant participant2 = new Participant(new Name("name2"), Money.wons(10000));
        participant2.hit(new Card(DIAMOND, TEN));
        participant2.hit(new Card(SPADE, ACE));

        final Dealer dealer2 = new Dealer();
        dealer2.hit(new Card(HEART, ACE));
        dealer2.hit(new Card(CLOVER, ACE));

        // 참여자 블랙잭 X, 딜러 블랙잭 O
        final Participant participant3 = new Participant(new Name("name1"), Money.wons(10000));
        participant3.hit(new Card(DIAMOND, TEN));
        participant3.hit(new Card(SPADE, TEN));

        final Dealer dealer3 = new Dealer();
        dealer3.hit(new Card(HEART, ACE));
        dealer3.hit(new Card(CLOVER, TEN));

        return Stream.of(
                Arguments.of(List.of(participant1), dealer1, Money.wons(10000)),
                Arguments.of(List.of(participant2), dealer2, Money.wons(15000)),
                Arguments.of(List.of(participant3), dealer3, Money.wons(-10000))
        );
    }

    @ParameterizedTest
    @MethodSource("determineFirstMatchResult")
    @DisplayName("determineDealerMoney() : 딜러가 얻은 금액은 참여자가 모두 잃은 금액과 같다.")
    void test_determineDealerMoney(final List<Participant> participants,
                                   final Dealer dealer,
                                   final Money resultMoney) throws Exception {

        //when
        final Money dealerMoney = cardTable.determineDealerMoney(participants, dealer);

        //then
        assertEquals(dealerMoney, resultMoney.lose());
    }

    @Test
    @DisplayName("dealCardTo() : Player에게 카드를 나눠줄 수 있다.")
    void test_dealCardTo() throws Exception {
        //given
        final Participant participant = new Participant(new Name("name1"), Money.wons(0));
        participant.hit(new Card(SPADE, ACE));
        participant.hit(new Card(SPADE, TWO));

        final Dealer dealer = new Dealer();
        dealer.hit(new Card(DIAMOND, TEN));
        dealer.hit(new Card(DIAMOND, TEN));

        //when
        cardTable.dealCardTo(participant);
        cardTable.dealCardTo(dealer);

        //then
        assertAll(
                () -> assertThat(participant.hand().cards()).hasSize(3),
                () -> assertThat(dealer.hand().cards()).hasSize(2)
        );
    }
}

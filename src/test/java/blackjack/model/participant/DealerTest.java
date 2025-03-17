package blackjack.model.participant;

import static blackjack.TestFixtures.UNSHUFFLED_DECK;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Suit;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("딜러 테스트")
class DealerTest {

    @DisplayName("카드를 한장 받는다.")
    @Test
    void receiveHandTest() {
        // given
        Card card = new Card(Suit.SPADES, CardValue.ACE);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);

        // when
        dealer.receiveHand(card);

        // then
        assertThat(dealer.getHand())
                .contains(new Card(Suit.SPADES, CardValue.ACE));
    }

    @DisplayName("가진 패의 총합을 계산한다.")
    @Test
    void calculateHandTotalTest() {
        // given
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Card spadeFive = new Card(Suit.SPADES, CardValue.FIVE);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(spadeTen);
        dealer.receiveHand(spadeFive);

        // when
        int total = dealer.calculateHandTotal();

        // then
        assertThat(total)
                .isEqualTo(15);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 이하인 경우 ACE를 11로 간주한다.")
    @Test
    void calculateHandTotalWithAceTest() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTen = new Card(Suit.SPADES, CardValue.TEN);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(spadeTen);
        dealer.receiveHand(spadeAce);

        // when
        int total = dealer.calculateHandTotal();

        // then
        assertThat(total)
                .isEqualTo(21);
    }

    @DisplayName("ACE를 가진 채, 총합이 11 초과인 경우 ACE를 1로 간주한다.")
    @Test
    void calculateHandTotalWithAceTestOver11() {
        // given
        Card spadeAce = new Card(Suit.SPADES, CardValue.ACE);
        Card spadeTwo = new Card(Suit.SPADES, CardValue.TWO);
        Card spadeNine = new Card(Suit.SPADES, CardValue.NINE);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(spadeAce);
        dealer.receiveHand(spadeTwo);
        dealer.receiveHand(spadeNine);

        // when
        int total = dealer.calculateHandTotal();

        // then
        assertThat(total)
                .isEqualTo(12);
    }

    @DisplayName("패가 2장만 있고, 합이 21이면 블랙잭이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, true",
            "TEN, TEN, false",
    })
    void isBlackjackTest(CardValue value1, CardValue value2, boolean expected) {
        // given
        Card spadeTen = new Card(Suit.SPADES, value1);
        Card spadeAce = new Card(Suit.SPADES, value2);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(spadeTen);
        dealer.receiveHand(spadeAce);

        // when
        boolean isBlackjack = dealer.isBlackjack();

        // then
        assertThat(isBlackjack)
                .isSameAs(expected);
    }

    @DisplayName("21이 초과하면 버스트이다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, TEN, TEN, true",
            "TWO, TWO, ACE, false",
    })
    void isBustTest(CardValue value1, CardValue value2, CardValue value3, boolean expected) {
        // given
        Card card1 = new Card(Suit.SPADES, value1);
        Card card2 = new Card(Suit.SPADES, value2);
        Card card3 = new Card(Suit.SPADES, value3);
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(card1);
        dealer.receiveHand(card2);
        dealer.receiveHand(card3);

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust)
                .isSameAs(expected);
    }

    @DisplayName("가진 패의 총합이 16이하인 경우 히트한다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, SIX, true",
            "TEN, SEVEN, false"
    })
    void shouldHitTrueTest(CardValue cardValue1, CardValue cardValue2, boolean expected) {
        // given
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        dealer.receiveHand(new Card(Suit.SPADES, cardValue1));
        dealer.receiveHand(new Card(Suit.SPADES, cardValue2));

        // when
        boolean canHit = dealer.hitDealer();

        // then
        assertThat(canHit)
                .isSameAs(expected);
    }

    @DisplayName("딜러의 수익률를 계산한다.")
    @Test
    void calculateProfitTest() {
        // given
        Dealer dealer = new Dealer(UNSHUFFLED_DECK);
        Map<Player, Profit> playerProfit = new HashMap<>();
        playerProfit.put(Player.of("pobi", 1000), new Profit(10000));

        // when
        Profit profit = dealer.calculateProfit(playerProfit);

        // then
        assertThat(profit.getProfit())
                .isEqualTo(-10000);
    }
}

package participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import domain.participant.Dealer;
import domain.participant.Hand;
import domain.participant.PlayerName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DealerTest {

    Hand dummyHand;
    PlayerName name;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = new PlayerName("딜러");
    }

    @Test
    @DisplayName("딜러 핸드의 카드 총합이 21초과면 bust이다.")
    void isBust_ReturnsTrue_WhenTotalScoreExceeds21() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.QUEEN, Pattern.CLOVER);
        Card card3 = new Card(Rank.KING, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);
        dummyHand.addCard(card3);

        boolean result = new Dealer(dummyHand).isBust();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러 핸드의 카드 총합이 21이하면 bust가 아니다.")
    void isBust_ReturnsFalse_WhenTotalScoreLessThan21() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.TWO, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);

        boolean result = new Dealer(dummyHand).isBust();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러 핸드의 카드 총합이 16이하이면 카드를 더 뽑아야 한다.")
    void shouldHit_ReturnsTrue_WhenHandSizeLessThan16() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.TWO, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);

        boolean result = new Dealer(dummyHand).shouldHit();

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("딜러 핸드의 카드 총합이 16초과이면 카드를 더 뽑지 않는다.")
    void shouldHit_ReturnsFalse_WhenHandSizeExceeds16() {
        Card card1 = new Card(Rank.JACK, Pattern.CLOVER);
        Card card2 = new Card(Rank.SEVEN, Pattern.CLOVER);
        dummyHand.addCard(card1);
        dummyHand.addCard(card2);

        boolean result = new Dealer(dummyHand).shouldHit();

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("딜러가 블랙잭인 경우 true를 반환한다.")
    void isBlackjack_returnsTrue_whenDealerHasBlackjack() {
        Dealer blackJackDealer = new Dealer(dummyHand);
        blackJackDealer.keepCard(new Card(Rank.ACE, Pattern.CLOVER));
        blackJackDealer.keepCard(new Card(Rank.JACK, Pattern.CLOVER));

        boolean success = blackJackDealer.isBlackjack();

        assertThat(success).isTrue();
    }

    @Test
    @DisplayName("딜러가 블랙잭이 아닌 경우 false를 반환한다")
    void isBlackjack_returnsFalse_whenDealerHasNotBlackjack() {
        Dealer blackJackDealer2 = new Dealer(new Hand());
        blackJackDealer2.keepCard(new Card(Rank.ACE, Pattern.HEART));
        blackJackDealer2.keepCard(new Card(Rank.EIGHT, Pattern.HEART));

        boolean fail = blackJackDealer2.isBlackjack();

        assertThat(fail).isFalse();
    }
}

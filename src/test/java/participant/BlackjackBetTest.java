package participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.blackjackgame.CardValue;
import domain.blackjackgame.Suit;
import domain.blackjackgame.TrumpCard;
import domain.participant.BlackjackBet;
import domain.participant.BlackjackHands;
import exception.BlackJackException;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackjackBetTest {

    @Test
    void 플레이어는_베팅할_수_있다() {
        int money = 1000;
        assertThat(new BlackjackBet(money)).isNotInstanceOf(BlackJackException.class);
    }

    @Test
    void 플레이어는_0원이상만_베팅할_수_있다() {
        int money = -1;
        assertThatThrownBy(() -> new BlackjackBet(money)).isInstanceOf(BlackJackException.class);
    }

    @Test
    void 베팅금액_버스트시_잃는다() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.TEN),
                new TrumpCard(Suit.CLOVER, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.NINE));
        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(0);
    }

    @Test
    void 딜러가_버스트시_이긴다() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.TEN),
                new TrumpCard(Suit.CLOVER, CardValue.NINE));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.FOUR),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT),
                new TrumpCard(Suit.CLOVER, CardValue.FIVE),
                new TrumpCard(Suit.CLOVER, CardValue.SIX));
        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(2000);
    }

    @Test
    void 무승부일시_베팅_금액을_돌려받는다() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.HEART, CardValue.NINE),
                new TrumpCard(Suit.HEART, CardValue.EIGHT));

        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(money);
    }

    @Test
    void 둘다_버스트일시_무승부() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.HEART, CardValue.NINE),
                new TrumpCard(Suit.HEART, CardValue.EIGHT));
        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(money);
    }

    @Test
    void 플레이어가_숫자가_높으면_이긴다() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.CLOVER, CardValue.TWO));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.SPADE, CardValue.A));
        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(money * 2);
    }

    @Test
    void 블랙잭_배율_테스트() {
        int money = 1000;
        BlackjackBet blackjackBet = new BlackjackBet(money);
        List<TrumpCard> trumpCards = List.of(new TrumpCard(Suit.CLOVER, CardValue.A),
                new TrumpCard(Suit.SPADE, CardValue.J));
        List<TrumpCard> dealerTrumpCards = List.of(new TrumpCard(Suit.HEART, CardValue.A),
                new TrumpCard(Suit.SPADE, CardValue.A));
        BlackjackHands cardSum = new BlackjackHands(trumpCards);
        BlackjackHands otherCardSum = new BlackjackHands(dealerTrumpCards);
        assertThat(blackjackBet.calculateEarnMoney(cardSum, otherCardSum))
                .isEqualTo(2500);
    }
}



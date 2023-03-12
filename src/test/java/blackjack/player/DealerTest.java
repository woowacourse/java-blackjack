package blackjack.player;

import static blackjackgame.Result.LOSE;
import static blackjackgame.Result.TIE;
import static blackjackgame.Result.WIN;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjackgame.Result;
import card.Card;
import card.Rank;
import card.Suit;
import participants.Count;
import participants.Dealer;

class DealerTest {
    @Test
    @DisplayName("딜러를 생성한다.")
    void create() {
        assertThatCode(() -> new Dealer())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("딜러는 카드를 받고, 받은 카드의 점수 합계를 구한다")
    void calculateScore() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        assertThat(dealer.calculateScore().getScore()).isEqualTo(15);
    }

    @Test
    @DisplayName("딜러는 hit로 받은 카드를 반환한다.")
    void showCards() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        assertThat(dealer.showCards()).contains(card1, card2, card3);
    }

    @Test
    @DisplayName("딜러는 현재 가지고 있는 카드중 첫번째 카드를 반환한다.")
    void showOneCard() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        assertThat(dealer.showOneCard()).isEqualTo(card1);
    }

    @Test
    @DisplayName("딜러의 점수합계가 16점 이하이면 true를 반환한다.")
    void isUnderScore() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.SIX, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        Assertions.assertThat(dealer.isUnderScore()).isTrue();
    }

    @Test
    @DisplayName("딜러의 점수합계가 16점 초과이면 false를 반환한다")
    void isUnderScoreFalse() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.ACE, Suit.HEART);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.EIGHT, Suit.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        Assertions.assertThat(dealer.isUnderScore()).isFalse();
    }

    @Test
    @DisplayName("딜러가 버스트인 경우 true를 반환한다.")
    void isBust() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.KING, Suit.SPADE);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        Card card3 = new Card(Rank.KING, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);
        dealer.hit(card3);

        Assertions.assertThat(dealer.isBust()).isTrue();
    }

    @Test
    @DisplayName("딜러가 버스트가 아니라면 false를 반환한다.")
    void isBustFalse() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(Rank.KING, Suit.SPADE);
        Card card2 = new Card(Rank.EIGHT, Suit.HEART);
        dealer.hit(card1);
        dealer.hit(card2);

        Assertions.assertThat(dealer.isBust()).isFalse();
    }

    @Test
    @DisplayName("딜러의 승패 결과를 확인할 수 있다.")
    void getWinningResult() {
        Dealer dealer = new Dealer();
        dealer.win();
        dealer.win();
        dealer.lose();
        dealer.tie();

        Map<Result, Count> dealerResult = dealer.getDealerResult();

        Assertions.assertThat(dealerResult.get(WIN).getCount()).isEqualTo(2);
        Assertions.assertThat(dealerResult.get(LOSE).getCount()).isEqualTo(1);
        Assertions.assertThat(dealerResult.get(TIE).getCount()).isEqualTo(1);
    }
}

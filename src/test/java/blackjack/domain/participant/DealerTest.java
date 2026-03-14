package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 점수가 16점 이하이면 카드를 추가로 뽑을 수 있다.")
    void isDrawable_True_WhenScoreIs16OrLess() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEARTS));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("딜러는 점수가 정확히 16점일 때 카드를 추가로 뽑을 수 있다. (엣지 케이스)")
    void isDrawable_True_WhenScoreIsExactly16() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.SIX, Suit.HEARTS));

        assertThat(dealer.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("딜러는 점수가 정확히 17점일 때 카드를 추가로 뽑을 수 없다. (엣지 케이스)")
    void isDrawable_False_WhenScoreIsExactly17() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADES));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEARTS));

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    @DisplayName("딜러가 받은 첫 번째 카드를 오픈 카드(Upcard)로 반환한다.")
    void getUpcard_ReturnsUpcard() {
        Dealer dealer = new Dealer();
        Card firstCard = new Card(Rank.TEN, Suit.SPADES);
        Card secondCard = new Card(Rank.SEVEN, Suit.HEARTS);

        dealer.receiveCard(firstCard);
        dealer.receiveCard(secondCard);

        assertThat(dealer.getUpcard()).isEqualTo(firstCard);
    }
}

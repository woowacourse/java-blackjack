package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @DisplayName("딜러의 이름은 '딜러'이다.")
    @Test
    void create() {
        Dealer dealer = new Dealer();

        String result = dealer.getName();

        assertThat(result).isEqualTo("딜러");
    }

    @DisplayName("딜러는 카드를 한 장 받을 수 있다.")
    @Test
    void hitOneCard() {
        Dealer dealer = new Dealer();
        Card card = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        dealer.hit(card);

        assertThat(dealer.getCards()).containsExactly(card);
    }

    @DisplayName("딜러는 17점 이상이 되면, 더 이상 카드를 받을 수 없다.")
    @Test
    void hitWhenIsNotPlayable() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.EIGHT, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        assertThatThrownBy(() -> dealer.hit(card3))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("딜러가 카드를 더 받을 수 있는지 확인할 수 있다.")
    @Test
    void isPlayable() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        boolean result = dealer.isPlayable();

        assertThat(result).isFalse();
    }

    @DisplayName("딜러의 카드 중 첫 번째 카드는 가려져 있다.")
    @Test
    void getVisibleCards() {
        Dealer dealer = new Dealer();
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.SEVEN, CardShape.DIAMOND);
        dealer.hit(card1);
        dealer.hit(card2);

        assertThat(dealer.getVisibleCards()).containsExactly(card2);
    }
}

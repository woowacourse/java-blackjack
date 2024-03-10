package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
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

        assertThat(dealer.getCards()).isEqualTo(List.of(card));
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

    @DisplayName("둘 다 버스트되면, 딜러가 이긴다.")
    @Test
    void allBust() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.FOUR, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
        player.hit(new Card(CardRank.QUEEN, CardShape.DIAMOND));

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("둘 다 블랙잭이면, 무승부이다.")
    @Test
    void allBlackJack() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.JACK, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.KING, CardShape.CLOVER));
        player.hit(new Card(CardRank.ACE, CardShape.CLOVER));

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.PUSH);
    }

    @DisplayName("둘 다 버스트되지 않고, 딜러 점수가 더 낮으면 진다.")
    @Test
    void whenDealerLose() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.WIN);
    }

    @DisplayName("둘 다 버스트되지 않고, 딜러 점수가 더 높으면 이긴다.")
    @Test
    void whenDealerWin() {
        Dealer dealer = new Dealer();
        dealer.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        dealer.hit(new Card(CardRank.ACE, CardShape.DIAMOND));

        Player player = new Player("pobi");
        player.hit(new Card(CardRank.KING, CardShape.DIAMOND));
        player.hit(new Card(CardRank.SEVEN, CardShape.DIAMOND));

        GameResult gameResult = dealer.judge(player);

        assertThat(gameResult).isEqualTo(GameResult.LOSE);
    }
}

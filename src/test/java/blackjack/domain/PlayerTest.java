package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Player("atom", new Money(1000)))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 초기 돈은 음수가 될 수 없다.")
    @Test
    void validateNegativeMoney() {
        Money negativeMoney = new Money(-1000);

        assertThatThrownBy(() -> new Player("atom", negativeMoney))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어는 카드를 한 장 받을 수 있다.")
    @Test
    void hitOneCard() {
        Player player = new Player("atom", new Money(1000));
        Card givenCard = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        player.hit(givenCard);

        assertThat(player.getCards()).containsExactly(givenCard);
    }

    @DisplayName("플레이어가 버스트 상태면 더 이상 카드를 받을 수 없다")
    @Test
    void hitWhenBust() {
        Player player = new Player("atom", new Money(1000));
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.QUEEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.JACK, CardShape.DIAMOND);
        Card card4 = new Card(CardRank.ACE, CardShape.DIAMOND);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThatThrownBy(() -> player.hit(card4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어가 21점에 도달하면 더 이상 카드를 받을 수 없다")
    @Test
    void hitWhenBlackJackScore() {
        Player player = new Player("atom", new Money(1000));
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.QUEEN, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.ACE, CardShape.DIAMOND);
        Card card4 = new Card(CardRank.ACE, CardShape.HEART);

        player.hit(card1);
        player.hit(card2);
        player.hit(card3);

        assertThatThrownBy(() -> player.hit(card4))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어는 카드를 더 받을 수 있는지 확인할 수 있다.")
    @Test
    void isPlayable() {
        Player player = new Player("atom", new Money(1000));
        player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.CLOVER));

        boolean result = player.isPlayable();

        assertThat(result).isFalse();
    }

    @DisplayName("플레이어가 질 경우, 수익금을 계산한다.")
    @Test
    void whenPlayerLose() {
        Money playerMoney = new Money(1000);
        Player player = new Player("atom", playerMoney);

        Money result = player.calculateProfit(GameResult.LOSE);

        assertThat(result.getAmount()).isEqualTo(-1000);
    }

    @DisplayName("플레이어가 이길 경우, 수익금을 계산한다.")
    @Test
    void whenPlayerWin() {
        Money playerMoney = new Money(1000);
        Player player = new Player("atom", playerMoney);

        Money result = player.calculateProfit(GameResult.WIN);

        assertThat(result.getAmount()).isEqualTo(1000);
    }

    @DisplayName("플레이어가 블랙잭으로 이길 경우, 수익금을 계산한다.")
    @Test
    void whenPlayerWinWithBlackJack() {
        Money playerMoney = new Money(1000);
        Player player = new Player("atom", playerMoney);

        Money result = player.calculateProfit(GameResult.BLACKJACK_WIN);

        assertThat(result.getAmount()).isEqualTo(1500);
    }

    @DisplayName("플레이어가 무승부일 경우, 수익금을 계산한다.")
    @Test
    void whenPlayerTie() {
        Money playerMoney = new Money(1000);
        Player player = new Player("atom", playerMoney);

        Money result = player.calculateProfit(GameResult.TIE);

        assertThat(result.getAmount()).isEqualTo(0);
    }
}

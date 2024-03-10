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
        assertThatCode(() -> new Player("atom"))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어는 카드를 한 장 받을 수 있다.")
    @Test
    void hitOneCard() {
        Player player = new Player("atom");
        Card givenCard = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        player.hit(givenCard);

        assertThat(player.getCards()).containsExactly(givenCard);
    }

    @DisplayName("플레이어가 버스트되거나, 블랙잭인 상태면 더 이상 카드를 받을 수 없다")
    @Test
    void hitWhenIsNotPlayable() {
        Player player = new Player("atom");
        Card card1 = new Card(CardRank.KING, CardShape.DIAMOND);
        Card card2 = new Card(CardRank.ACE, CardShape.DIAMOND);
        Card card3 = new Card(CardRank.EIGHT, CardShape.DIAMOND);

        player.hit(card1);
        player.hit(card2);

        assertThatThrownBy(() -> player.hit(card3))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("플레이어는 카드를 더 받을 수 있는지 확인할 수 있다.")
    @Test
    void isPlayable() {
        Player player = new Player("atom");
        player.hit(new Card(CardRank.JACK, CardShape.DIAMOND));
        player.hit(new Card(CardRank.ACE, CardShape.CLOVER));

        boolean result = player.isPlayable();

        assertThat(result).isFalse();
    }
}

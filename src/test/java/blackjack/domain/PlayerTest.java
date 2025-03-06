package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드의 합을 계산해 반환한다")
    void 카드의_합을_계산해_반환한다() {
        // given
        Player player = new Player("두리");
        player.pushDealCard(new CardPack(new SortShuffle()), 3);

        // when
        int result = player.calculateCardNumber();

        // then
        assertThat(result).isEqualTo(30);
    }
}

package blackjack.domain;

import blackjack.domain.card.CardPack;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드의 합을 계산해 반환한다")
    void calculate_calculation_check_rot() {
        // given
        Player player = new Gambler("두리");
        player.pushDealCard(new CardPack(new SortShuffle()), 3);

        // when
        int result = player.calculateCardNumbers();

        // then
        assertThat(result).isEqualTo(30);
    }

    @DisplayName("에이스가 2장이면 12로 계산한다")
    @Test
    void ace_test() {
        Player player = new Gambler("두리");
        player.pushDealCard(new CardPack(new ReversedSortShuffle()), 2);

        // when
        int result = player.calculateCardNumbers();

        // then
        assertThat(result).isEqualTo(12);
    }
}

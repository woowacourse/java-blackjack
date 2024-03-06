package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRoundTest {
    @Test
    @DisplayName("플레이어 한명이 처음 받은 카드 두장을 반환한다.")
    void gameRunTest() {
        // given
        Player player = new Player("lemone");
        GameRound gameRound = new GameRound(player, new Dealer());
        CardPicker cardPicker = new CardPicker();
        // when
        gameRound.deal(cardPicker);

        // then
        assertThat(player.getCards().size()).isEqualTo(2);
    }

}

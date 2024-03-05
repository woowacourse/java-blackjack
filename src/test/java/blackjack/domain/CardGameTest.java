package blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGameTest {
    @Test
    void 카드_한_장을_플레이어에게_준다() {
        Player player = new Player();
        Card card = new Card();

        CardGame cardGame = new CardGame();
        cardGame.giveCard(player, card);

        assertThat(player.getCards().size()).isEqualTo(1);
    }
}

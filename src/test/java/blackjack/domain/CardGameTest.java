package blackjack.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CardGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        Player player = new Player();
        Card card = new Card();

        CardGame cardGame = new CardGame();
        cardGame.giveCard(player, card);

        assertThat(player.getCards().size()).isEqualTo(1);
    }

    @Test
    void 모든_플레이어에게_카드_2장을_지급한다() {
        Player mangcho = new Player();
        Player ddang = new Player();
        List<Player> players = List.of(mangcho, ddang);

        CardGame cardGame = new CardGame();
        cardGame.giveTwoCardsEachPlayer(players);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }
}

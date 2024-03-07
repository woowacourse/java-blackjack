package blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class CardGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        Player mangcho = player();
        Card card = new Card(ACE, SPADE);

        CardGame cardGame = new CardGame();
        cardGame.giveCard(mangcho, card);

        assertThat(mangcho.getCards().size()).isEqualTo(1);
    }

    @Test
    void 모든_플레이어에게_카드_2장을_지급한다() {
        Player mangcho = player();
        Player ddang = player();

        CardGame cardGame = new CardGame();
        cardGame.giveTwoCardsEachPlayer(List.of(mangcho, ddang));

        assertSoftly(softly -> {
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }
}

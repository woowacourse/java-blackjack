package blackjack.domain.cardgame;

import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Player mangcho = player();

        // when
        blackjackGame.giveCard(mangcho);

        // then
        assertThat(mangcho.getCards().size()).isEqualTo(1);
    }

    @Test
    void 게임_시작시_딜러와_모든_플레이어에게_카드_2장을_지급한다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = dealer();
        Player mangcho = player();
        Player ddang = player();

        // when
        blackjackGame.initializeHand(dealer, List.of(mangcho, ddang));

        // then
        assertSoftly(softly -> {
            softly.assertThat(dealer.getCards().size()).isEqualTo(2);
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }
}

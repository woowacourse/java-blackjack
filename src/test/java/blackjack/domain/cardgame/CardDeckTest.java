package blackjack.domain.cardgame;

import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.player;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        // given
        CardDeck cardDeck = new CardDeck();
        Player mangcho = player(0);

        // when
        cardDeck.giveCard(mangcho);

        // then
        assertThat(mangcho.getCards().size()).isEqualTo(1);
    }

    @Test
    void 게임_시작시_딜러와_모든_플레이어에게_카드_2장을_지급한다() {
        // given
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = dealer();
        Player mangcho = player(0);
        Player ddang = player(0);

        // when
        Participants participants = new Participants(dealer, List.of(mangcho, ddang));
        cardDeck.initializeHand(participants);

        // then
        assertSoftly(softly -> {
            softly.assertThat(dealer.getCards().size()).isEqualTo(2);
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }
}

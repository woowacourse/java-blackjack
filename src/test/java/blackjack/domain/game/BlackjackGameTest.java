package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

import static blackjack.fixture.DealerFixture.딜러;
import static blackjack.fixture.PlayerFixture.플레이어들;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {

    @Test
    void 블랙잭_게임을_시작하면_참가자들은_카드를_2장씩_받는다() {
        final Dealer dealer = 딜러();
        final Players players = 플레이어들("pobi", "jason");
        final BlackjackGame blackjackGame = new BlackjackGame(dealer, players);

        blackjackGame.start();

        assertThat(dealer.getCardHand())
                .extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .hasSize(2);
    }
}

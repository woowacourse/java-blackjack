package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.player.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.하트_10;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackjackGameTest {

    @Test
    void 딜러가_카드를_뽑는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack(List.of(스페이드_10, 하트_10)));
        Dealer dealer = new Dealer("dummy");

        // when
        blackjackGame.dealerDraw(dealer);

        // then
        Assertions.assertThat(dealer.showCards()).hasSize(1);
    }

    @Test
    void 플레이어가_카드를_뽑는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame(new CardPack(List.of(스페이드_10, 하트_10)));
        Player player = new Player("dummy");

        // when
        blackjackGame.playerDraw(player);

        // then
        Assertions.assertThat(player.showCards()).hasSize(1);
    }
}

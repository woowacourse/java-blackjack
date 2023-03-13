package blackjack.domain.game;

import blackjack.domain.cardpack.CardPack;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.player.Player;
import blackjack.domain.user.player.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static blackjack.domain.fixture.FixtureCard.다이아몬드_6;
import static blackjack.domain.fixture.FixtureCard.스페이드_10;
import static blackjack.domain.fixture.FixtureCard.스페이드_8;
import static blackjack.domain.fixture.FixtureCard.클로버_2;
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

    @Test
    void 딜러가_이기면_플레이어의_배팅금액만큼_수익을_얻는다() {
        // given
        String dealerName = "딜러";

        Player player = new Player("dummy");
        Players players = new Players(List.of(player));
        Dealer dealer = new Dealer(dealerName);

        BlackjackGame blackjackGame = new BlackjackGame(new CardPack(List.of(
                스페이드_8, 클로버_2,
                스페이드_10, 다이아몬드_6,
                스페이드_10, 하트_10
        )));

        // when
        int betMoney = 1000;
        blackjackGame.playerBet(player, new Money(betMoney));

        blackjackGame.dealerDraw(dealer);
        blackjackGame.dealerDraw(dealer);

        blackjackGame.playerDraw(player);
        blackjackGame.playerDraw(player);
        
        // then
        Map<String, Integer> dealerProfit = blackjackGame.toDealerProfit(players, dealer);
        Integer dealerMoney = dealerProfit.get(dealerName);

        Assertions.assertThat(dealerMoney).isEqualTo(betMoney);
    }
}

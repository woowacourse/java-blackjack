package blackjack.domain.user;

import blackjack.domain.card.cardpack.CardPack;
import blackjack.domain.game.GameResult;
import blackjack.domain.user.player.Player;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.fixture.FixtureCard.다이아몬드_6;
import static blackjack.domain.fixture.FixtureCard.클로버_10;
import static blackjack.domain.fixture.FixtureCard.클로버_A;
import static blackjack.domain.fixture.FixtureCard.하트_10;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DealerTest {

    public static final String DEALER_NAME = "딜러";

    @Test
    void 카드를_뽑을_수_있다() {
        // given
        Dealer dealer = new Dealer("딜러");
        CardPack cardPack = new CardPack();

        // when
        dealer.drawCard(cardPack);

        // then
        assertThat(dealer.showCards()).isNotEmpty();
    }


    @Test
    void 점수를_받아서_각플레이어의_승_패_무_를_결정한다() {
        // given
        final Player player1 = new Player("p1");
        final Player player2 = new Player("p2");
        final Player player3 = new Player("p3");

        final Dealer dealer = new Dealer(DEALER_NAME);

        CardPack cardPack1 = new CardPack(List.of(클로버_10, 클로버_A));
        CardPack cardPack2 = new CardPack(List.of(클로버_10, 하트_10));
        CardPack cardPack3 = new CardPack(List.of(클로버_10, 하트_10, 다이아몬드_6));

        CardPack dealerPack = new CardPack(List.of(클로버_10, 하트_10));

        // when
        player1.drawCard(cardPack1);
        player1.drawCard(cardPack1);

        player2.drawCard(cardPack2);
        player2.drawCard(cardPack2);

        player3.drawCard(cardPack3);
        player3.drawCard(cardPack3);
        player3.drawCard(cardPack3);

        dealer.drawCard(dealerPack);
        dealer.drawCard(dealerPack);

        //then
        assertThat(dealer.declareGameResult(player1.getScore().getValue())).isEqualTo(GameResult.WIN);
        assertThat(dealer.declareGameResult(player2.getScore().getValue())).isEqualTo(GameResult.DRAW);
        assertThat(dealer.declareGameResult(player3.getScore().getValue())).isEqualTo(GameResult.LOSE);
    }
}

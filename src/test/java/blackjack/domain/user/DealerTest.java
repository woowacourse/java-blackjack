package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static blackjack.domain.card.CardNumber.ACE;
import static blackjack.domain.card.CardNumber.KING;
import static blackjack.domain.card.CardNumber.QUEEN;
import static blackjack.domain.card.CardNumber.THREE;
import static blackjack.domain.card.CardNumber.TWO;
import static blackjack.domain.card.CardShape.CLOVER;

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
        Assertions.assertThat(dealer.showCards())
                .containsExactly(new Card(KING, CLOVER));
    }


    @Test
    void 점수를_받아서_각플레이어의_승_패_무_를_결정한다() {
        // given
        final Player player1 = new Player("p1");
        final Player player2 = new Player("p2");
        final Player player3 = new Player("p3");

        final Players players = new Players(List.of(player1, player2, player3));
        final Dealer dealer = new Dealer(DEALER_NAME);

        CardPack cardPack1 = new CardPack(List.of(new Card(QUEEN, CLOVER), new Card(ACE, CLOVER)));
        CardPack cardPack2 = new CardPack(List.of(new Card(QUEEN, CLOVER), new Card(QUEEN, CLOVER)));
        CardPack cardPack3 = new CardPack(List.of(new Card(QUEEN, CLOVER), new Card(QUEEN, CLOVER), new Card(QUEEN, CLOVER)));

        CardPack dealerPack = new CardPack(List.of(new Card(QUEEN, CLOVER), new Card(QUEEN, CLOVER)));

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
        Assertions.assertThat(dealer.declareGameResult(player1.getScore().getValue())).isEqualTo(GameResult.WIN);
        Assertions.assertThat(dealer.declareGameResult(player2.getScore().getValue())).isEqualTo(GameResult.DRAW);
        Assertions.assertThat(dealer.declareGameResult(player3.getScore().getValue())).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러의_승부_결과를_가져올_수_있다() {
        //given
        CardPack cardPack = new CardPack(List.of(
                new Card(QUEEN, CLOVER), new Card(ACE, CLOVER),
                new Card(TWO, CLOVER), new Card(THREE, CLOVER))
        );

        Player player = new Player("player");
        Dealer dealer = new Dealer("딜러");

        //when
        player.drawCard(cardPack);
        player.drawCard(cardPack);

        dealer.drawCard(cardPack);
        dealer.drawCard(cardPack);

        dealer.declareGameResult(player.getScore().getValue());

        //then
        Map<GameResult, Integer> result = dealer.getResult(new Players(List.of(player)));
        Assertions.assertThat(result.get(GameResult.WIN)).isEqualTo(1);
    }
}

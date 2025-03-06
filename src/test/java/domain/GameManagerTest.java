package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GameManagerTest {

    @Test
    void 딜러와_플레이어들로_카드매니저를_생성한다() {
        //given
        Dealer dealer = new Dealer();
        Player player1 = new Player("이름1");
        Player player2 = new Player("이름2");

        //when
        //then
        assertThatCode(() -> new GameManager(dealer, List.of(player1, player2)))
            .doesNotThrowAnyException();
    }

    @Test
    void 딜러와_플레이어들에게_초기카드를_나눠준다() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("이름");

        //when
        GameManager gameManager = new GameManager(dealer, List.of(player));
        gameManager.distributeSetUpCards();

        //then
        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player.getCards()).hasSize(2)
        );
    }

    @Test
    void 딜러의_승무패_횟수를_계산한다() {
        //given
        Dealer dealer = new Dealer();
        Player winnerPlayer1 = new Player("이름1");
        Player drawPlayer1 = new Player("이름2");
        Player drawPlayer2 = new Player("이름3");
        Player losePlayer1 = new Player("이름4");
        Player losePlayer2 = new Player("이름5");
        Player losePlayer3 = new Player("이름6");

        GameManager gameManager = new GameManager(
            dealer,
            List.of(winnerPlayer1, drawPlayer1, drawPlayer2,
                losePlayer1, losePlayer2, losePlayer3));

        Card tenSpade = new Card(Rank.TEN, Shape.SPADE);
        Card tenDiamond = new Card(Rank.TEN, Shape.DIAMOND);
        Card aceSpade = new Card(Rank.ACE, Shape.SPADE);
        Card tenHeart = new Card(Rank.TEN, Shape.HEART);
        Card tenClover = new Card(Rank.TEN, Shape.CLOVER);
        Card jackSpade = new Card(Rank.JACK, Shape.SPADE);
        Card jackDiamond = new Card(Rank.JACK, Shape.DIAMOND);
        Card jackHeart = new Card(Rank.JACK, Shape.HEART);
        Card kingSpade = new Card(Rank.KING, Shape.SPADE);
        Card nineSpade = new Card(Rank.NINE, Shape.SPADE);
        Card kingDiamond = new Card(Rank.KING, Shape.DIAMOND);
        Card nineDiamond = new Card(Rank.NINE, Shape.DIAMOND);
        Card kingHeart = new Card(Rank.KING, Shape.HEART);
        Card nineHeart = new Card(Rank.NINE, Shape.HEART);

        dealer.setUpCardDeck(tenSpade, tenDiamond);
        winnerPlayer1.setUpCardDeck(aceSpade, tenHeart);
        drawPlayer1.setUpCardDeck(tenClover, jackSpade);
        drawPlayer2.setUpCardDeck(jackDiamond, jackHeart);
        losePlayer1.setUpCardDeck(kingSpade, nineSpade);
        losePlayer2.setUpCardDeck(kingDiamond, nineDiamond);
        losePlayer3.setUpCardDeck(kingHeart, nineHeart);

        //when
        GameResult gameResult = gameManager.evaluateFinalScore();

        int dealerWinCount = gameResult.countDealerWin();
        int dealerDrawCount = gameResult.countDealerDraw();
        int dealerLoseCount = gameResult.countDealerLose();

        //then
        assertAll(
            () -> assertThat(dealerWinCount).isEqualTo(3),
            () -> assertThat(dealerDrawCount).isEqualTo(2),
            () -> assertThat(dealerLoseCount).isEqualTo(1)
        );
    }
}

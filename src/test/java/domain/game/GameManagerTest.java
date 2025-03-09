package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
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

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);
        winnerPlayer1.takeCards(Card.SPADE_A, Card.HEART_10);
        drawPlayer1.takeCards(Card.CLOVER_10, Card.SPADE_J);
        drawPlayer2.takeCards(Card.DIAMOND_J, Card.HEART_J);
        losePlayer1.takeCards(Card.SPADE_K, Card.SPADE_9);
        losePlayer2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9);
        losePlayer3.takeCards(Card.HEART_K, Card.HEART_9);

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

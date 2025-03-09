package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.CardPack;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GamblersTest {

    private final Dealer dealer = new Dealer();
    private final Player player = new Player("이름");

    @Test
    void 딜러와_플레이어들로_Gamblers를_생성한다() {
        //given
        Player player1 = new Player("이름1");
        Player player2 = new Player("이름2");

        //when
        //then
        assertThatCode(() -> new Gamblers(dealer, List.of(player1, player2)))
            .doesNotThrowAnyException();
    }

    @Test
    void Gambler들에게_초기카드를_나눠준다() {
        //given
        Gamblers gamblers = new Gamblers(dealer, List.of(player));

        //when
        gamblers.distributeSetUpCards(new CardPack(Card.allCards()));

        //then
        assertAll(
            () -> assertThat(dealer.getCards()).hasSize(2),
            () -> assertThat(player.getCards()).hasSize(2)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "SPADE_A,SPADE_10,WIN",
        "SPADE_10,DIAMOND_10,DRAW",
        "SPADE_10,SPADE_9,LOSE"
    })
    void 딜러와_비교하여_플레이어의_승무패를_가린다(Card playerCard1, Card playerCard2, Winning expectedWinning) {
        //given
        Gamblers gamblers = new Gamblers(dealer, List.of(player));
        dealer.takeCards(Card.CLOVER_J, Card.CLOVER_K);
        player.takeCards(playerCard1, playerCard2);

        //when
        Map<Player, Winning> playerWinnings = gamblers.evaluatePlayerWinnings();

        //then
        assertThat(playerWinnings.get(player)).isEqualTo(expectedWinning);
    }

    @Test
    void 딜러의_승무패_횟수를_계산한다() {
        //given
        Player winnerPlayer1 = new Player("이름1");
        Player drawPlayer1 = new Player("이름2");
        Player drawPlayer2 = new Player("이름3");
        Player losePlayer1 = new Player("이름4");
        Player losePlayer2 = new Player("이름5");
        Player losePlayer3 = new Player("이름6");

        Gamblers gamblers = new Gamblers(
            dealer,
            List.of(winnerPlayer1, drawPlayer1, drawPlayer2,
                losePlayer1, losePlayer2, losePlayer3));

        dealer.takeCards(Card.SPADE_10, Card.DIAMOND_10);

        winnerPlayer1.takeCards(Card.SPADE_A, Card.HEART_10); // 21
        drawPlayer1.takeCards(Card.CLOVER_10, Card.SPADE_J); // 20
        drawPlayer2.takeCards(Card.DIAMOND_J, Card.HEART_J); // 20
        losePlayer1.takeCards(Card.SPADE_K, Card.SPADE_9); // 19
        losePlayer2.takeCards(Card.DIAMOND_K, Card.DIAMOND_9); // 19
        losePlayer3.takeCards(Card.HEART_K, Card.HEART_9); // 19

        //when
        WinningCounts winningCounts = gamblers.evaluateDealerWinnings();

        int dealerWinCount = winningCounts.winCount();
        int dealerDrawCount = winningCounts.drawCount();
        int dealerLoseCount = winningCounts.loseCount();

        //then
        assertAll(
            () -> assertThat(dealerWinCount).isEqualTo(3),
            () -> assertThat(dealerDrawCount).isEqualTo(2),
            () -> assertThat(dealerLoseCount).isEqualTo(1)
        );
    }
}

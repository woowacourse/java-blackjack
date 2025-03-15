package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Shape;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
public class GameManagerTest {

    private Dealer dealer;
    private Player winnerPlayer;
    private Player drawPlayer1;
    private Player drawPlayer2;
    private Player losePlayer1;
    private Player losePlayer2;
    private Player losePlayer3;
    private GameManager gameManager;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
        winnerPlayer = new Player("이름1");
        drawPlayer1 = new Player("이름2");
        drawPlayer2 = new Player("이름3");
        losePlayer1 = new Player("이름4");
        losePlayer2 = new Player("이름5");
        losePlayer3 = new Player("이름6");

        gameManager = new GameManager(
                dealer,
                List.of(winnerPlayer, drawPlayer1, drawPlayer2,
                        losePlayer1, losePlayer2, losePlayer3)
        );
    }

    private void setUpDealerAndPlayersCards() {
        dealer.setUpCardDeck(new Card(Rank.TEN, Shape.SPADE), new Card(Rank.TEN, Shape.DIAMOND)); // 20점
        winnerPlayer.setUpCardDeck(new Card(Rank.ACE, Shape.SPADE), new Card(Rank.TEN, Shape.HEART)); // 21점 (승)
        drawPlayer1.setUpCardDeck(new Card(Rank.KING, Shape.SPADE), new Card(Rank.TEN, Shape.CLOVER)); // 20점 (무)
        drawPlayer2.setUpCardDeck(new Card(Rank.JACK, Shape.SPADE), new Card(Rank.JACK, Shape.DIAMOND)); // 20점 (무)
        losePlayer1.setUpCardDeck(new Card(Rank.NINE, Shape.SPADE), new Card(Rank.KING, Shape.DIAMOND)); // 19점 (패)
        losePlayer2.setUpCardDeck(new Card(Rank.NINE, Shape.HEART), new Card(Rank.KING, Shape.HEART)); // 19점 (패)
        losePlayer3.setUpCardDeck(new Card(Rank.NINE, Shape.DIAMOND), new Card(Rank.JACK, Shape.HEART)); // 19점 (패)
    }

    @Test
    void 딜러와_플레이어들로_카드매니저를_생성한다() {
        //given
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
}

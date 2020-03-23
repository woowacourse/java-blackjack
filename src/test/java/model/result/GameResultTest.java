package model.result;

import model.card.Card;
import model.card.Symbol;
import model.card.Type;
import model.user.Dealer;
import model.user.Player;
import model.user.Players;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static model.user.BlackJackGameUserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    Player playerWin1 = new Player(PLAYER_NAME, Arrays.asList(
        new Card(Symbol.KING, Type.CLUB),
        new Card(Symbol.KING, Type.DIAMOND)
    ));
    Player playerWin2 = new Player(PLAYER_NAME, Arrays.asList(
        new Card(Symbol.QUEEN, Type.CLUB),
        new Card(Symbol.KING, Type.HEART)
    ));
    Player playerBlackJack = new Player(PLAYER_NAME, Arrays.asList(
        new Card(Symbol.QUEEN, Type.CLUB),
        new Card(Symbol.ACE, Type.HEART)
    ));
    Dealer dealerLose = new Dealer(Arrays.asList(
        new Card(Symbol.TWO, Type.CLUB),
        new Card(Symbol.TWO, Type.DIAMOND)
    ));
    Dealer dealerBlackJack = new Dealer(Arrays.asList(
        new Card(Symbol.ACE, Type.CLUB),
        new Card(Symbol.JACK, Type.DIAMOND)
    ));
    Players players = new Players(Arrays.asList(playerWin1, playerWin2, playerBlackJack));

    @Test
    void calculateDealerResultTest1() {
        GameResult gameResult = new GameResult(players, dealerLose);
        assertThat(gameResult.getDealerResult().getRevenue()).isEqualTo(-350);
    }

    @Test
    void calculateDealerResultTest2() {
        GameResult gameResult = new GameResult(players, dealerBlackJack);
        assertThat(gameResult.getDealerResult().getRevenue()).isEqualTo(200);
    }
}
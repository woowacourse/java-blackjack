package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.UserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {
    static CardHand cardHandWin = new CardHand();
    static CardHand cardHandLose = new CardHand();
    static List<Player> players = new ArrayList<>();
    static Dealer dealer = new Dealer(cardHandLose);
    static {
        cardHandWin.addCard(new Card(Symbol.KING, Type.CLUB));
        cardHandWin.addCard(new Card(Symbol.KING, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.CLUB));
        players.add(new Player(PLAYER_NAME, cardHandWin));
        players.add(new Player(PLAYER_NAME, cardHandWin));
    }

    @Test
    void gameResultTest(){
        GameResult gameResult = new GameResult(new Players(players), dealer);
        gameResult.calculateResults();
        assertThat(gameResult.getDealerResult().get(Result.LOSE)).isEqualTo(2);
    }

}
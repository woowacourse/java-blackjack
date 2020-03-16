package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static controller.BlackJackGame.INITIAL_DRAW_COUNT;
import static model.UserTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    CardHand cardHandWin1 = new CardHand();
    CardHand cardHandWin2 = new CardHand();
    CardHand cardHandLose = new CardHand();
    Deck deckWin1;
    Deck deckWin2;
    Deck deckLose;
    List<Player> players = new ArrayList<>();
    Dealer dealer;

    @BeforeEach
    void init_field() {
        cardHandWin1.addCard(new Card(Symbol.KING, Type.CLUB));
        cardHandWin1.addCard(new Card(Symbol.KING, Type.DIAMOND));
        cardHandWin2.addCard(new Card(Symbol.KING, Type.CLUB));
        cardHandWin2.addCard(new Card(Symbol.KING, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.DIAMOND));
        cardHandLose.addCard(new Card(Symbol.TWO, Type.CLUB));
        deckWin1 = new Deck(cardHandWin1);
        deckWin2 = new Deck(cardHandWin2);
        deckLose = new Deck(cardHandLose);
        players.add(new Player(PLAYER_NAME, deckWin1, INITIAL_DRAW_COUNT));
        players.add(new Player(PLAYER_NAME, deckWin2, INITIAL_DRAW_COUNT));
        dealer = new Dealer(deckLose, INITIAL_DRAW_COUNT);
    }

    @Test
    void gameResultTest() {
        GameResult gameResult = new GameResult(new Players(players), dealer);
        gameResult.calculateResults();
        assertThat(gameResult.getDealerResult().get(Result.LOSE)).isEqualTo(2);
    }
}
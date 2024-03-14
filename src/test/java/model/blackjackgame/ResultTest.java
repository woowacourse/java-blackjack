package model.blackjackgame;

import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ACE;
import static model.card.CardNumber.SEVEN;
import static model.card.CardNumber.SIX;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.dealer.Dealer;
import model.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    @DisplayName("딜러와 플레이어 중 카드 합이 21 또는 21에 가까운 숫자가 승리")
    @Test
    void testWinnerWhenCardSumIsTwentyOne() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(JACK, HEART),
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(ACE, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertTrue(result.getPlayerResult().containsKey(blackjackGame.getPlayers().getPlayers().get(0).getName()));
        assertTrue(result.getPlayerResult().containsValue("승"));
    }

    @DisplayName("딜러의 결과는 플레이어의 수만큼 존재")
    @Test
    void testDealerResult() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(JACK, HEART),
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(SEVEN, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertEquals("1승 1패", result.getDealerResult());
    }

    @DisplayName("플레이어가 블랙잭인 경우")
    @Test
    void testPlayerBlackjack() {
        BlackjackGame blackjackGame = prepareBlackjackGame();
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ACE, HEART),
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(ACE, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer());
        blackjack.playerBlackjackStatus(blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertTrue(result.getPlayerResult().containsKey(blackjackGame.getPlayers().getPlayers().get(0).getName()));
        assertTrue(result.getPlayerResult().containsValue("블랙잭"));
    }

    private BlackjackGame prepareBlackjackGame() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(dealer, players);
    }
}

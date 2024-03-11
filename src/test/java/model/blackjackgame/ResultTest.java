package model.blackjackgame;

import static model.card.CardNumber.FIVE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ONE;
import static model.card.CardNumber.SIX;
import static model.card.CardShape.CLOVER;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static model.card.CardShape.SPADE;
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
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(ONE, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers());
        assertTrue(result.getPlayerResult().containsKey(blackjackGame.getPlayers().getPlayers().get(0).getName()));
        assertTrue(result.getPlayerResult().containsValue("승"));
    }

    private BlackjackGame prepareBlackjackGame() {
        Dealer dealer = new Dealer();
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(dealer, players);
    }
}

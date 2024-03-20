package blackjackgame.model.blackjackgame;

import static blackjackgame.model.card.CardNumber.ACE;
import static blackjackgame.model.card.CardNumber.FIVE;
import static blackjackgame.model.card.CardNumber.JACK;
import static blackjackgame.model.card.CardNumber.SIX;
import static blackjackgame.model.card.CardShape.CLOVER;
import static blackjackgame.model.card.CardShape.DIAMOND;
import static blackjackgame.model.card.CardShape.HEART;
import static blackjackgame.model.card.CardShape.SPADE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import blackjackgame.model.card.Card;
import blackjackgame.model.card.Cards;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Players;
import blackjackgame.model.result.GameResult;
import blackjackgame.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private final BlackjackGame blackjackGame = prepareBlackjackGame();

    @DisplayName("딜러와 플레이어 중 카드 합이 21 또는 21에 가까운 숫자가 승리")
    @Test
    void testWinnerWhenCardSumIsTwentyOne() {
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(JACK, HEART),
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(ACE, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertEquals(GameResult.WIN,
                result.getPlayerResult().get(blackjackGame.getPlayers().getPlayers().get(0).getName()));
    }

    @DisplayName("플레이어가 블랙잭인 경우")
    @Test
    void testPlayerBlackjack() {
        Cards cards = new Cards(
                List.of(new Card(JACK, DIAMOND), new Card(FIVE, CLOVER), new Card(ACE, HEART),
                        new Card(JACK, CLOVER), new Card(SIX, DIAMOND), new Card(ACE, SPADE))
        );
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertEquals(GameResult.BLACKJACK,
                result.getPlayerResult().get(blackjackGame.getPlayers().getPlayers().get(0).getName()));
    }

    private BlackjackGame prepareBlackjackGame() {
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(new Dealer(), players);
    }
}

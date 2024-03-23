package blackjackgame.model.blackjackgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import blackjackgame.model.card.Card;
import blackjackgame.model.card.CardNumber;
import blackjackgame.model.card.CardShape;
import blackjackgame.model.card.Cards;
import blackjackgame.model.card.StaticCardDispenser;
import blackjackgame.model.participants.dealer.Dealer;
import blackjackgame.model.participants.player.Players;
import blackjackgame.model.result.GameResult;
import blackjackgame.model.result.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {

    private final BlackjackGame blackjackGame = prepareBlackjackGame();

    @DisplayName("딜러와 플레이어 중 카드 합이 21 또는 21에 가까운 숫자가 승리")
    @Test
    void testWinnerWhenCardSumIsTwentyOne() {
        Cards cards = createWinnerTestCards();
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertEquals(GameResult.WIN,
                result.getPlayerResult().get(blackjackGame.getPlayers().getPlayers().get(0).getName()));
    }

    private Cards createWinnerTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardJackHeart = new StaticCardDispenser(CardNumber.JACK, CardShape.HEART);
        StaticCardDispenser cardJackClover = new StaticCardDispenser(CardNumber.JACK, CardShape.CLOVER);
        StaticCardDispenser cardSixDia = new StaticCardDispenser(CardNumber.SIX, CardShape.DIAMOND);
        StaticCardDispenser cardAceSpade = new StaticCardDispenser(CardNumber.ACE, CardShape.SPADE);
        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardFiveClover), new Card(cardJackHeart),
                        new Card(cardJackClover), new Card(cardSixDia), new Card(cardAceSpade))
        );
    }

    @DisplayName("플레이어가 블랙잭인 경우")
    @Test
    void testPlayerBlackjack() {
        Cards cards = createPlayerBlackjackTestCards();
        blackjackGame.distributeCardsForSetting(cards);
        Blackjack blackjack = new Blackjack(blackjackGame.getDealer(), blackjackGame.getPlayers());
        Result result = new Result(blackjackGame.getDealer(), blackjackGame.getPlayers(), blackjack);
        assertEquals(GameResult.BLACKJACK,
                result.getPlayerResult().get(blackjackGame.getPlayers().getPlayers().get(0).getName()));
    }

    private Cards createPlayerBlackjackTestCards() {
        StaticCardDispenser cardJackDia = new StaticCardDispenser(CardNumber.JACK, CardShape.DIAMOND);
        StaticCardDispenser cardFiveClover = new StaticCardDispenser(CardNumber.FIVE, CardShape.CLOVER);
        StaticCardDispenser cardAceHeart = new StaticCardDispenser(CardNumber.ACE, CardShape.HEART);
        StaticCardDispenser cardJackClover = new StaticCardDispenser(CardNumber.JACK, CardShape.CLOVER);
        StaticCardDispenser cardSixDia = new StaticCardDispenser(CardNumber.SIX, CardShape.DIAMOND);
        StaticCardDispenser cardAceSpade = new StaticCardDispenser(CardNumber.ACE, CardShape.SPADE);
        return new Cards(
                List.of(new Card(cardJackDia), new Card(cardFiveClover), new Card(cardAceHeart),
                        new Card(cardJackClover), new Card(cardSixDia), new Card(cardAceSpade))
        );
    }

    private BlackjackGame prepareBlackjackGame() {
        Players players = Players.from(List.of("lily", "jojo"));
        return new BlackjackGame(new Dealer(), players);
    }
}

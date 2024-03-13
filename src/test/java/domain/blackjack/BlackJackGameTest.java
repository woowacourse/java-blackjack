package domain.blackjack;

import static domain.card.Card.TWO_HEART;

import domain.card.Card;
import domain.card.Deck;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private static void doNothing(Player player) {
    }

    @Test
    @DisplayName("플레이어들의 드로우를 했을 때 제대로 드로우 되는지 검증")
    void playersDraw() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("player", "robin"));
        List<Boolean> fixedYesOrNo = new ArrayList<>(List.of(true, false, true, false));
        blackJackGame.playersDraw(Deck.of(Card.FIVE_HEART, Card.SIX_HEART), BlackJackGameTest::doNothing,
                playerName -> fixedYesOrNo.remove(0));

        Players players = blackJackGame.getPlayers();
        Dealer bustedDealer = Dealer.of(HoldingCards.of(TWO_HEART));
        List<GameResult> gameResults = players.calculateGameResultsWith(bustedDealer);
        Assertions.assertThat(gameResults)
                .containsExactly(GameResult.WIN, GameResult.WIN);
    }

    @Test
    @DisplayName("딜러가 드로우를 했을 때 제대로 드로우 되는지 검증")
    void dealerDraw() {
        BlackJackGame blackJackGame = new BlackJackGame(List.of("player", "robin"));
        blackJackGame.dealerTryDraw(Deck.of(Card.FIVE_HEART));
        Dealer dealer = blackJackGame.getDealer();

        Player player = Player.from("플레이어", HoldingCards.of(TWO_HEART));
        GameResult gameResult = dealer.calculateGameResult(player);
        Assertions.assertThat(gameResult)
                .isEqualTo(GameResult.WIN);
    }
}

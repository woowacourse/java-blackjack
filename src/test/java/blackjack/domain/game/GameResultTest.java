package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardValue;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Nickname;
import blackjack.domain.user.Player;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    @Test
    @DisplayName("승패를 종합할 수 있다.")
    void canGetDealerWinningState() {
        GameResult gameResult = setGameResult();
        assertThat(gameResult.getDealerWinningState(WinningType.WIN))
                .isEqualTo(1);
        assertThat(gameResult.getPlayerWinnings().getFirst().getWinningType())
                .isEqualTo(WinningType.LOSE);
    }

    public GameResult setGameResult() {
        List<Card> dealerCards = List.of(
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.QUEEN));
        Dealer dealer = new Dealer();
        dealer.addInitialCards(dealerCards);

        List<Card> playerCards = List.of(
                new Card(CardShape.HEART, CardValue.JACK),
                new Card(CardShape.HEART, CardValue.EIGHT));
        Player player = new Player(new Nickname("쿠키"));
        player.addInitialCards(playerCards);

        return new GameResult(dealer, List.of(player));
    }

}
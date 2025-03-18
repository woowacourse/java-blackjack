package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameScore;
import blackjack.domain.player.BetAmount;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드의 합을 계산해 반환한다")
    void calculate_calculation_check_rot() {
        // given
        Player player = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        player.pushDealCards(List.of(
                new Card(CardNumber.QUEEN, CardShape.CLOVER),
                new Card(CardNumber.KING, CardShape.CLOVER),
                new Card(CardNumber.JACK, CardShape.CLOVER)
        ));

        // when
        GameScore result = player.getGameScore();

        // then
        assertThat(result).isEqualTo(new GameScore(30, 3));
    }

    @DisplayName("에이스가 2장이면 12로 계산한다")
    @Test
    void ace_test() {
        Player player = new Gambler(new PlayerName("두리"), new BetAmount(0), new Cards());
        player.pushDealCards(List.of(
                        new Card(CardNumber.ACE, CardShape.CLOVER),
                        new Card(CardNumber.ACE, CardShape.DIAMOND)
                )

        );

        GameScore result = player.getGameScore();

        assertThat(result).isEqualTo(new GameScore(12, 2));
    }
}

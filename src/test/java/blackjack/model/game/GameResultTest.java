package blackjack.model.game;

import static blackjack.model.game.GameResult.calculateResult;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.model.card.CardCreator;
import blackjack.model.card.CardNumber;
import blackjack.model.card.Cards;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class GameResultTest {

    @CsvSource(value = {
            "TWO,TWO,DRAW", "THREE,TWO,WIN", "TWO,THREE,LOSE"
    })
    @ParameterizedTest
    void 플레이어와_상대방을_알려주면_결과를_계산한다(CardNumber playerCard, CardNumber challengerCard, GameResult expected) {
        Player player = new User("pobi");
        Player challenger = new User("json");
        player.receiveCards(new Cards(CardCreator.createCard(playerCard)));
        challenger.receiveCards(new Cards(CardCreator.createCard(challengerCard)));

        assertThat(calculateResult(player, challenger, new BlackJackRule()))
                .isEqualByComparingTo(expected);
    }

}

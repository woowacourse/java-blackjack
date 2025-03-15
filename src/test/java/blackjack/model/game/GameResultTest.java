package blackjack.model.game;

import static blackjack.model.card.CardCreator.createCard;
import static blackjack.model.card.CardNumber.ACE;
import static blackjack.model.card.CardNumber.EIGHT;
import static blackjack.model.card.CardNumber.NINE;
import static blackjack.model.card.CardNumber.TEN;
import static blackjack.model.card.CardNumber.THREE;
import static blackjack.model.game.GameResult.DRAW;
import static blackjack.model.game.GameResult.LOSE;
import static blackjack.model.game.GameResult.WIN;
import static blackjack.model.game.GameResult.calculateResult;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.model.card.Cards;
import blackjack.model.player.Player;
import blackjack.model.player.User;

class GameResultTest {

    @MethodSource("플레이어와_상대방을_알려주면_결과를_계산한다_테스트_케이스")
    @ParameterizedTest
    void 플레이어와_상대방을_알려주면_결과를_계산한다(Cards playerCards, Cards otherPlayerCards, GameResult expected) {
        Player player = new User("pobi");
        Player challenger = new User("json");
        player.receiveCards(playerCards);
        challenger.receiveCards(otherPlayerCards);

        assertThat(calculateResult(player, challenger, new BlackJackRule()))
                .isEqualByComparingTo(expected);
    }

    private static Stream<Arguments> 플레이어와_상대방을_알려주면_결과를_계산한다_테스트_케이스() {
        return Stream.of(
                Arguments.of(
                        new Cards(createCard(TEN), createCard(ACE)),
                        new Cards(createCard(TEN), createCard(NINE)),
                        WIN
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(NINE)),
                        new Cards(createCard(TEN), createCard(NINE), createCard(THREE)),
                        WIN
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(NINE)),
                        new Cards(createCard(TEN), createCard(EIGHT)),
                        WIN
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(ACE)),
                        new Cards(createCard(TEN), createCard(ACE)),
                        DRAW
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(NINE)),
                        new Cards(createCard(TEN), createCard(NINE)),
                        DRAW
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(NINE)),
                        new Cards(createCard(TEN), createCard(ACE)),
                        LOSE
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(EIGHT)),
                        new Cards(createCard(TEN), createCard(NINE)),
                        LOSE
                ),
                Arguments.of(
                        new Cards(createCard(TEN), createCard(NINE), createCard(THREE)),
                        new Cards(createCard(TEN), createCard(NINE), createCard(THREE)),
                        LOSE
                )
        );
    }

}

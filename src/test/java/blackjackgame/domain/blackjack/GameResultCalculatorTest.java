package blackjackgame.domain.blackjack;

import static blackjackgame.domain.blackjack.GameResult.LOSE;
import static blackjackgame.domain.blackjack.GameResult.TIE;
import static blackjackgame.domain.blackjack.GameResult.WIN;
import static blackjackgame.domain.card.CardName.ACE;
import static blackjackgame.domain.card.CardName.JACK;
import static blackjackgame.domain.card.CardName.NINE;
import static blackjackgame.domain.card.CardName.QUEEN;
import static blackjackgame.domain.card.CardName.SEVEN;
import static blackjackgame.domain.card.CardName.SIX;
import static blackjackgame.domain.card.CardName.TWO;
import static blackjackgame.domain.card.CardType.DIAMOND;
import static blackjackgame.domain.card.CardType.HEART;

import blackjackgame.domain.card.Card;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameResultCalculatorTest {
    private static final String firstGamerName = "게이머1";
    private static final String secondGamerName = "게이머2";
    private static final HoldingCards ONLY_SIX_HEART = HoldingCards.of(new Card(SIX, HEART));
    private static final HoldingCards ONLY_SEVEN_HEART = HoldingCards.of(new Card(SEVEN, HEART));
    private static final HoldingCards DEAD_CARDS = HoldingCards.of(
            new Card(JACK, HEART), new Card(QUEEN, HEART), new Card(TWO, HEART)
    );
    private static final HoldingCards WIN_CARDS_WITH_ACE = HoldingCards.of(
            new Card(ACE, HEART), new Card(QUEEN, HEART)
    );
    private static final HoldingCards WIN_CARDS_WITHOUT_ACE = HoldingCards.of(
            new Card(JACK, HEART), new Card(NINE, HEART), new Card(TWO, HEART)
    );
    private static final HoldingCards TWO_SIX_CARDS = HoldingCards.of(
            new Card(SIX, HEART), new Card(SIX, DIAMOND)
    );

    public static Stream<Arguments> getGameResultParameters() {
        return Stream.of(
                Arguments.of(new CardHolderGamer(firstGamerName, ONLY_SEVEN_HEART), new CardHolderGamer(secondGamerName, ONLY_SIX_HEART),
                        WIN),
                Arguments.of(new CardHolderGamer(firstGamerName, ONLY_SIX_HEART), new CardHolderGamer(secondGamerName, ONLY_SEVEN_HEART),
                        LOSE),
                Arguments.of(new CardHolderGamer(firstGamerName, ONLY_SEVEN_HEART), new CardHolderGamer(secondGamerName, ONLY_SEVEN_HEART),
                        TIE),
                Arguments.of(new CardHolderGamer(firstGamerName, DEAD_CARDS), new CardHolderGamer(secondGamerName, ONLY_SEVEN_HEART), LOSE),
                Arguments.of(new CardHolderGamer(firstGamerName, ONLY_SEVEN_HEART), new CardHolderGamer(secondGamerName, DEAD_CARDS), WIN),
                Arguments.of(new CardHolderGamer(firstGamerName, DEAD_CARDS), new CardHolderGamer(secondGamerName, DEAD_CARDS), TIE),
                Arguments.of(new CardHolderGamer(firstGamerName, WIN_CARDS_WITH_ACE), new CardHolderGamer(secondGamerName, TWO_SIX_CARDS),
                        WIN),
                Arguments.of(new CardHolderGamer(firstGamerName, WIN_CARDS_WITH_ACE),
                        new CardHolderGamer(secondGamerName, WIN_CARDS_WITHOUT_ACE), TIE)
        );
    }

    @ParameterizedTest
    @MethodSource("getGameResultParameters")
    @DisplayName("승부가 잘 결정되는지 검증")
    void calculate(CardHolderGamer cardHolderGamer1, CardHolderGamer cardHolderGamer2, GameResult expected) {
        Assertions.assertThat(GameResultCalculator.calculate(cardHolderGamer1, cardHolderGamer2))
                .isEqualTo(expected);
    }
}

package domain.helper;

import static domain.card.Denomination.ACE;
import static domain.card.Denomination.FOUR;
import static domain.card.Denomination.JACK;
import static domain.card.Denomination.KING;
import static domain.card.Denomination.QUEEN;
import static domain.card.Denomination.SIX;
import static domain.card.Denomination.TEN;
import static domain.card.Denomination.THREE;
import static domain.card.Denomination.TWO;
import static domain.card.Shape.CLOVER;
import static domain.card.Shape.DIAMOND;
import static domain.card.Shape.HEART;
import static domain.card.Shape.SPADE;
import static domain.game.GameResult.BLACKJACK_WIN;
import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;

import domain.card.Card;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class ParticipantArguments {

    private ParticipantArguments() {
    }

    private static Stream<Arguments> validPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto")),
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron"))
        );
    }

    private static Stream<Arguments> invalidPlayerNames() {
        return Stream.of(
                Arguments.of(List.of("zeeto", "journey", "pobi", "neo", "lisa", "wonnie", "cron", "juno"))
        );
    }

    private static Stream<Arguments> makeCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, THREE), Card.of(SPADE, QUEEN)), 16),
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, TWO), Card.of(SPADE, TWO)), 17)
        );
    }

    private static Stream<Arguments> makeBustCard() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, THREE), Card.of(SPADE, QUEEN)), false),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, TEN), Card.of(SPADE, TWO)), true)
        );
    }

    private static Stream<Arguments> makeBlackJackCard() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(SPADE, TWO)), false),
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, QUEEN)), true)
        );
    }

    private static Stream<Arguments> makePlayerCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, THREE), Card.of(SPADE, QUEEN)), true),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, TEN), Card.of(SPADE, TWO)), false)
        );
    }

    private static Stream<Arguments> makeDealerCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, THREE), Card.of(SPADE, QUEEN)), true),
                Arguments.of(List.of(Card.of(HEART, ACE), Card.of(DIAMOND, TWO),
                        Card.of(CLOVER, TWO), Card.of(SPADE, TWO)), false)
        );
    }

    private static Stream<Arguments> makeParticipantsCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, FOUR), Card.of(HEART, THREE)),
                        List.of(Card.of(DIAMOND, THREE), Card.of(DIAMOND, TWO)), LOSE),
                Arguments.of(List.of(Card.of(HEART, TWO), Card.of(HEART, THREE)),
                        List.of(Card.of(DIAMOND, TWO), Card.of(DIAMOND, THREE)), DRAW),
                Arguments.of(List.of(Card.of(HEART, TWO), Card.of(HEART, THREE)),
                        List.of(Card.of(DIAMOND, FOUR), Card.of(DIAMOND, THREE)), WIN)
        );
    }

    private static Stream<Arguments> makePlayerWinByBlackJack() {
        return Stream.of(
                Arguments.of(List.of(Card.of(DIAMOND, THREE), Card.of(DIAMOND, TWO)),
                        List.of(Card.of(HEART, ACE), Card.of(HEART, TEN)),
                        BLACKJACK_WIN),
                Arguments.of(List.of(Card.of(DIAMOND, TEN), Card.of(HEART, TEN), Card.of(CLOVER, TEN)),
                        List.of(Card.of(HEART, ACE), Card.of(HEART, TEN)),
                        BLACKJACK_WIN)
        );
    }

    private static Stream<Arguments> makeDrawByBlackJack() {
        return Stream.of(
                Arguments.of(List.of(Card.of(DIAMOND, TEN), Card.of(DIAMOND, ACE)),
                        List.of(Card.of(HEART, ACE), Card.of(HEART, TEN)),
                        DRAW)
        );
    }

    private static Stream<Arguments> makeLoseByBust() {
        return Stream.of(
                Arguments.of(List.of(Card.of(DIAMOND, TEN), Card.of(HEART, TEN), Card.of(CLOVER, TEN)),
                        List.of(Card.of(DIAMOND, KING), Card.of(HEART, QUEEN), Card.of(CLOVER, JACK)),
                        LOSE)
        );
    }

    private static Stream<Arguments> makeLoseByPlayerBust() {
        return Stream.of(
                Arguments.of(List.of(Card.of(DIAMOND, TEN), Card.of(HEART, TEN)),
                        List.of(Card.of(DIAMOND, KING), Card.of(HEART, QUEEN), Card.of(CLOVER, JACK)),
                        LOSE),
                Arguments.of(List.of(Card.of(DIAMOND, TEN), Card.of(HEART, ACE)),
                        List.of(Card.of(DIAMOND, KING), Card.of(HEART, QUEEN), Card.of(CLOVER, JACK)),
                        LOSE)
        );
    }

    private static Stream<Arguments> makeDrawCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, FOUR), Card.of(HEART, THREE)), true),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, JACK),
                        Card.of(HEART, KING)), false),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, SIX)), true),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, SIX)), true)
        );
    }
}

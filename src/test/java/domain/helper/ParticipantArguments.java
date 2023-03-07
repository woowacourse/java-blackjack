package domain.helper;

import static domain.card.CardNumber.ACE;
import static domain.card.CardNumber.FOUR;
import static domain.card.CardNumber.JACK;
import static domain.card.CardNumber.KING;
import static domain.card.CardNumber.QUEEN;
import static domain.card.CardNumber.SIX;
import static domain.card.CardNumber.TEN;
import static domain.card.CardNumber.THREE;
import static domain.card.CardNumber.TWO;
import static domain.card.CardPattern.CLOVER;
import static domain.card.CardPattern.DIAMOND;
import static domain.card.CardPattern.HEART;
import static domain.card.CardPattern.SPADE;
import static domain.participant.ParticipantOffset.DEALER;
import static domain.participant.ParticipantOffset.PLAYER;
import static domain.participant.Result.DRAW;
import static domain.participant.Result.LOSE;
import static domain.participant.Result.WIN;

import domain.card.Card;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

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

    private static Stream<Arguments> makeDrawCards() {
        return Stream.of(
                Arguments.of(List.of(Card.of(HEART, FOUR), Card.of(HEART, THREE)),
                        PLAYER, true),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, JACK),
                        Card.of(HEART, KING)), PLAYER, false),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, SIX)),
                        DEALER, true),
                Arguments.of(List.of(Card.of(HEART, QUEEN), Card.of(HEART, SIX)),
                        DEALER, true)
        );
    }
}

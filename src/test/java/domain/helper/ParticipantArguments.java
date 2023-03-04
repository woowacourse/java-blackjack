package domain.helper;

import static domain.card.CardNumber.ACE;
import static domain.card.CardNumber.FOUR;
import static domain.card.CardNumber.QUEEN;
import static domain.card.CardNumber.TEN;
import static domain.card.CardNumber.THREE;
import static domain.card.CardNumber.TWO;
import static domain.card.CardPattern.CLOVER;
import static domain.card.CardPattern.DIAMOND;
import static domain.card.CardPattern.HEART;
import static domain.card.CardPattern.SPADE;
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
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, THREE), Card.create(SPADE, QUEEN)), 16),
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, TWO), Card.create(SPADE, TWO)), 17)
        );
    }

    private static Stream<Arguments> makeBustCard() {
        return Stream.of(
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, THREE), Card.create(SPADE, QUEEN)), false),
                Arguments.of(List.of(Card.create(HEART, QUEEN), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, TEN), Card.create(SPADE, TWO)), true)
        );
    }

    private static Stream<Arguments> makePlayerCards() {
        return Stream.of(
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, THREE), Card.create(SPADE, QUEEN)), true),
                Arguments.of(List.of(Card.create(HEART, QUEEN), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, TEN), Card.create(SPADE, TWO)), false)
        );
    }

    private static Stream<Arguments> makeDealerCards() {
        return Stream.of(
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, THREE), Card.create(SPADE, QUEEN)), true),
                Arguments.of(List.of(Card.create(HEART, ACE), Card.create(DIAMOND, TWO),
                        Card.create(CLOVER, TWO), Card.create(SPADE, TWO)), false)
        );
    }

    private static Stream<Arguments> makeParticipantsCards() {
        return Stream.of(
                Arguments.of(List.of(Card.create(HEART, FOUR), Card.create(HEART, THREE)),
                        List.of(Card.create(DIAMOND, THREE), Card.create(DIAMOND, TWO)), LOSE),
                Arguments.of(List.of(Card.create(HEART, TWO), Card.create(HEART, THREE)),
                        List.of(Card.create(DIAMOND, TWO), Card.create(DIAMOND, THREE)), DRAW),
                Arguments.of(List.of(Card.create(HEART, TWO), Card.create(HEART, THREE)),
                        List.of(Card.create(DIAMOND, FOUR), Card.create(DIAMOND, THREE)), WIN)
        );
    }
}

package blackjack.util;

import static blackjack.domain.card.Pattern.CLOVER;
import static blackjack.domain.card.Pattern.DIAMOND;
import static blackjack.domain.card.Pattern.HEART;

import blackjack.domain.Betting;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Pattern;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BlackjackTestUtil {

    public static Player createPlayer(int expectedScore) {
        return new Player(new Name("player"), createCards(expectedScore), new Betting(1000));
    }

    public static Dealer createDealer(int expectedScore) {
        return new Dealer(createCards(expectedScore));
    }

    public static List<Card> createCards(int expectedScore) {
        if (expectedScore <= 3 || expectedScore > 21) {
            throw new IllegalArgumentException("불가능한 테스트");
        }
        if (expectedScore <= 11) {
            return List.of(createCard(CLOVER, 2), createCard(DIAMOND, expectedScore - 2));
        }
        if (expectedScore == 21) {
            return List.of(createCard(CLOVER, 10), createCard(DIAMOND, 1));
        }
        return List.of(createCard(CLOVER, 10), createCard(DIAMOND, expectedScore - 10));
    }

    public static CardDeck createDeck(int expectedScore, int... expectedScores) {
        List<Card> cards = new ArrayList<>();
        cards.add(createCard(HEART, expectedScore));
        cards.addAll(Arrays.stream(expectedScores)
                .mapToObj(score -> createCard(HEART, score))
                .collect(Collectors.toList()));
        return new CardDeck(cards);
    }

    private static Card createCard(Pattern pattern, int expectedDenomination) {
        Denomination actual = Arrays.stream(Denomination.values())
                .filter(denomination -> denomination.getValue() == expectedDenomination)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return Card.of(pattern, actual);
    }
}

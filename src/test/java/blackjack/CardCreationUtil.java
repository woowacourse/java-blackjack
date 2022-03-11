package blackjack;

import static blackjack.domain.card.Suit.HEART;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CardCreationUtil {

    private CardCreationUtil() {
    }

    public static List<Card> createCardList(Denomination... denominations) {
        return Arrays.stream(denominations)
                .map(denomination -> new Card(denomination, HEART))
                .collect(Collectors.toUnmodifiableList());
    }

    public static Hand createHand(Denomination... denominations) {
        Hand hand = new Hand();
        hand.add(createCardList(denominations).toArray(new Card[0]));
        return hand;
    }
}

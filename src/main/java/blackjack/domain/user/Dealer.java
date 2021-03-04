package blackjack.domain.user;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Dealer implements User {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final String DELIMITER = ", ";
    private static final String NO_DEALER_CARD_ERROR_MESSAGE = "카드가 없습니다.";

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public boolean canHit() {
        return getScore() <= DEALER_HIT_THRESHOLD;
    }

    public List<Integer> calculateMatchResult(Map<Player, Status> result) {
        List<Integer> winnings = new ArrayList<>();
        Arrays.stream(Status.values())
            .forEach(status -> winnings.add(Collections.frequency(result.values(), status)));
        return winnings;
    }

    public String getFirstCard() {
        return Arrays.stream(getCards().split(DELIMITER))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                NO_DEALER_CARD_ERROR_MESSAGE));
    }

    @Override
    public void hit(Card card) {
        this.cards.addCard(card);
    }

    @Override
    public String getCards() {
        return this.cards.getCards();
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }

    @Override
    public int getScore() {
        return cards.getScore();
    }
}

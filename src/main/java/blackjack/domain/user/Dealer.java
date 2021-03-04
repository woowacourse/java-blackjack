package blackjack.domain.user;

import blackjack.domain.Status;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.*;

public class Dealer implements User {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final String COMMA = ",";

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
        return Arrays.stream(getCards().split(COMMA)).findFirst().get();
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

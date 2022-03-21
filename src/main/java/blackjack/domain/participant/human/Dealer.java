package blackjack.domain.participant.human;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.cards.card.Card;
import blackjack.domain.participant.human.name.Name;
import java.util.List;
import java.util.Map;

public final class Dealer extends Participant {
    private static final int HIT_DEALER_POINT = 16;

    public Dealer(final List<Card> cards) {
        super(cards, new Name("딜러"));
    }

    public Card getFirstCard() {
        return state.cards().getFirstCard();
    }

    public boolean draw(CardDeck cardDeck) {
        if (isAbleToHit()) {
            addCard(cardDeck.pop());
        }
        stay();
        return !isInitState();
    }

    private boolean isAbleToHit() {
        return getPoint() <= HIT_DEALER_POINT;
    }

    public int getProfit(final Map<Player, Integer> profits) {
        return -profits.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}

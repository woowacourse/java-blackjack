package blackjack.domain.players.participant;

import blackjack.domain.cards.CardDeck;
import blackjack.domain.cards.card.Card;
import blackjack.domain.players.participant.name.Name;
import java.util.List;
import java.util.Map;

public final class Dealer extends Participant {
    private static final int HIT_DEALER_POINT = 16;

    public Dealer(final List<Card> cards) {
        super(cards, new Name("딜러"));
    }

    public void draw(CardDeck cardDeck) {
        if (isAbleToHit()) {
            addCard(cardDeck.pop());
        }
        stay();
    }

    public Card getFirstCard() {
        return state.cards().getFirstCard();
    }

    private boolean isAbleToHit() {
        return getPoint() <= HIT_DEALER_POINT;
    }

    public int getProfit(final Map<Player, Integer> profits) {
        return (-1) * profits.values()
                .stream()
                .mapToInt(Integer::valueOf)
                .sum();
    }
}

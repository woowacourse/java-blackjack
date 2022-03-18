package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayingCards {

    public static final int BLACKJACK = 21;

    private final List<Card> playingCards = new ArrayList<>();

    public void add(final List<Card> cards) {
        playingCards.addAll(cards);
    }

    public void add(final Card card) {
        playingCards.add(card);
    }

    public int total() {
        List<Denomination> denominations = getDenominations();
        return Denomination.getTotal(denominations);
    }

    public boolean isMoreDeal() {
        return playingCards.size() < Deck.INIT_CARD_COUNT;
    }

    public boolean isBust() {
        return total() > BLACKJACK;
    }

    public boolean isUnderBlackjack() {
        return total() < BLACKJACK;
    }

    public boolean isBlackjack() {
        List<Denomination> denominations = getDenominations();
        return Denomination.isBlackjack(denominations);
    }

    public List<Card> getPartOfCard() {
        if (playingCards.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(playingCards.get(0));
    }

    public List<Card> getAllCards() {
        return playingCards.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Denomination> getDenominations() {
        return playingCards.stream()
                .map(Card::getDenomination)
                .collect(Collectors.toUnmodifiableList());
    }
}

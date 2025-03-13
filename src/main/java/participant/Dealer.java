package participant;

import card.Card;
import card.Cards;
import card.Deck;
import strategy.DeckShuffleStrategy;

import java.util.Comparator;
import java.util.List;

public class Dealer {

    private static final int DEALER_HIT_THRESHOLD = 16;
    private static final int INITIAL_CARD_COUNT = 2;

    private final Cards cards;
    private final Deck deck;

    public Dealer(Cards cards) {
        validateInitialCardsSize(cards);
        this.cards = cards;
        this.deck = new Deck(new DeckShuffleStrategy());
    }

    public Card openOneCard() {
        return cards.getCards().stream()
                .min(Comparator.comparingInt(card -> card.getNumber().getValue()))
                .orElse(cards.getCards().getFirst());
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public boolean shouldDealerHit() {
        return sumCardNumbers() <= DEALER_HIT_THRESHOLD;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }

}

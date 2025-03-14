package blackjack.domain.card;
package blackjack.card;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards from(List<Card> cards) {
        return new Cards(cards);
    }

    public static Cards empty() {
        return new Cards(new ArrayList<>());
    }

    public void add(Card card) {
        this.cards.add(card);
    }

    public void add(Cards cards) {
        this.cards.addAll(cards.getCards());
    }

    public void add(CardDeck cardDeck) {
        add(cardDeck.getCards());
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        return cards.removeLast();
    }

    public int calculateSum() {
        List<AceCard> aces = getAces();

        List<NormalCard> nonAces = getNonAces();
        int sumWithoutAce = calculateSumWithoutAce(nonAces);

        return calculateTotalSum(sumWithoutAce, aces);
    }

    public void reverse(int cardIndex) {
        cards.get(cardIndex).reverse();
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    private List<AceCard> getAces() {
        return cards.stream()
                .filter(Card::isAce)
                .map(card -> (AceCard) card)
                .toList();
    }

    private List<NormalCard> getNonAces() {
        return cards.stream()
                .filter(Card::isNotAce)
                .map(card -> (NormalCard) card)
                .toList();
    }

    private int calculateSumWithoutAce(List<NormalCard> nonAces) {
        return nonAces.stream()
                .mapToInt(NormalCard::getPoint)
                .sum();
    }

    private int calculateTotalSum(int sumWithoutAce, List<AceCard> aces) {
        int accumulatedSum = sumWithoutAce;

        for (AceCard ace : aces) {
            accumulatedSum += ace.getPoint(accumulatedSum);
        }

        return accumulatedSum;
    }
}

package blackjack;

import java.util.Arrays;
import java.util.List;

public class Player implements Playable {
    private final String name;
    private final Cards cards;

    public Player(String name) {
        this(name, Arrays.asList(Card.from("A클로버"), Card.from("A클로버")));
    }

    public Player(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Card> getCards() {
        return cards.getUnmodifiableList();
    }

    @Override
    public void takeCard(Card card) {
        cards.add(card);
    }

    @Override
    public int sumCards() {
        List<Card> cardValues = cards.getUnmodifiableList();
        return cardValues.stream().mapToInt(Card::getScore).sum();
    }

    @Override
    public int sumCardsForResult() {
        List<Card> cardValues = cards.getUnmodifiableList();
        int sum = cardValues.stream().mapToInt(Card::getScore).sum();
        int aceCount = (int) cardValues.stream().filter(Card::isAce).count();
        sum += aceCount * 10;
        for (int i = 0; i < aceCount; i++) {
            if (sum > 21) {
                sum -= 10;
                continue;
            }
            break;
        }
        return sum;
    }

    @Override
    public boolean isAvailableToTake() {
        return sumCards() <= 21;
    }

    @Override
    public int result(int i) {
        return 0;
    }
}

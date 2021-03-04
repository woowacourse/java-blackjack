package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.List;
import java.util.regex.Pattern;

public class Player implements Playable {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z|A-Z]+");
    private final String name;
    private final Cards cards;

    public Player(String name, CardDeck cardDeck) {
        this(name, cardDeck.initCards());
    }

    public Player(String name, List<Card> cards) {
        name = name.trim();
        validateWord(name);
        this.name = name;
        this.cards = new Cards(cards);
    }

    private static void validateWord(String number) {
        if (!NAME_PATTERN.matcher(number).matches()) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public List<Card> getUnmodifiableCards() {
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
    public int result(int counterpart) {
        int playerSum = sumCardsForResult();

        if (counterpart > 21 && playerSum <= 21) {
            return 1;
        }
        if (playerSum <= 21 && counterpart < playerSum) {
            return 1;
        }

        if (counterpart <= 21 && playerSum > 21) {
            return -1;
        }
        if (counterpart <= 21 && counterpart > playerSum) {
            return -1;
        }

        return 0;
    }
}

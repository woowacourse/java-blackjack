package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardDeck {
    private final Stack<Card> deck;

    public CardDeck() {
        this.deck = new Stack<>();
        init();
        validate(deck);
        shuffle();
    }

    private void init() {
        for (Pattern pattern : Pattern.values()) {
            for (Rank rank : Rank.values()) {
                deck.push(new Card(rank, pattern));
            }
        }

        //위아래중 무엇이 나은지?
//                Arrays.stream(Pattern.values())
//                        .flatMap(pattern -> Arrays.stream(Rank.values())
//                        .map(rank -> new Card(rank, pattern)))
//                        .forEach(deck::push);
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Integer getDeckSize() {
        return deck.size();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 카드를 다 뽑았습니다.");
        }
        return deck.pop();
    }

    public List<Card> getCardDeck(){
        return Collections.unmodifiableList(deck);
    }

    private void validate(Stack<Card> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 리스트가 비어있습니다.");
        }
        if (hasDuplicates(items)) {
            throw new IllegalArgumentException("[ERROR] 중복된 항목이 존재합니다.");
        }
    }

    private boolean hasDuplicates(List<Card> items) {
        return items.stream().distinct().count() != items.size();
    }
}

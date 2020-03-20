package domain.card;

import sun.plugin.dom.exception.InvalidStateException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cards {
    private final List<Card> cards;
    private final static int MIN_SIZE = 1;
    static final String INVALID_SIZE_MESSAGE = String.format("카드 갯수가 최소 갯수인 %d보다 작습니다", MIN_SIZE);

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    Cards(Cards cards) {
        this.cards = cards.getCards();
    }

    static Cards of(List<Card> cards) {
        //todo refac
//        if (cards.size() < MIN_SIZE) {
//            throw new IllegalArgumentException(INVALID_SIZE_MESSAGE);
//        }
        return new Cards(cards);
    }

    static Cards of(Cards cards) {
        return of(cards.getCards());
    }

    private List<Card> getCards() {
        return cards;
    }

    public int size() {
        return cards.size();
    }

    protected Cards add(Card card) {
        cards.add(card);
        return of(cards);
    }

    protected Cards add(Cards cards) {
        this.cards.addAll(cards.getCards());
        return of(this.cards);
    }

    int calculateSumExceptAce() {
        Cards cards = getCardsExceptAce();
        return calculateSumExceptAce(cards);
    }

    private int calculateSumExceptAce(Cards cards) {
        if (cards.hasAce()) {
            throw new InvalidStateException(String.format("복수의 카드 내에 부적절한 %s가 존해합니다.", Symbol.ACE.getPattern()));
        }

        int sum = 0;
        for (Card card : cards.getCards()) {
            sum += card.calculate();
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    int calculateSumWithAces(int sum) {
        if (hasAce()) {
            Cards aces = getAces();
            return calculateSumWithAces(sum, aces);
        }
        return sum;
    }

    private int calculateSumWithAces(int sum, Cards aces) {
        if (aces.hasCardNotAce()) {
            throw new InvalidStateException("복수의 에이스 카드 내에 부적절한 카드가 존해합니다.");
        }
        for (Card card : aces.getCards()) {
            int score = card.calculate(sum);
            sum += score;
        }
        return sum;
    }

    private boolean hasCardNotAce() {
        return cards.stream().anyMatch(Card::isNotAce);
    }

    private Cards getCardsExceptAce() {
        List<Card> cards = this.cards.stream().filter(Card::isNotAce).collect(Collectors.toList());
        return Cards.of(cards);
    }

    //todo: 테스트 추가
    private Cards getAces() {
        List<Card> aces = this.cards.stream().filter(Card::isAce).collect(Collectors.toList());
        if (aces.isEmpty()) {
            throw new InvalidStateException(String.format("%s가 존재하지 않습니다.", Symbol.ACE.getPattern()));
        }
        return Cards.of(aces);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards1 = (Cards) o;
        return Objects.equals(cards, cards1.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    protected List<String> serialize() {
        return cards.stream().map(Card::toString).collect(Collectors.toList());
    }
}

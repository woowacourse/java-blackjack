package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        validateInitCards(cards);
        this.cards = new ArrayList<>();
        addCards(cards);
    }

    private void validateInitCards(final List<Card> cards) {
        if (cards == null || cards.size() != Deck.INIT_DISTRIBUTE_SIZE) {
            throw new IllegalArgumentException("[ERROR] 잘못 배분된 카드입니다.");
        }
    }

    public void addCards(final List<Card> cards) {
        cards.forEach(this::addCard);
    }

    public void addCard(final Card card) {
        checkCardNull(card);
        cards.add(card);
    }

    private void checkCardNull(final Card card) {
        if (card == null) {
            throw new IllegalArgumentException("[ERROR] 올바른 카드를 입력해주세요.");
        }
    }

    public int calculateScoreByAceOne() {
        return cards.stream().mapToInt(card -> card.getScore().getAmount()).sum();
    }

    public int calculateScoreByAceEleven() {
        if (isContainsAce()) {
            return calculateScoreByAceOne() + Score.getDifferenceAcesScore();
        }
        return calculateScoreByAceOne();
    }

    private boolean isContainsAce() {
        return cards.stream().anyMatch(card -> card.getScore() == Score.ACE);
    }

    public List<Card> getCards() {
        return List.copyOf(this.cards);
    }
}

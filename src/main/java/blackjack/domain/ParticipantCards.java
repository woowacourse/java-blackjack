package blackjack.domain;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ParticipantCards {

    private static final int INITIAL_SIZE = 2;

    private final Set<Card> cards;

    public ParticipantCards(final List<Card> cards) {
        if (cards.size() != INITIAL_SIZE) {
            throw new IllegalArgumentException("첫 카드는 두 장이어야 합니다.");
        }
        
        if (new HashSet<>(cards).size() != INITIAL_SIZE) {
            throw new IllegalArgumentException("카드는 중복될 수 없습니다.");
        }
        this.cards = new LinkedHashSet<>(cards);
    }

    public void receive(final Card card) {
        if(cards.contains(card)) {
            throw new IllegalArgumentException("중복되는 카드를 가질 수 없습니다.");
        }
        cards.add(card);
    }

    public int calculate() {
        List<CardNumber> cardNumbers = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.toList());
        return CardNumber.getMaxValueNear21(cardNumbers);
    }
}

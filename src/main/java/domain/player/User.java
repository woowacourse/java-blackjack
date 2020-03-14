package domain.player;

import domain.card.CardCalculator;
import domain.card.Card;
import domain.card.Cards;

import java.util.*;
import java.util.stream.Collectors;

public abstract class User {
    protected final List<Card> cards;

    public User(Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
        validateDuplicateCard();
    }

    public int sumCardNumber() {
        return CardCalculator.calculateDeterminedAce(this.cards);
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }
    
    protected void validateDuplicateCard(){
        Set<Card> cards = new HashSet<>(this.cards);
        if(cards.size() != this.cards.size()){
            throw new IllegalArgumentException("카드를 중복으로 받았습니다.");
        }
    }

    public abstract void insertCard(Cards cards);
}

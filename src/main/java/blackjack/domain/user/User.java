package blackjack.domain.user;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    public abstract String getName();

    public abstract boolean isGameOver();

    protected abstract List<Card> getCards();

    public abstract void addFirstCards(Card card1, Card card2);

    public abstract void addCard(Card card);

    public abstract int score();

    public List<String> getCardsStatus() {
        return getCards().stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.toList());
    }
}

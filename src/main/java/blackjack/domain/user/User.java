package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;

import java.util.List;
import java.util.stream.Collectors;

public abstract class User {
    public abstract String getName();

    protected abstract List<Card> getCards();

    public abstract void addCard(Card card);

    public abstract int scoreToInt();

    public abstract Score score();

    public abstract boolean isFinished();

    public List<String> getCardsStatus() {
        return getCards().stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.toList());
    }
}

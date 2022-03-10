package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {

    private final Name name;
    private final List<Card> cards;

    public Player(Name name, List<Card> cards) {
        cards = new ArrayList<>(cards);
        validateCards(cards);
        validateName(name);

        this.name = name;
        this.cards = cards;
    }

    private void validateName(Name name) {
        Objects.requireNonNull(name, "[ERROR] 이름은 null일 수 없습니다.");
    }

    private void validateCards(List<Card> cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null일 수 없습니다.");
        validateSize(cards);
        validateDistinct(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
        }
    }

    private void validateDistinct(List<Card> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("[ERROR] 카드는 중복될 수 없습니다.");
        }
    }

    public void hit(CardDeck deck) {
        cards.add(deck.draw());
    }

    public List<Card> getCards() {
        return cards;
    }

    public String getName() {
        return name.getValue();
    }
}

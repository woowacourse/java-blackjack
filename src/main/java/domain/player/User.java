package domain.player;

import domain.card.Card;
import domain.card.CardCalculator;

import java.util.*;
import java.util.stream.Collectors;

public abstract class User {
    private static final int START_CARD_DECK_SIZE = 2;

    protected String name;
    protected final List<Card> cards;

    public User(List<Card> userCardDeck) {
        if(userCardDeck == null || userCardDeck.size() < START_CARD_DECK_SIZE){
            throw new IllegalArgumentException("2장의 카드를 정상적으로 받지 않았습니다.");
        }
        this.cards = userCardDeck;
        validateDuplicateCard();
    }

    public String cardToString() {
        List<String> cardString = cards.stream().map(Card::toString).collect(Collectors.toList());

        return String.join(",", cardString);
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }

    public String getName() {
        return this.name;
    }

    protected void validateDuplicateCard() {
        Set<Card> cards = new HashSet<>(this.cards);
        if (cards.size() != this.cards.size()) {
            throw new IllegalArgumentException("카드를 중복으로 받았습니다.");
        }
    }

    protected void validatePlayerName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("플레이어 이름이 null 입니다.");
        }
    }

    public abstract void drawCard(Card cards);
}

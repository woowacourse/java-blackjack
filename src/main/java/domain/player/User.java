package domain.player;

import domain.money.Money;
import domain.card.Card;
import domain.card.CardCalculator;

import java.util.*;
import java.util.stream.Collectors;

public abstract class User {
    private static final int WINNING_COUNT = 21;
    private static final int BLACK_JACK = 21;
    private static final int DEALER_STANDARD_ADDITIONAL_CARD = 16;
    private static final int START_CARD_DECK_SIZE = 2;

    protected String name;
    protected final List<Card> cards;
    protected Money money;

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

    public int sumCardDeck(){
        return CardCalculator.sumCardDeck(this.cards);
    }

    public boolean isUnderSixteen() {
        return CardCalculator.sumCardDeck(this.cards) <= DEALER_STANDARD_ADDITIONAL_CARD;
    }


    public boolean isBlackJack() {
        return this.cards.stream().anyMatch(Card::isAce)
                && this.cards.size() == 2
                && CardCalculator.sumCardDeck(this.cards) == BLACK_JACK;
    }

    public boolean isUnderWinningCount() {
        int sum = CardCalculator.sumCardDeck(this.cards);

        return sum < WINNING_COUNT;
    }

    public boolean isMoreThanWinningCount() {
        int sum = CardCalculator.sumCardDeck(this.cards);

        return sum >= WINNING_COUNT;
    }

    public abstract void drawCard(Card cards);

    public double getMoney() {
        return money.getMoney();
    }
}

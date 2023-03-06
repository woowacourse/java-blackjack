package domain.player;

import domain.card.Cards;
import domain.card.Card;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Player {

    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    protected final Cards cards;

    Player(final String name, final Cards cards) {
        validateBlank(name);
        validateLength(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }

    abstract public List<String> revealCards();

    abstract public boolean isInPlaying(boolean isHit);

    public boolean isBust() {
        return HandsState.from(cards.calculateScore()) == HandsState.BUST;
    }

    public boolean isBlackjack() {
        return HandsState.from(cards.calculateScore()) == HandsState.BLACKJACK;
    }

    public List<Card> showCards() {
        return List.copyOf(cards.getCards());
    }

    public List<String> showCardNames() {
        return showCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public String getName() {
        return name;
    }
}

package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardPicker;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class Gamer {
    public static final int DEAL_CARD_COUNT = 2;
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private static final int MAX_SCORE = 21;
    private static final int HIT_CARD_COUNT = 1;

    protected final Deck deck;
    private final String name;

    protected Gamer(String name) {
        validate(name);
        this.name = name;
        this.deck = new Deck();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(DEAL_CARD_COUNT)
                .forEach(deck::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(HIT_CARD_COUNT)
                .forEach(deck::addCard);
    }

    public boolean isBust() {
        return deck.totalScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return isMaxScore() && deck.get().size() == DEAL_CARD_COUNT;
    }

    public boolean isMaxScore() {
        return deck.totalScore() == MAX_SCORE;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return deck.get();
    }

    public int getScore() {
        return deck.totalScore();
    }
}

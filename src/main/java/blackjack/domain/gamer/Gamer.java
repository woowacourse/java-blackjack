package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.List;

public abstract class Gamer {
    public static final int DEAL_CARD_COUNT = 2;
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private static final int MAX_SCORE = 21;
    private static final int HIT_CARD_COUNT = 1;

    protected final Cards cards;
    private final String name;

    protected Gamer(String name) {
        validate(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }

    public void deal(Deck deck) {
        deck.pick(DEAL_CARD_COUNT)
                .forEach(cards::addCard);
    }

    public void hit(Deck deck) {
        deck.pick(HIT_CARD_COUNT)
                .forEach(cards::addCard);
    }

    public boolean isBurst() {
        return cards.totalScore() > MAX_SCORE;
    }

    public boolean isBlackjack() {
        return isMaxScore() && cards.get().size() == DEAL_CARD_COUNT;
    }

    public boolean isMaxScore() {
        return cards.totalScore() == MAX_SCORE;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.get();
    }

    public int getScore() {
        return cards.totalScore();
    }
}

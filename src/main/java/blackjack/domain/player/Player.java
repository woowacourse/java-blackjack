package blackjack.domain.player;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.view.ResultView;
import java.util.List;

public abstract class Player {

    private static final String FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE = "처음 제공받는 카드는 2장이어야 합니다.";

    private static final int FIRST_RECEIVED_CARD_SIZE = 2;

    private final String name;
    protected final Cards cards;

    public Player(String name, List<Card> cards) {
        checkFirstReceivedCardsSize(cards.size());
        this.name = name;
        this.cards = new Cards(cards);
    }

    private void checkFirstReceivedCardsSize(int size) {
        if (size != FIRST_RECEIVED_CARD_SIZE) {
            throw new IllegalArgumentException(FIRST_RECEIVED_CARD_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public abstract boolean isPossibleToPickCard();

    public Boolean isBlackJack() {
        return cards.getCards().size() == FIRST_RECEIVED_CARD_SIZE && cards.calculateScore() == Result.BLACK_JACK_SCORE;
    }

    public void pickCard(Card card) {
        cards.addCard(card);
    }

    public Result findResult(Player otherPlayer) {
        return Result.findResult(this.cards.calculateScore(), otherPlayer.cards.calculateScore());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public int getTotalScore() {
        return cards.calculateScore();
    }
}

package domain;

import static domain.GameResult.DRAW;
import static domain.GameResult.LOSE;
import static domain.GameResult.WIN;

import java.util.List;

public class Dealer extends Gamer {
    private static final int BUST_THRESHOLD = 16;
    private final Deck deck;

    public Dealer(Deck deck) {
        super();
        this.deck = deck;
    }

    public GameResult decideGameResult(Player player) {
        int dealerScore = this.calculateScore();
        int playerScore = player.calculateScore();

        if (dealerScore == playerScore) {
            return DRAW;
        }

        if (dealerScore > playerScore || playerScore > 21) {
            return WIN;
        }
        return LOSE;
    }

    public boolean canGetMoreCard() {
        return this.canGetMoreCard(BUST_THRESHOLD);
    }

    public Card drawCard() {
        return deck.draw();
    }

    public Card showAnyOneCard() {
        List<Card> dealerCard = getCards();
        if (dealerCard.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }
        return dealerCard.getFirst();
    }
}

package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Player {

    private static final int PLAYING_STANDARD = 21;

    private final Name name;
    private final Cards cards;

    public Player(String name, List<Card> cards) {
        this.name = new Name(name);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isPlaying() {
        return cards.calculateTotalScore() <= PLAYING_STANDARD;
    }

    public void combine(Card card) {
        cards.combine(card);
    }

    public GameResult decideResult(int dealerScore) {
        if (getTotalScore() > PLAYING_STANDARD) {
            return GameResult.LOSE;
        }

        if (dealerScore > PLAYING_STANDARD) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore() - dealerScore);
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getValue();
    }
}

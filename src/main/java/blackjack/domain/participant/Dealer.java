package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchStatus;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;

    private Dealer(final Deck deck) {
        super(DEALER_NAME, deck);
    }

    public static Dealer readyToPlay(final Deck deck) {
        return new Dealer(deck);
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return cards.calculateScore() < DEALER_MIN_SCORE;
    }

    public MatchStatus judgeWinner(final Player player) {
        if (player.isBurst()) {
            return MatchStatus.LOSS;
        }
        if (this.isBurst()) {
            return MatchStatus.WIN;
        }
        return MatchStatus.from(this.isLowerThan(player));
    }

    private boolean isLowerThan(final Player player) {
        return this.getScore() < player.getScore();
    }

    public String getFirstCardName() {
        return cards.getFirstCardName();
    }

}

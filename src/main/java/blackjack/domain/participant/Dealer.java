package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.MatchStatus;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int DEALER_MIN_SCORE = 17;

    private Dealer() {
        this.name = DEALER_NAME;
    }

    public static Dealer readyToPlay(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawTwoCard(deck);
        return dealer;
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return calculateScore() < DEALER_MIN_SCORE;
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

    public boolean isLowerThan(final Player player) {
        return this.calculateScore() < player.calculateScore();
    }

    public String getFirstCardName() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("딜러의 카드가 존재하지 않습니다.");
        }
        final Card firstCard = cards.get(0);
        return firstCard.getCardName();
    }

}

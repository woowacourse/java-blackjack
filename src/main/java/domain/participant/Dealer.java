package domain.participant;

import domain.blackjack.BlackjackScore;
import domain.blackjack.Result;
import domain.card.Card;
import domain.card.Cards;

public class Dealer extends Participant {
    private static final int INITIAL_CARD_INDEX = 0;
    private static final BlackjackScore MORE_CARD_LIMIT_SCORE = BlackjackScore.from(16);

    public Dealer() {
        super(ParticipantName.getDealerName());
    }

    public Result competeWithPlayer(Player player) {
        Result result = gameState.competeToOtherState(player.gameState);
        if (result == Result.DRAW) {
            result = competeByScore(player);
        }

        return result;
    }

    private Result competeByScore(Player player) {
        if (player.isBusted()) {
            return Result.WIN;
        }

        BlackjackScore blackjackScore = this.calculateBlackjackScore();
        BlackjackScore otherBlackjackScore = player.calculateBlackjackScore();
        return blackjackScore.compete(otherBlackjackScore);
    }

    public boolean isAbleToReceiveCard() {
        return !calculateBlackjackScore().isGreaterThan(MORE_CARD_LIMIT_SCORE);
    }

    @Override
    public Cards getInitialOpeningCards() {
        Cards cards = gameState.getCards();
        Card initialOpeningCard = cards.getCards().get(INITIAL_CARD_INDEX);
        return Cards.of(initialOpeningCard);
    }
}

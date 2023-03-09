package domain;

public class Dealer extends Participant {
    private static final int INITIAL_CARD_INDEX = 0;
    private static final BlackjackScore MORE_CARD_LIMIT_SCORE = BlackjackScore.from(16);

    public Dealer() {
        super(ParticipantName.getDealerName());
    }

    public Result competeWithPlayer(Player player) {
        if (player.isBusted()) {
            return Result.WIN;
        }

        if (this.isBusted()) {
            return Result.LOSE;
        }

        BlackjackScore blackjackScore = BlackjackScore.from(cards);
        BlackjackScore otherBlackjackScore = BlackjackScore.from(player.cards);
        return blackjackScore.compete(otherBlackjackScore);
    }

    public boolean isAbleToReceiveCard() {
        return !calculateBlackjackScore().isGreaterThan(MORE_CARD_LIMIT_SCORE);
    }

    @Override
    public Cards getInitialOpeningCards() {
        Card initialOpeningCard = cards.getCards().get(INITIAL_CARD_INDEX);
        return Cards.of(initialOpeningCard);
    }
}

package domain.participant;

import domain.card.Card;
import domain.game.GameResult;
import java.math.BigDecimal;

public final class Player {

    private static final int STANDARD_GIVEN_SCORE = 21;

    private final Participant participant;
    private final PlayerBet playerBet;

    private Player(final Participant participant, final int betAmount) {
        this.participant = participant;
        this.playerBet = PlayerBet.create(betAmount);
    }

    public static Player create(final String name, final int betAmount) {
        final Participant participant = Participant.create(name);

        return new Player(participant, betAmount);
    }

    public void addCard(final Card drawCard) {
        participant.addCard(drawCard);
    }

    public int calculateScore() {
        final ParticipantScore participantScore = participant.calculateScore();

        return participantScore.score();
    }

    public BigDecimal calculateProfit(final GameResult gameResult) {
        final double prizeRatio = gameResult.prizeRatio();

        return playerBet.calculateBenefit(prizeRatio);
    }

    public boolean matchByName(final String name) {
        return participant.getName().equals(name);
    }

    public boolean canDraw() {
        return participant.canDraw(STANDARD_GIVEN_SCORE);
    }

    public ParticipantCard participantCard() {
        return participant.participantCard();
    }

    public String getName() {
        return participant.getName();
    }
}

package domain.player;

import domain.card.CardHolder;
import domain.gameresult.GameResult;

public class Dealer extends Player {

    public Dealer(CardHolder cardHolder) {
        super(cardHolder, Name.of("딜러"));
    }

    public void battle(Player participant, GameResult gameResult) {
        if (isBust() || participant.isBust()) {
            decideGameResultOnBurst(participant, gameResult);
            return;
        }
        decideGameResultOnScore(participant, gameResult);
    }

    private void decideGameResultOnBurst(Player participant, GameResult gameResult) {
        if (isBust() && participant.isBust()) {
            gameResult.addDrawCount(participant);
            return;
        } // 둘 다 버스트

        if (isBust()) {
            gameResult.addParticipantWinningCase(participant);
            return;
        } // 딜러가 버스트

        gameResult.addParticipantLosingCase(participant);
        // 참가자가 버스트
    }

    private void decideGameResultOnScore(Player participant, GameResult gameResult) {
        int dealerScore = getTotalScore();
        int participantScore = participant.getTotalScore();

        if (dealerScore > participantScore) {
            gameResult.addParticipantLosingCase(participant);
            return;
        }

        if (dealerScore < participantScore) {
            gameResult.addParticipantWinningCase(participant);
            return;
        }

        gameResult.addDrawCount(participant);
    }

    @Override
    public boolean isNameEqualTo(String name) {
        return getName().equals(name);
    }
}

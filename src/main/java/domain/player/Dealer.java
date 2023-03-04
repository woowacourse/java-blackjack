package domain.player;

import domain.gameresult.GameResult;

public class Dealer extends Player {

    public Dealer() {
        super("딜러");
    }

    public void battle2(Player participant, GameResult gameResult) {
        if (isBurst() || participant.isBurst()) {
            decideGameResultOnBurst2(participant, gameResult);
            return;
        }
        decideGameResultOnScore2(participant, gameResult);
    }

    private void decideGameResultOnBurst2(Player participant, GameResult gameResult) {
        if (isBurst() && participant.isBurst()) {
            gameResult.addDrawCount(participant);
            return;
        } // 둘 다 버스트

        if (isBurst()) {
            gameResult.addParticipantWinningCase(participant);
            return;
        } // 딜러가 버스트

        gameResult.addParticipantLosingCase(participant);
        // 참가자가 버스트
    }

    private void decideGameResultOnScore2(Player participant, GameResult gameResult) {
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

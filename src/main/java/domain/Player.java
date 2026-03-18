package domain;

import domain.state.ParticipantState;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean canHit() {
        return !state.isFinished();
    }

    public WinningStatus calculateResult(ParticipantState dealerState) {
        return state.calculateWinningStatus(dealerState);
    }
}
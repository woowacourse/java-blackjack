package model.participant.role;

import model.betting.Bet;

public interface Bettable extends BetOwnable {

    Bet makeBet(int betAmount);
}

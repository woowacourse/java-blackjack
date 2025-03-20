package model.participant.role;

import model.betting.Bet;

public interface GameProcessable {

    void receiveBet(Bet bet);

    void updateBetOwnerFrom(BetOwnable beforeOwner);

    void updateBetAmountWhenBlackJackOf(Bettable better);

    Bet findBetByBetter(Bettable better);
}

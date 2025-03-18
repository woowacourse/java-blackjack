package model.participant.role;

import model.betting.Bet;
import model.deck.Deck;

public interface GameProcessable {
    void splitInitialDeck(Deck deck, Gameable gamer);

    void receiveBet(Bet bet);

    void updateBetOwnerFrom(BetOwnable beforeOwner);

    void updateBetAmountWhenBlackJackOf(Bettable better);

    Bet findBetByBetter(Bettable better);
}

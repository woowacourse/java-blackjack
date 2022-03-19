package blackjack_statepattern;

import blackjack_statepattern.participant.Dealer;
import blackjack_statepattern.participant.Players;

public final class BlackjackBoard {

    private final Players players;
    private final Dealer dealer;

    public BlackjackBoard(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

}

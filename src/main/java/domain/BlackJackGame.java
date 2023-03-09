package domain;

import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Players;

public class BlackJackGame {
    private final Players players;
    private final Dealer dealer;

    public BlackJackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void initSettingCards() {
        dealer.takeCard(2);
        players.initDistribute();
    }

    public void distributeCard(Participant participant, int num) {
        participant.takeCard(num);
    }
}

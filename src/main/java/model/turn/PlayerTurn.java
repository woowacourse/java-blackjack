package model.turn;

import java.util.Map;
import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;

public class PlayerTurn extends Turn {
    private final Betting betting;

    public PlayerTurn(Player player, Betting betting) {
        super(player);
        this.betting = betting;
    }

    public void betInsurance(int insuranceBet) {
        betting.takeInsurance(insuranceBet);
    }

    public void putBetting(Map<Player, Betting> playerBetting) {
        Player player = (Player) participant;
        playerBetting.put(player, betting);
    }

    public void processHit(Deck deck) {
        Player player = (Player) participant;
        player.receiveCard(deck.pick());
    }

    public void processDoubleDown(Deck deck, int additionalBet) {
        Player player = (Player) participant;
        player.receiveCard(deck.pick());
        betting.addBet(additionalBet);
    }

    public void processSurrender() {
        betting.surrender();
    }

    public int getMaxInsuranceAmount() {
        return betting.calculateMaxInsuranceAmount();
    }

    public Player getPlayer() {
        return (Player) participant;
    }
}

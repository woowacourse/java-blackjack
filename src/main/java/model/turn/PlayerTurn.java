package model.turn;

import java.util.Map;
import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;

public class PlayerTurn implements Turn {
    private final Player player;
    private final Betting betting;

    public PlayerTurn(Player player, Betting betting) {
        this.player = player;
        this.betting = betting;
    }

    @Override
    public void dealInitialCards(Deck deck) {
        dealCards(player, deck);
    }

    public void betInsurance(int insuranceBet) {
        betting.updateInsuranceBet(insuranceBet);
    }

    public void putBetting(Map<Player, Betting> playerBetting) {
        playerBetting.put(player, betting);
    }

    public void processHit(Deck deck) {
        player.receiveCard(deck.pick());
    }

    public void processDoubleDown(Deck deck, int additionalBet) {
        player.receiveCard(deck.pick());
        betting.addBet(additionalBet);
    }

    public void processSurrender() {
        betting.updateSurrender();
    }

    public int getMaxInsuranceAmount() {
        return betting.calculateMaxInsuranceAmount();
    }

    public Player getPlayer() {
        return player;
    }
}

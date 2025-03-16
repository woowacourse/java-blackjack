package model.turn;

import controller.GameManager;
import java.util.Map;
import model.betting.Betting;
import model.card.Deck;
import model.participant.Player;
import view.InputView;

public class PlayerTurn extends Turn {
    private final Betting betting;

    public PlayerTurn(Player player, Betting betting) {
        super(player);
        this.betting = betting;
    }

    public void selectAtOnePlayerChoice(Deck deck) {
        Player player = (Player) participant;
        GameManager.playPlayerTurn(player, deck, betting);
    }

    public void betInsurance() {
        int maxInsuranceAmount = betting.calculateMaxInsuranceAmount();
        int insuranceBet = InputView.readInsuranceBet(maxInsuranceAmount);
        betting.takeInsurance(insuranceBet);
    }

    public void putBetting(Map<Player, Betting> playerBetting) {
        Player player = (Player) participant;
        playerBetting.put(player, betting);
    }

    public Player getPlayer() {
        return (Player) participant;
    }
}

package model.turn;

import java.util.Map;
import model.betting.Betting;
import model.PlayerChoice;
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
        PlayerChoice playerChoice = InputView.readFirstChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)) {
            processHit(deck);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)) {
            int additionalBet = InputView.inputAdditionalBet();
            processDoubleDown(deck, additionalBet);
        }
        if (playerChoice.equals(PlayerChoice.SURRENDER)) {
            betting.surrender();
        }
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

    private void processHit(Deck deck) {
        Player player = (Player) participant;
        player.receiveCard(deck.pick());
        if (!player.isBust()) {
            selectHitOrStand(deck);
        }
    }

    private void selectHitOrStand(Deck deck) {
        Player player = (Player) participant;
        PlayerChoice playerChoice = InputView.readHitOrStand(player);
        if (playerChoice == PlayerChoice.HIT) {
            processHit(deck);
        }
    }

    private void processDoubleDown(Deck deck, int additionalBet) {
        Player player = (Player) participant;
        player.receiveCard(deck.pick());
        betting.addBet(additionalBet);
    }

    public Player getPlayer() {
        return (Player) participant;
    }
}

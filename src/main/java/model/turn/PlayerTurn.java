package model.turn;

import java.util.Map;
import model.Betting;
import model.PlayerChoice;
import model.card.Deck;
import model.participant.Player;
import view.InputView;

public class PlayerTurn extends Turn {
    private final Player player;
    private final Betting betting;
    private boolean isSurrender;

    public PlayerTurn(Player player, Betting betting) {
        super(player);
        this.player = player;
        this.betting = betting;
        this.isSurrender = false;
    }

    public void selectAtOnePlayerChoice(Deck deck) {
        PlayerChoice playerChoice = InputView.readFirstChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)) {
            processHit(deck);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)) {
            int additionalBet = InputView.inputAdditionalBet();
            processDoubleDown(deck, additionalBet);
        }
        if (playerChoice.equals(PlayerChoice.SURRENDER)) {
            isSurrender = true;
        }
    }

    public void betInsurance() {
        int maxInsuranceAmount = betting.calculateMaxInsuranceAmount();
        int insuranceBet = InputView.readInsuranceBet(maxInsuranceAmount);
        betting.takeInsurance(insuranceBet);
    }

    public void putBetting(Map<Player, Betting> playerBetting) {
        playerBetting.put(player, betting);
    }

    private void processHit(Deck deck) {
        player.receiveCard(deck.pick());
        if (!player.isBust()) {
            selectHitOrStand(deck);
        }
    }

    private void selectHitOrStand(Deck deck) {
        PlayerChoice playerChoice = InputView.readHitOrStand(player);
        if (playerChoice == PlayerChoice.HIT) {
            processHit(deck);
        }
    }

    private void processDoubleDown(Deck deck, int additionalBet) {
        player.receiveCard(deck.pick());
        betting.addBet(additionalBet);
    }

    public Player getPlayer() {
        return player;
    }
}

package controller;

import java.util.List;
import model.PlayerChoice;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.turn.PlayerTurn;
import view.InputView;

public class PlayerTurnController {
    private final List<PlayerTurn> playersTurn;

    public PlayerTurnController(List<PlayerTurn> playersTurn) {
        this.playersTurn = playersTurn;
    }

    public void dealInitialCards(Deck deck) {
        dealInitialCardsToAllPlayers(deck);
    }

    public void betInsurance(Dealer dealer) {
        if (dealer.isFirstCardAce()) {
            runPlayersBetInsurance();
        }
    }

    public void runParticipantsTurn(Deck deck) {
        runPlayersTurn(deck);
    }

    private void dealInitialCardsToAllPlayers(Deck deck){
        for (PlayerTurn playerTurn : playersTurn){
            playerTurn.dealInitialCards(deck);
        }
    }

    private void runPlayersBetInsurance() {
        for (PlayerTurn playerTurn : playersTurn) {
            processInsuranceBet(playerTurn);
        }
    }

    private void processInsuranceBet(PlayerTurn playerTurn) {
        try {
            int maxInsuranceAmount = playerTurn.getMaxInsuranceAmount();
            int insuranceBet = InputView.readInsuranceBet(maxInsuranceAmount);
            playerTurn.betInsurance(insuranceBet);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            processInsuranceBet(playerTurn);
        }
    }

    private void runPlayersTurn(Deck deck) {
        for (PlayerTurn playerTurn : playersTurn) {
            selectAtOnePlayerChoice(playerTurn, deck);
        }
    }

    private void selectAtOnePlayerChoice(PlayerTurn playerTurn, Deck deck) {
        Player player = playerTurn.getPlayer();
        PlayerChoice playerChoice = InputView.readFirstChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)) {
            playerTurn.processHit(deck);
            selectHitOrStand(player, playerTurn, deck);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)) {
            int additionalBet = InputView.inputAdditionalBet();
            playerTurn.processDoubleDown(deck, additionalBet);
        }
        if (playerChoice.equals(PlayerChoice.SURRENDER)) {
            playerTurn.processSurrender();
        }
    }

    private void selectHitOrStand(Player player, PlayerTurn playerTurn, Deck deck) {
        if (player.getParticipantHand().checkBust()) {
            return;
        }
        PlayerChoice playerChoice = InputView.readHitOrStand(player);
        if (playerChoice == PlayerChoice.HIT) {
            playerTurn.processHit(deck);
            selectHitOrStand(player, playerTurn, deck);
        }
    }
}

package controller;

import model.PlayerChoice;
import model.card.Deck;
import model.participant.Dealer;
import model.participant.Player;
import model.turn.DealerTurn;
import model.turn.PlayerTurn;
import model.turn.PlayersTurn;
import view.InputView;
import view.OutputView;

public class TurnController {
    private final PlayersTurn playersTurn;
    private final DealerTurn dealerTurn;
    private final Deck deck = Deck.of();

    public TurnController(PlayersTurn playersTurn, DealerTurn dealerTurn) {
        this.playersTurn = playersTurn;
        this.dealerTurn = dealerTurn;
    }

    public void dealInitialCards() {
        playersTurn.dealInitialCardsToAllPlayers(deck);
        dealerTurn.dealInitialCards(deck);
    }

    public void betInsurance() {
        Dealer dealer = dealerTurn.getDealer();
        if (dealer.isFirstCardAce()) {
            runPlayersBetInsurance();
        }
    }

    public void runParticipantsTurn() {
        runPlayersTurn();
        runDealerTurn();
    }

    private void runPlayersBetInsurance() {
        for (PlayerTurn playerTurn : playersTurn.getPlayersGameRound()) {
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

    private void runPlayersTurn() {
        for (PlayerTurn playerTurn : playersTurn.getPlayersGameRound()) {
            selectAtOnePlayerChoice(playerTurn);
        }
    }

    private void selectAtOnePlayerChoice(PlayerTurn playerTurn) {
        Player player = playerTurn.getPlayer();
        PlayerChoice playerChoice = InputView.readFirstChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)) {
            playerTurn.processHit(deck);
            selectHitOrStand(player, playerTurn);
        }
        if (playerChoice.equals(PlayerChoice.DOUBLE_DOWN)) {
            int additionalBet = InputView.inputAdditionalBet();
            playerTurn.processDoubleDown(deck, additionalBet);
        }
        if (playerChoice.equals(PlayerChoice.SURRENDER)) {
            playerTurn.processSurrender();
            ;
        }
    }

    private void selectHitOrStand(Player player, PlayerTurn playerTurn) {
        if (player.getParticipantHand().checkBust()) {
            return;
        }
        PlayerChoice playerChoice = InputView.readHitOrStand(player);
        if (playerChoice == PlayerChoice.HIT) {
            playerTurn.processHit(deck);
            selectHitOrStand(player, playerTurn);
        }
    }

    private void runDealerTurn() {
        Dealer dealer = dealerTurn.getDealer();
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }
}

package model;

import model.card.Deck;
import model.participant.Dealer;
import model.participant.Participant;
import model.participant.Player;
import model.participant.Players;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
    private static final int INITIAL_DEAL_CARD_COUNT = 2;

    private final Players players;
    private final Dealer dealer;
    private final Deck deck = Deck.of();

    public BlackjackGame(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void dealInitially() {
        dealInitialCardsToAllPlayers();
        dealInitialCardsToParticipant(dealer);
        OutputView.printInitialDealResult(dealer, players);
    }

    public void runGameTurn() {
        runPlayerTurn();
        runDealerTurn();
    }

    private void dealInitialCardsToAllPlayers() {
        for (Player player : players.getPlayers()) {
            dealInitialCardsToParticipant(player);
        }
    }

    private void dealInitialCardsToParticipant(Participant participant) {
        for (int i = 0; i < INITIAL_DEAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.pick());
        }
    }

    private void runPlayerTurn() {
        for (Player player : players.getPlayers()) {
            chooseAtOnePlayerTurn(player);
        }
    }

    private void runDealerTurn() {
        while (dealer.checkScoreUnderSixteen()) {
            OutputView.printDealerDealResult();
            dealer.receiveCard(deck.pick());
        }
    }

    private void chooseAtOnePlayerTurn(Player player) {
        OutputView.printHitResult(player);
        PlayerChoice playerChoice = InputView.readChoice(player);
        if (playerChoice.equals(PlayerChoice.HIT)){
            processHit(player);
        }
    }

    private void processHit(Player player){
        player.receiveCard(deck.pick());
        if (!player.isBust()){
            chooseAtOnePlayerTurn(player);
        }
    }
}

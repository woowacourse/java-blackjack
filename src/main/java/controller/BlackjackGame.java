package controller;

import domain.deck.Deck;
import domain.game.GameResult;
import domain.participant.*;

import java.util.ArrayList;

import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGameSetUp blackjackGameSetUp = new BlackjackGameSetUp(inputView);
        Players players = blackjackGameSetUp.setUpPlayer();
        Dealer dealer = blackjackGameSetUp.setUpDealer();
        Deck deck = blackjackGameSetUp.setUpDeck();

        playGame(players, dealer, deck);
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        initParticipantsHand(players, dealer, deck);

        for (Player player : players.getPlayers()) {
            playerTurn(player, deck);
        }

        dealerTurn(dealer, deck);

        showGameResult(players, dealer);
    }

    private void initParticipantsHand(Players players, Dealer dealer, Deck deck) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(participant -> participant.receiveInitialCards(deck.drawInitialCards()));

        outputView.printInitialDistribution(players, dealer);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.receive(deck.draw());
            outputView.printDealerReceiveMessage();
        }
    }

    private void playerTurn(Player player, Deck deck) {
        while (shouldPlayerDraw(player)) {
            player.receive(deck.draw());
            outputView.printParticipantCards(player);
        }
    }

    private boolean shouldPlayerDraw(Player player) {
        if (!player.canDraw()) {
            return false;
        }

        String choice = inputView.getChoice(player.name());

        if (!choice.equals("y") && !choice.equals("n")) {
            throw new IllegalArgumentException();
        }

        return choice.equals("y");
    }

    private void showGameResult(Players players, Dealer dealer) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(outputView::printFinalResult);

        GameResult gameResult = new GameResult(players, dealer);
        outputView.printGameResult(gameResult, players, dealer);
    }
}

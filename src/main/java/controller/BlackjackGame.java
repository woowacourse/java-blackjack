package controller;

import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.game.GameResult;
import domain.participant.*;

import java.util.ArrayList;

import util.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;

import static domain.BlackjackRule.DEALER_NAME;

public class BlackjackGame {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String names = inputView.getNames();
        List<String> parsedNames = InputParser.parseName(names);

        Players players = new Players(parsedNames
                .stream()
                .map(Player::new).toList()
        );


        players.getPlayers().forEach(player -> player.bet(inputView.getBetAmount(player.name())));

        Dealer dealer = new Dealer(DEALER_NAME);

        CardShuffleStrategy cardShuffleStrategy = new RandomShuffleStrategy();
        Deck deck = Deck.createDeck(cardShuffleStrategy);

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

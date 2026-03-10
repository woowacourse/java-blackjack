package controller;

import domain.card.Card;
import domain.deck.CardShuffleStrategy;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.game.GameResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;

import java.util.ArrayList;

import util.InputParser;
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
        String names = inputView.getNames();
        List<String> parsedName = InputParser.parseName(names);

        Players players = new Players(parsedName);
        Dealer dealer = new Dealer();

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
        while (player.canDraw()) {
            if (inputView.getChoice(player.name()).equals("n")) {
                break;
            }

            player.receive(deck.draw());
            outputView.printParticipantCards(player);
        }
    }

    private void showGameResult(Players players, Dealer dealer) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(dealer);
        participants.forEach(outputView::printFinalResult);

        GameResult gameResult = new GameResult(players, dealer);
        outputView.printGameResult(gameResult, dealer);
    }
}

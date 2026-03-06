package controller;

import domain.*;
import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import util.InputParser;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackjackGame {
    InputView inputView = new InputView();
    InputParser inputParser = new InputParser();
    OutputView outputView = new OutputView();

    public void run() {
        String names = inputView.getNames();
        List<String> parsedName = inputParser.parseName(names);

        Players players = new Players(parsedName);
        Dealer dealer = new Dealer();

        List<Card> cards = Deck.prepareCards();
        Deck deck = new Deck(cards);

        playGame(players, dealer, deck);
    }

    private void playGame(Players players, Dealer dealer, Deck deck) {
        initGame(players, dealer, deck);

        for (Player player : players.getPlayers()) {
            playerTurn(player, deck);
        }

        dealerTurn(dealer, deck);

        showGameResult(players, dealer);
    }

    private void initGame(Players players, Dealer dealer, Deck deck) {
        for (Player player : players.getPlayers()) {
            Card card1 = deck.draw();
            Card card2 = deck.draw();

            player.receiveInitialCards(List.of(card1, card2));
        }

        Card card1 = deck.draw();
        Card card2 = deck.draw();
        dealer.receiveInitialCards(List.of(card1, card2));

        outputView.printInitialDistribution(players, dealer);
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        if (dealer.canDraw()) {
            dealer.receive(deck.draw());
            outputView.printDealerReceiveMessage();
        }
    }

    private void playerTurn(Player player, Deck deck) {
        while (player.canDraw()) {
            if (inputView.getChoice(player.name()).equals("y")) {
                player.receive(deck.draw());
                outputView.printParticipantCards(player);
            }
            break;
        }
    }

    private void showGameResult(Players players, Dealer dealer) {
        outputView.printFinalResult(dealer);

        for (Player player : players.getPlayers()) {
            outputView.printFinalResult(player);
        }

        GameResult gameResult = new GameResult(players, dealer);
        outputView.printWinOrLoseMessage();
        outputView.printWinOrLose(gameResult.getDealerStatistics());
        outputView.printWinOrLose(gameResult.getPlayersStatistics());
    }

}

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
    private final InputView inputView;
    private final InputParser inputParser;
    private final OutputView outputView;

    public BlackjackGame(InputView inputView, InputParser inputParser, OutputView outputView) {
        this.inputView = inputView;
        this.inputParser = inputParser;
        this.outputView = outputView;
    }

    public void run() {
        String names = inputView.getNames();
        List<String> parsedName = inputParser.parseName(names);

        Players players = new Players(parsedName);
        Dealer dealer = new Dealer();

        List<Card> cards = Deck.createDeck();
        Deck deck = new Deck(cards);

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
            if (inputView.getChoice(player.name()).equals("n")) {
                break;
            }

            player.receive(deck.draw());
            outputView.printParticipantCards(player);
        }
    }

    private void showGameResult(Players players, Dealer dealer) {
        outputView.printFinalResult(dealer);

        for (Player player : players.getPlayers()) {
            outputView.printFinalResult(player);
        }

        GameResult gameResult = new GameResult(players, dealer);
        outputView.printWinOrLoseMessage();
        outputView.printDealerResult(gameResult.getDealerResult());
        outputView.printPlayersResult(gameResult.getPlayersResult());
    }
}

package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.WinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        Participants participants = createParticipants();

        handOutAndPrintInitialCards(participants, deck);
        handOutMoreCards(participants, deck);

        printResult(participants);
    }

    private Participants createParticipants() {
        List<String> playerNames = InputView.inputPlayerName();
        return new Participants(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    private void handOutAndPrintInitialCards(Participants participants, Deck deck) {
        participants.handOutInitialCards(deck);
        OutputView.printInitialCardInformation(participants);
    }

    private void handOutMoreCards(Participants participants, Deck deck) {
        handOutMoreCardsToPlayers(participants.getPlayers(), deck);
        handOutMoreCardsToDealer(participants.getDealer(), deck);
    }

    private void handOutMoreCardsToPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            boolean cardPrintFlag = false;
            String answer = InputView.inputPlayerHit(player.getName());

            while (answer.equals("y")) {
                player.receiveCard(deck.pickCard());
                cardPrintFlag = true;
                OutputView.printPlayerCardInformation(player);
                answer = InputView.inputPlayerHit(player.getName());

            }

            if (!cardPrintFlag) {
                OutputView.printPlayerCardInformation(player);
            }
        }
    }

    private void handOutMoreCardsToDealer(Dealer dealer, Deck deck) {
        while (dealer.checkHitRule()) {
            OutputView.printDealerHitMessage();
            dealer.receiveCard(deck.pickCard());
        }
    }

    private void printResult(Participants participants) {

        OutputView.printCardsAndPoint(participants);

        BlackjackGame blackjackGame = new BlackjackGame(participants);
        blackjackGame.calculatePlayerResult();

        Map<WinningResult, Integer> dealerResult =  blackjackGame.getDealerResult();
        Map<Player, WinningResult> playerResult = blackjackGame.getPlayerResult();

        OutputView.printResult(dealerResult,playerResult);
    }


}


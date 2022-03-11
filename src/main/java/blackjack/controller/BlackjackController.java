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
            handOutMoreCardsToPlayer(player, deck);
        }
    }

    private void handOutMoreCardsToPlayer(Player player, Deck deck) {
        boolean cardPrintFlag = isPlayerWantMoreCards(player, deck);

        if (!cardPrintFlag) {
            OutputView.printPlayerCardInformation(player);
        }

    }

    private boolean isPlayerWantMoreCards(Player player, Deck deck) {
        boolean cardPrintFlag = false;
        while (isPlayerHit(player) && !isBust(player)) {
            player.receiveCard(deck.pickCard());
            OutputView.printPlayerCardInformation(player);
            cardPrintFlag = true;
        }
        return cardPrintFlag;
    }

    private boolean isBust(Player player) {
        if (player.isMoreThanThreshold()) {
            OutputView.printPlayerHitImpossibleMessage();
            return true;
        }
        return false;
    }

    private boolean isPlayerHit(Player player) {
        return InputView.inputPlayerHit(player.getName()).equals("y");
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

        Map<WinningResult, Integer> dealerResult = blackjackGame.getDealerResult();
        Map<Player, WinningResult> playerResult = blackjackGame.getPlayerResult();

        OutputView.printResult(dealerResult, playerResult);
    }


}


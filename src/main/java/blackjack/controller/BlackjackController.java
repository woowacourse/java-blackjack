package blackjack.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.WinningResult;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackjackController {

    public void run() {
        Deck deck = new Deck();
        Participants participants = createParticipants();

        handOutAndPrintInitialCards(participants, deck);
        isHandOut(participants, deck);

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

    private void isHandOut(Participants participants, Deck deck) {
        handOutMoreCardsToPlayers(participants.getPlayers(), deck);
        handOutMoreCardsToDealer(participants.getDealer(), deck);
    }

    private void handOutMoreCardsToPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            handOutMoreCardsToPlayer(deck, player);
        }
    }

    private void handOutMoreCardsToPlayer(Deck deck, Player player) {
        boolean printCheck = false;
        while (player.canHit() && isHandOut(player, deck)) {
            OutputView.printPlayerCardInformation(player);
            printCheck = true;
        }
        if (!printCheck) {
            OutputView.printPlayerCardInformation(player);
        }
    }

    private boolean isHandOut(Player player, Deck deck) {
        boolean isHit = InputView.inputPlayerHit(player.getName());
        if (isHit) {
            player.addCard(deck.pickCard());
            return true;
        }
        return false;
    }

    private void handOutMoreCardsToDealer(Dealer dealer, Deck deck) {
        while (dealer.checkHitRule()) {
            OutputView.printDealerHitMessage();
            dealer.addCard(deck.pickCard());
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


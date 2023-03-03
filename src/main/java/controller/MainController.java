package controller;

import domain.PlayerCommand;
import domain.WinningStatus;
import domain.card.Deck;
import domain.card.shuffler.DeckShuffler;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.HashMap;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class MainController {

    private final InputView inputView;
    private final OutputView outputView;
    private final DeckShuffler deckShuffler;

    public MainController(final InputView inputView, final OutputView outputView, DeckShuffler deckShuffler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.deckShuffler = deckShuffler;
    }

    public void run() {
        Deck deck = new Deck(deckShuffler);
        Participants participants = new Participants(inputView.readPlayerNames(), deck);
        Dealer dealer = participants.getDealer();

        outputView.printInitialState(participants);
        receiveAdditionalCard(deck, participants, dealer);

        outputView.printFinalState(participants);
        printFinalResult(participants, dealer);
    }

    private void receiveAdditionalCard(Deck deck, Participants participants, Dealer dealer) {
        receiveAdditionalPlayerCard(deck, participants);
        receiveAdditionalDealerCard(deck, dealer);
    }

    private void printFinalResult(Participants participants, Dealer dealer) {
        Map<Player, WinningStatus> playersResult = new HashMap<>();
        Map<WinningStatus, Integer> dealerResult = new HashMap<>();
        calculateResult(participants, dealer, playersResult, dealerResult);
        outputView.printFinalResultMessage();
        outputView.printDealerResult(dealerResult);
        outputView.printPlayerResult(playersResult);
    }

    private void calculateResult(Participants participants, Dealer dealer, Map<Player, WinningStatus> playersResult,
                           Map<WinningStatus, Integer> dealerResult) {
        int dealerScore = dealer.calculateScore();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = decideWinningStatus(player, dealerScore);
            playersResult.put(player, playerWinningStatus);
            dealerResult.put(playerWinningStatus.reverse(),
                    dealerResult.getOrDefault(playerWinningStatus.reverse(), 0) + 1);
        }
    }

    private void receiveAdditionalDealerCard(Deck deck, Dealer dealer) {
        while (dealer.calculateScore() <= 16) {
            outputView.printFillDealerCards();
            dealer.receiveCard(deck.getCard());
        }
    }

    private void receiveAdditionalPlayerCard(Deck deck, Participants participants) {
        for (Player player : participants.getPlayers()) {
            repeatReceiveCard(deck, player);
        }
    }

    private void repeatReceiveCard(Deck deck, Player player) {
        boolean repeat = true;
        while (repeat) {
            PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
            player.receiveAdditionalCard(command, deck);
            repeat = player.calculateScore() < 21 && command.isHit();
            outputView.printSingleState(player);
        }
    }

    public WinningStatus decideWinningStatus(final Player player, final int dealerScore) {
        int score = player.calculateScore();
        if (dealerScore > 21) {
            return decideWinningStatusDealerBust(score);
        }
        return decideWinningStatusDealerNotBust(dealerScore, score);
    }

    private WinningStatus decideWinningStatusDealerNotBust(int dealerScore, int score) {
        if (score <= 21 && score > dealerScore) {
            return WinningStatus.WIN;
        }
        if (score == dealerScore) {
            return WinningStatus.TIE;
        }
        return WinningStatus.LOSE;
    }

    private WinningStatus decideWinningStatusDealerBust(int score) {
        if (score > 21) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }
}

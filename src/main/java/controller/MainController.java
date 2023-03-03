package controller;

import domain.PlayerCommand;
import domain.WinningStatus;
import domain.card.Cards;
import domain.card.shuffler.CardsShuffler;
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
    private final CardsShuffler cardsShuffler;

    public MainController(final InputView inputView, final OutputView outputView, CardsShuffler cardsShuffler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.cardsShuffler = cardsShuffler;
    }

    public void run() {

        Cards cards = new Cards(cardsShuffler);
        Participants participants = new Participants(inputView.readPlayerNames(), cards);
        outputView.printInitialMessage(participants.getPlayerNames());
        outputView.printAllState(participants);

        for (Player player : participants.getPlayers()) {
            boolean repeat = true;
            while (repeat) {
                PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
                player.receiveAdditionalCard(command, cards);
                repeat = player.calculateScore() < 21 && command.isHit();
                outputView.printSingleState(player);
            }
        }

        Dealer dealer = participants.getDealer();
        while (dealer.calculateScore() <= 16) {
            outputView.printFillDealerCards();
            dealer.receiveCard(cards.getCard());
        }

        outputView.printFinalState(participants);

        int dealerScore = dealer.calculateScore();
        Map<Player, WinningStatus> playersResult = new HashMap<>();
        Map<WinningStatus, Integer> dealerResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = decideWinningStatus(player, dealerScore);
            playersResult.put(player, playerWinningStatus);
            dealerResult.put(playerWinningStatus.reverse(),
                    dealerResult.getOrDefault(playerWinningStatus.reverse(), 0) + 1);
        }
        outputView.printFinalResult();
        outputView.printDealerResult(dealerResult);
        outputView.printPlayerResult(playersResult);
    }

    public WinningStatus decideWinningStatus(final Player player, final int dealerScore) {
        int score = player.calculateScore();
        if (dealerScore > 21) {
            if (score > 21) {
                return WinningStatus.TIE;
            }
            return WinningStatus.WIN;
        }
        if (score <= 21 && score > dealerScore) {
            return WinningStatus.WIN;
        }
        if (score == dealerScore) {
            return WinningStatus.TIE;
        }
        return WinningStatus.LOSE;
    }
}

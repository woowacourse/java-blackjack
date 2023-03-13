package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.participant.Amount;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantName;
import blackjack.domain.participant.Player;
import blackjack.util.RandomCardPickerGenerator;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackjackGame blackjackGame = initBlackjackGame();

        hitFirstSetting(blackjackGame);
        hitParticipantsCard(blackjackGame);

        BlackjackGameResult blackjackGameResult = blackjackGame.generatePlayersResult(new BlackJackReferee());
        printResult(blackjackGame, blackjackGameResult);
    }

    private BlackjackGame initBlackjackGame() {
        Deck deck = Deck.create(new RandomCardPickerGenerator());
        List<ParticipantName> playersName = inputPlayerName();
        List<Amount> playerAmount = creatPlayerAmount(playersName);
        return BlackjackGame.of(playersName, playerAmount, deck);
    }

    private void hitFirstSetting(final BlackjackGame blackjackGame) {
        for (Participant participant : blackjackGame.getParticipants()) {
            blackjackGame.getTwoHitCards(participant);
        }
        outputView.printParticipants(blackjackGame.getParticipantsName());
        outputView.printParticipantsCard(blackjackGame.findDealer(), blackjackGame.findPlayers());
    }

    private void hitParticipantsCard(final BlackjackGame blackjackGame) {
        List<Player> players = blackjackGame.findPlayers();
        for (Player player : players) {
            hitPlayerCard(player, blackjackGame);
        }
        blackjackGame.hitDealerCard();
        outputView.printHitDealerCount(blackjackGame.findDealer());
    }

    private void hitPlayerCard(final Player player, final BlackjackGame blackjackGame) {
        while (player.decideHit() && HitCommand.of(inputHitCommand(player)).isQuit()) {
            blackjackGame.hitPlayerCard(player);
            outputView.printCurrentCards(player);
        }
    }

    private void printResult(final BlackjackGame blackjackGame, final BlackjackGameResult blackjackGameResult) {
        for (Participant participant : blackjackGame.getParticipants()) {
            outputView.printTotalCardsAndScore(participant);
        }
        printProfits(blackjackGameResult);
    }

    private void printProfits(final BlackjackGameResult blackjackGameResult) {
        outputView.printFinalRevenueStatement();
        outputView.printDealerProceeds(blackjackGameResult.calculateDealerPrizeByGameResult());
        outputView.printPlayersProceeds(blackjackGameResult.calculatePlayersPrizeByGameResult());
    }

    private List<ParticipantName> inputPlayerName() {
        try {
            return creatPlayersName(inputView.readPlayerName());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return inputPlayerName();
    }

    private Amount inputPlayerAmount(final String name) {
        try {
            return new Amount(inputView.readPlayerAmount(name));
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return inputPlayerAmount(name);
    }

    private String inputHitCommand(final Player player) {
        try {
            return inputView.readHitCommand(player.getName());
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
        return inputHitCommand(player);
    }

    private List<ParticipantName> creatPlayersName(final List<String> readPlayerName) {
        List<ParticipantName> playersName = new ArrayList<>();
        for (String name : readPlayerName) {
            playersName.add(new ParticipantName(name));
        }
        return playersName;
    }

    private List<Amount> creatPlayerAmount(final List<ParticipantName> playersName) {
        List<Amount> playerAmount = new ArrayList<>();
        for (ParticipantName name : playersName) {
            playerAmount.add(inputPlayerAmount(name.getName()));
        }
        return playerAmount;
    }
}

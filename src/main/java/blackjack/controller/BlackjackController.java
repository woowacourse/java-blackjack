package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.game.BlackJackReferee;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.BlackjackGameResult;
import blackjack.domain.participant.Participant;
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
        List<String> playersName = inputPlayerName();
        List<String> playerAmount = creatPlayerAmount(playersName);
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
        outputView.printAllWinORLose(blackjackGameResult);
    }

    private List<String> creatPlayerAmount(List<String> playersName) {
        List<String> playerAmount = new ArrayList<>();
        for (String name : playersName) {
            playerAmount.add(inputPlayerAmount(name));
        }
        return playerAmount;
    }

    private List<String> inputPlayerName() {
        try {
            return inputView.readPlayerName();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputPlayerName();
    }

    private String inputPlayerAmount(String name) {
        try {
            return inputView.readPlayerAmount(name);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputView.readPlayerAmount(name);
    }

    private String inputHitCommand(final Player player) {
        try {
            return inputView.readHitCommand(player.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return inputHitCommand(player);
    }
}

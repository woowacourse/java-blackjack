package blackjack.controller;

import static blackjack.controller.AddCardOrNot.YES;

import java.util.List;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.dealer.DealerFirstOpenDto;
import blackjack.domain.participant.dealer.DealerWinningDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.player.PlayerResultDto;
import blackjack.domain.participant.player.PlayerWinningDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public Controller(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    public void run() {
        setGame();
        showFirstDraw();
        playersHit();
        dealerHit();
        printFinalCards();
        printWinningResult();
    }

    private void printWinningResult() {
        blackjackGame.calculateWinning();
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }

    private void printFinalCards() {
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }

    private void dealerHit() {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void playersHit() {
        for (int i = 0; i < blackjackGame.countPlayer(); i++) {
            Name userName = blackjackGame.findUserNameByIndex(i);
            playerHit(i, userName);
        }
    }
    private void playerHit(int i, Name userName) {
        String command = inputView.readCommandToAddCardOrNot(userName);

        while (AddCardOrNot.of(command).equals(YES) && !blackjackGame.isBust(i)) {
            blackjackGame.supplyAdditionalCard(i);
            ParticipantCardsDto playerCard = blackjackGame.getPlayerCardsByIndex(i);
            outputView.printPlayerCard(playerCard);
            if (blackjackGame.isBust(i)) {
                break;
            }
            command = inputView.readCommandToAddCardOrNot(userName);
        }
    }
    private void showFirstDraw() {
        DealerFirstOpenDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<ParticipantCardsDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);
    }

    private void setGame() {
        List<String> names = inputView.readPlayerNames();
        for (String name : names) {
            blackjackGame.addPlayer(new Player(new Name(name)));
        }
        blackjackGame.supplyCardsToDealer();
        blackjackGame.supplyCardsToPlayers();
        outputView.printFirstDrawMessage(names);
    }
}

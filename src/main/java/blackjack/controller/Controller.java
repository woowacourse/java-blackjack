package blackjack.controller;

import java.util.List;

import blackjack.domain.game.BlackjackGame;
import blackjack.domain.participant.dealer.DealerFirstCardDto;
import blackjack.domain.participant.dealer.DealerWinningDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.player.Player;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.player.PlayerResultDto;
import blackjack.domain.participant.player.PlayerWinningDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.function.Consumer;
import java.util.function.Function;

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
        supplyAdditionalCards();
        printFinalCards();
        printWinningResult();
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

    private void showFirstDraw() {
        DealerFirstCardDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<ParticipantCardsDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);
    }

    private void supplyAdditionalCards() {
        supplyAdditionalCardToPlayers();
        supplyAdditionalCardToDealer();
    }

    private void supplyAdditionalCardToPlayers() {
        Function<Name, AddCardOrNot> decideAddCardOrNot = (Name name) -> {
            String command = inputView.readCommandToAddCardOrNot(name);
            return AddCardOrNot.of(command);
        };
        Consumer<Player> showPlayerCards = (player) -> outputView.printPlayerCard(ParticipantCardsDto.from(player));
        blackjackGame.supplyCardToPlayerNameOf(decideAddCardOrNot, showPlayerCards);
    }

    private void supplyAdditionalCardToDealer() {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void printFinalCards() {
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }

    private void printWinningResult() {
        blackjackGame.calculateWinning();
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }
}

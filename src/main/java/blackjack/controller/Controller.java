package blackjack.controller;

import blackjack.domain.BetAmount;
import blackjack.domain.game.BlackjackGame;
import blackjack.domain.game.GameResultDto;
import blackjack.domain.game.ParticipantPrizeDto;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.ParticipantCardsDto;
import blackjack.domain.participant.ParticipantResultDto;
import blackjack.domain.participant.dealer.DealerFirstCardDto;
import blackjack.domain.participant.player.AddCardOrNot;
import blackjack.domain.participant.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

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
        showFinalCards();
        showPrize();
    }

    private void setGame() {
        List<String> names = inputView.readPlayerNames();
        for (String name : names) {
            int betAmount = inputView.readBetAmount(name);
            blackjackGame.addPlayer(new Player(new Name(name), new BetAmount(betAmount)));
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
        blackjackGame.supplyAdditionalCardToPlayersBy(this::readAddOrNot, this::showCurrentCards);
        blackjackGame.supplyAdditionalCardToDealerAnd(ignore -> outputView.printDealerHitMessage());
    }

    private AddCardOrNot readAddOrNot(Participant participant) {
        String command = inputView.readCommandToAddCardOrNot(participant.getName());
        return AddCardOrNot.of(command);
    }

    private void showCurrentCards(Participant participant) {
        outputView.printPlayerCard(ParticipantCardsDto.from(participant));
    }

    private void showFinalCards() {
        ParticipantResultDto dealerResult = blackjackGame.getDealerResult();
        List<ParticipantResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }

    private void showWinningResult() {
        GameResultDto gameResultDto = blackjackGame.calculateWinning();
        outputView.printWinningResults(gameResultDto.getDealerResult(), gameResultDto.getPlayerToResult());
    }

    private void showPrize() {
        List<ParticipantPrizeDto> participantsPrize = blackjackGame.calculatePrize();
        outputView.printFinalPrize(participantsPrize);
    }
}

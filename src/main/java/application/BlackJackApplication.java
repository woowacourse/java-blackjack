package application;

import domain.BlackJackGame;
import domain.BlackJackGameGenerator;
import domain.card.Card;
import dto.response.BattingResult;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    private final BlackJackGameGenerator blackJackGameGenerator;

    public BlackJackApplication(InputView inputView,
                                OutputView outputView,
                                BlackJackGameGenerator blackJackGameGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackGameGenerator = blackJackGameGenerator;
    }

    public void run() {
        BlackJackGame blackJackGame = blackJackGameGenerator.create(inputView, outputView);

        splitCards(blackJackGame);
        drawCards(blackJackGame);

        printParticipantResults(blackJackGame);
        printBattingResults(blackJackGame);
    }

    private void splitCards(BlackJackGame blackJackGame) {
        blackJackGame.splitCards();
        DrawnCardsInfo dealerCardInfo = createDealerCardInfo(blackJackGame);
        List<DrawnCardsInfo> playerCardInfos = createPlayerCardInfos(blackJackGame);

        outputView.printCardSplitMessage(dealerCardInfo, playerCardInfos);
    }

    private DrawnCardsInfo createDealerCardInfo(BlackJackGame blackJackGame) {
        List<Card> dealerOpenCard = blackJackGame.getDealerOpenCard();
        DrawnCardsInfo dealerCardInfo = DrawnCardsInfo.toDto(blackJackGame.getDealerName(), dealerOpenCard);
        return dealerCardInfo;
    }

    private List<DrawnCardsInfo> createPlayerCardInfos(BlackJackGame blackJackGame) {
        List<DrawnCardsInfo> playerCardInfos = new ArrayList<>();

        for (String playerName : blackJackGame.getPlayersName()) {
            List<Card> openCards = blackJackGame.getOpenCardsByName(playerName);
            playerCardInfos.add(DrawnCardsInfo.toDto(playerName, openCards));
        }
        return playerCardInfos;
    }

    private void drawCards(BlackJackGame blackJackGame) {
        for (String playerName : blackJackGame.getPlayersName()) {
            drawPlayerCards(blackJackGame, playerName);
        }
        drawDealerCards(blackJackGame);
    }

    private void drawPlayerCards(BlackJackGame blackJackGame, String playerName) {
        while (getDrawCommand(playerName).isDraw()) {
            blackJackGame.drawPlayerCardByName(playerName);
            DrawnCardsInfo openCardsInfo = createOpenCardsInfo(blackJackGame, playerName);
            outputView.printPlayerCardInfo(openCardsInfo);

            if (!blackJackGame.canPlayerDrawMore(playerName)) {
                break;
            }
        }
    }

    private DrawnCardsInfo createOpenCardsInfo(BlackJackGame blackJackGame, String playerName) {
        List<Card> openCards = blackJackGame.getOpenCardsByName(playerName);
        return DrawnCardsInfo.toDto(playerName, openCards);
    }

    private DrawCommand getDrawCommand(final String playerName) {
        try {
            String rawCommand = inputView.readChoiceOfDrawCard(playerName);
            DrawCommand drawCommand = DrawCommand.from(rawCommand);
            return drawCommand;
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getDrawCommand(playerName);
        }
    }

    private void drawDealerCards(BlackJackGame blackJackGame) {
        while (blackJackGame.canDealerDrawMore()) {
            blackJackGame.drawDealerCard();
            outputView.printDealerCardPickMessage(blackJackGame.getDealerDrawLimitScore());
        }
    }

    private void printParticipantResults(BlackJackGame blackJackGame) {
        List<ParticipantResult> playerResults = new ArrayList<>();

        for (String playerName : blackJackGame.getPlayersName()) {
            List<Card> drawnCards = blackJackGame.getDrawnCardByNames(playerName);
            int score = blackJackGame.getScoreByName(playerName);
            playerResults.add(ParticipantResult.toDto(playerName, drawnCards, score));
        }

        ParticipantResult dealerResult = createDealerResult(blackJackGame);

        outputView.printParticipantResults(dealerResult, playerResults);
    }

    private ParticipantResult createDealerResult(BlackJackGame blackJackGame) {
        List<Card> dealerCards = blackJackGame.getDealerCards();
        int dealerScore = blackJackGame.getDealerScore();
        String dealerName = blackJackGame.getDealerName();

        return ParticipantResult.toDto(dealerName, dealerCards, dealerScore);
    }

    private void printBattingResults(BlackJackGame blackJackGame) {
        List<BattingResult> battingResults = new ArrayList<>();

        for (String playerName : blackJackGame.getPlayersName()) {
            int result = blackJackGame.calculateBettingResult(playerName);
            battingResults.add(new BattingResult(playerName, result));
        }

        outputView.printBattingResults(battingResults);
    }
}

package application;

import static java.util.stream.Collectors.toList;

import domain.BlackJackBettingMachine;
import domain.BlackJackGame;
import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.ParticipantGenerator;
import domain.participant.Players;
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

    public BlackJackApplication(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();
        BlackJackBettingMachine blackJackBettingMachine = new BlackJackBettingMachine();

        betEachPlayer(blackJackGame, blackJackBettingMachine);
        splitCards(blackJackGame);
        drawCards(blackJackGame);

        printParticipantResults(blackJackGame);
        printBattingResults(blackJackGame, blackJackBettingMachine);
    }

    private BlackJackGame createBlackJackGame() {
        CardDeck cardDeck = CardDeckGenerator.create();
        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();
        return new BlackJackGame(players, dealer, cardDeck);
    }

    private Players createPlayers() {
        try {
            List<String> rawNames = inputView.readPlayerNames();

            List<Name> names = rawNames.stream()
                    .map(Name::new)
                    .collect(toList());

            return ParticipantGenerator.createPlayers(names);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return createPlayers();
        }
    }

    private void betEachPlayer(BlackJackGame blackJackGame, BlackJackBettingMachine blackJackBettingMachine) {
        for (String playerName : blackJackGame.getPlayersName()) {
            int bettingMoney = getBettingMoney(playerName);
            blackJackBettingMachine.betMoney(playerName, bettingMoney);
        }
    }

    private int getBettingMoney(String playerName) {
        try {
            return inputView.readBettingMoneyByName(playerName);
        } catch (IllegalArgumentException exception) {
            outputView.printExceptionMessage(exception.getMessage());
            return getBettingMoney(playerName);
        }
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

            if (!blackJackGame.canPlayerDrawMore(playerName)) break;
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

        outputView.printParticipantResults(dealerResult,playerResults);
    }

    private ParticipantResult createDealerResult(BlackJackGame blackJackGame) {
        List<Card> dealerCards = blackJackGame.getDealerCards();
        int dealerScore = blackJackGame.getDealerScore();
        String dealerName = blackJackGame.getDealerName();
        return ParticipantResult.toDto(dealerName, dealerCards, dealerScore);
    }

    private void printBattingResults(BlackJackGame blackJackGame, BlackJackBettingMachine blackJackBettingMachine) {
        List<BattingResult> battingResultDtos = new ArrayList<>();

        for (String playerName : blackJackGame.getPlayersName()) {
            int bettingMoney = blackJackBettingMachine.findBetMoneyByName(playerName);
            int result = blackJackGame.getResult(playerName, bettingMoney);
            battingResultDtos.add(new BattingResult(playerName, result));
        }

        outputView.printBattingResults(battingResultDtos);
    }
}

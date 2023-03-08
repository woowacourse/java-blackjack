package application;

import static java.util.stream.Collectors.toList;

import domain.BlackJackBettingMachine;
import domain.BlackJackGame;
import domain.card.CardDeck;
import domain.card.CardDeckGenerator;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.ParticipantGenerator;
import domain.participant.Players;
import dto.response.BattingResultDto;
import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import dto.response.WinLoseResult;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private final InputView inputView;
    private final OutputView outputView;
    //TODO: 생성 위치 고민
    private final BlackJackBettingMachine blackJackBettingMachine;


    public BlackJackApplication(InputView inputView, OutputView outputView,
                                BlackJackBettingMachine blackJackBettingMachine) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackJackBettingMachine = blackJackBettingMachine;
    }

    public void run() {
        BlackJackGame blackJackGame = createBlackJackGame();

        for (String playerName : blackJackGame.getPlayersName()) {
            int bettingMoney = inputView.readBettingMoneyByName(playerName);
            blackJackBettingMachine.betMoney(playerName, bettingMoney);
        }

        splitCards(blackJackGame);
        drawCards(blackJackGame);



        printParticipantResults(blackJackGame);

        List<BattingResultDto> battingResultDtos = new ArrayList<>();
        for (String playerName : blackJackGame.getPlayersName()) {
            int bettingMoney = blackJackBettingMachine.findBetMoneyByName(playerName);
            double result = blackJackGame.getResult(playerName, bettingMoney);
            battingResultDtos.add(new BattingResultDto(playerName, result));
        }

        outputView.printBattingResults(battingResultDtos);
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

    private void splitCards(BlackJackGame blackJackGame) {
        List<DrawnCardsInfo> drawnCardsInfos = blackJackGame.splitCards();
        outputView.printCardSplitMessage(drawnCardsInfos);
    }

    private void drawCards(BlackJackGame blackJackGame) {
        for (String playerName : blackJackGame.getPlayersName()) {
            drawPlayerCards(blackJackGame, playerName);
        }
        drawDealerCards(blackJackGame);
    }

    private void drawPlayerCards(BlackJackGame blackJackGame, String playerName) {
        while (getDrawCommand(playerName).isDraw()) {
            DrawnCardsInfo drawnCardsInfo = blackJackGame.drawPlayerCardByName(playerName);
            outputView.printPlayerCardInfo(drawnCardsInfo);

            if (!blackJackGame.canPlayerDrawMore(playerName)) break;
        }
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
        List<ParticipantResult> participantResults = blackJackGame.getParticipantResults();
        outputView.printParticipantResults(participantResults);
    }

    private void printWinLoseResult(BlackJackGame blackJackGame) {
        List<WinLoseResult> winLoseResults = blackJackGame.getWinLoseResults();
        outputView.printWinLoseResult(blackJackGame.getDealerName(), winLoseResults);
    }
}

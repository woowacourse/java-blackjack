package controller;

import domain.betting.Betting;
import domain.betting.BettingAmount;
import domain.card.GameCards;
import domain.game.GamblersGameResult;
import domain.game.Game;
import domain.player.Dealer;
import domain.player.Gambler;
import view.requestDto.AgreementRequestDto;
import view.requestDto.BettingAmountRequestDto;
import view.responseDto.DealerResultDto;
import view.responseDto.ParticipantHandResponseDto;
import view.responseDto.ParticipantsGameInfoDto;
import view.responseDto.ParticipantsHandResponseDto;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> names = inputGamblersInfo();
        Map<String, BettingAmount> gamblerNameAndBettingInfo = betByName(names);
        Game game = initializeGame(gamblerNameAndBettingInfo);

        playGame(game);
        checkDealerHand(game);

        printParticipantsResult(game);

        determineFinalGameProfit(game.getResult());
    }

    private List<String> inputGamblersInfo() {
        return inputView.askGamblerNames().names();
    }

    private Map<String, BettingAmount> betByName(List<String> names) {
        Betting betting = new Betting(names);
        for (String name : names) {
            BettingAmountRequestDto bettingAmountRequestDto = inputView.askBettingAmount(name);
            betting.betBettingAmount(name,
                    new BettingAmount(bettingAmountRequestDto.getBettingAmount()));
        }
        return betting.getBettingAmounts();
    }

    private Game initializeGame(Map<String, BettingAmount> gamblerNameAndBettingInfo) {
        Game game = new Game(Dealer.DEALER_NAME, gamblerNameAndBettingInfo,
                GameCards.DEFAULT_CARD_SET);

        outputView.printInitialDeal(gamblerNameAndBettingInfo.keySet().stream().toList());
        game.initializeGame();
        outputView.printParticipantsInfo(
                new ParticipantsHandResponseDto(game.getInitialParticipantsHandInfo()));
        return game;
    }

    private void playGame(Game game) {
        List<Gambler> gamblers = game.getGamblersList();
        for (Gambler gambler : gamblers) {
            if (gambler.isBlackJack()) {
                outputView.printBlackJackMessage(gambler.getName());
                continue;
            }
            playTurn(game, gambler);
        }
    }

    private void playTurn(Game game, Gambler gambler) {
        while (!gambler.isBust()) {
            AgreementRequestDto agreementRequestDto = inputView.askHitOrStand(gambler.getName());
            if (agreementRequestDto.agreement().equals(InputView.HIT)) {
                gambler.addCard(game.pickCard());
                outputView.printParticipantInfo(
                        new ParticipantHandResponseDto(gambler.getName(), gambler.getHandInfo()));
            }
            if (agreementRequestDto.agreement().equals(InputView.STAND)) {
                break;
            }
        }
    }

    private void checkDealerHand(Game game) {
        if (game.shouldDealerDraw()) {
            outputView.printDealerCardIsBelowDrawThreshold();
            game.addDealerCard();
        }
    }

    private void printParticipantsResult(Game game) {
        outputView.printParticipantsGameInfo(
                new ParticipantsGameInfoDto(game.getParticipantGameInfos()));
    }

    private void determineFinalGameProfit(GamblersGameResult gamblersGameResult) {
        outputView.printDealerResult(
                new DealerResultDto(gamblersGameResult.getDealerProfit().getProfit()));
        outputView.printGamblerResult(gamblersGameResult.getParticipantProfits());
    }
}

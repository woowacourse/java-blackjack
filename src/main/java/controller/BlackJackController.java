//package controller;
//
//import static util.Constants.COMMA_DELIMITER;
//import static util.Constants.DEALER_NAME;
//import static util.Constants.DEFAULT_CARD_SET;
//import static util.Constants.HIT;
//import static util.Constants.STAND;
//
//import domain.betting.Betting;
//import domain.betting.BettingAmount;
//import domain.game.GamblersGameResult;
//import domain.game.Game;
//import domain.player.Gambler;
//import dto.AgreementRequestDto;
//import dto.BettingAmountRequestDto;
//import dto.DealerResultDto;
//import dto.ParticipantHandResponseDto;
//import dto.ParticipantsGameInfoDto;
//import dto.ParticipantsHandResponseDto;
//import java.util.List;
//import java.util.Map;
//import util.Parser;
//import view.InputView;
//import view.OutputView;
//
//public class BlackJackController {
//    private final InputView inputView;
//    private final OutputView outputView;
//
//    public BlackJackController(InputView inputView, OutputView outputView) {
//        this.inputView = inputView;
//        this.outputView = outputView;
//    }
//
//    public void run() {
//        List<String> names = inputGamblersInfo();
//        Map<String, BettingAmount> gamblerNameAndBettingInfo = betByName(names);
//        Game game = initializeGame(names);
//
//        playGame(game);
//        checkDealerHand(game);
//
//        printParticipantsResult(game);
//
//        determineFinalGameResult(game.getResult());
//    }
//
//    private List<String> inputGamblersInfo() {
//        String name = inputView.askGamblerNames().name();
//        return Parser.parse(name, COMMA_DELIMITER);
//    }
//
//    private Map<String, BettingAmount> betByName(List<String> names) {
//        Betting betting = new Betting(names);
//        return betting.getGamblerNameAndBettingInfo();
//    }
//    private Game initializeGame(List<String> names) {
//        Game game = new Game(DEALER_NAME, names, DEFAULT_CARD_SET);
//
//        outputView.printInitialDeal(names);
//        game.initializeGame();
//        outputView.printParticipantsInfo(
//                new ParticipantsHandResponseDto(game.getInitialParticipantsHandInfo())
//        );
//
//        return game;
//    }
//
//    private void playGame(Game game) {
//        List<Gambler> gamblers = game.getGamblersList();
//        for(Gambler gambler : gamblers) {
//            playTurn(game, gambler);
//        }
//    }
//
//    private void playTurn(Game game, Gambler gambler) {
//        while(!gambler.isBust()) {
//            AgreementRequestDto agreementRequestDto = inputView.askHitOrStand(gambler.getName());
//            if (agreementRequestDto.agreement().equals(HIT)) {
//                gambler.addCard(game.pickCard());
//                outputView.printParticipantInfo(
//                        new ParticipantHandResponseDto(gambler.getName(), gambler.getHandInfo()));
//            }
//            if (agreementRequestDto.agreement().equals(STAND)) {
//                break;
//            }
//        }
//    }
//
//    private void checkDealerHand(Game game) {
//        if (game.shouldDealerDraw()) {
//            outputView.printDealerCardIsUnder16();
//            game.addDealerCard();
//        }
//    }
//
//    private void printParticipantsResult(Game game) {
//        outputView.printParticipantsGameInfo(new ParticipantsGameInfoDto(
//                game.getParticipantGameInfos()
//        ));
//    }
//
//    private void determineFinalGameResult(GamblersGameResult gamblersGameResult) {
//        outputView.printDealerResult(
//                new DealerResultDto(gamblersGameResult.countDealerWin(),
//                        gamblersGameResult.countDealerLose(),
//                        gamblersGameResult.countDealerDraw()));
//        outputView.printGamblerResult(
//                gamblersGameResult.getResultInfo()
//        );
//    }
//}

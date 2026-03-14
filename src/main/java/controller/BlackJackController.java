package controller;

import static util.Constants.COMMA_DELIMITER;
import static util.Constants.DEALER_NAME;
import static util.Constants.HIT;
import static util.Constants.STAND;

import domain.card.GameCards;
import domain.game.GamblersGameResult;
import domain.game.Game;
import domain.player.Dealer;
import domain.player.Gambler;
import domain.player.Gamblers;
import domain.player.attribute.Hand;
import domain.player.attribute.Money;
import domain.player.attribute.Name;
import dto.request.AgreementRequestDto;
import dto.response.DealerResultDto;
import dto.response.ParticipantHandResponseDto;
import dto.response.ParticipantsGameInfoDto;
import dto.response.ParticipantsHandResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import util.Parser;
import view.input.InputView;
import view.output.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Dealer dealer = makeDealer();
        Gamblers gamblers = makeGambers();
        GameCards gambeCards = new GameCards(1);

        Game game = initializeGame(dealer, gamblers, gambeCards);

        playGame(game);
        checkDealerHand(game);

        printParticipantsResult(game);

        determineFinalGameResult(game.getResult());
    }

    private Dealer makeDealer() {
        Name dealerName = new Name(DEALER_NAME);
        Hand dealerHand = new Hand();
        Dealer dealer = new Dealer(dealerName, dealerHand);

        return dealer;
    }

    private Gamblers makeGambers() {
        List<Name> names = inputGamblersInfo()
                .stream()
                .map(Name::new)
                .toList();

        List<Gambler> gamblerList = names.stream()
                .map(name -> {
                    String betAmountInput = inputView.askGamblerBetAmount(name.getName())
                            .betAmount();
                    Hand hand = new Hand();
                    Money betAmount = new Money(betAmountInput);

                    return new Gambler(name, hand, betAmount);
                })
                .toList();
        return new Gamblers(gamblerList);
    }

    private List<String> inputGamblersInfo() {
        String name = inputView.askGamblerNames().name();
        return Parser.parse(name, COMMA_DELIMITER);
    }

    private Game initializeGame(Dealer dealer, Gamblers gamblers, GameCards cards) {
        Game game = new Game(dealer, gamblers, cards);

        outputView.printInitialDeal(gamblers.getNames());
        game.initializeGame();
        outputView.printParticipantsInfo(
                new ParticipantsHandResponseDto(game.getInitialParticipantsHandInfo())
        );

        return game;
    }

    private void playGame(Game game) {
        List<Gambler> gamblers = game.getGamblersList();
        for(Gambler gambler : gamblers) {
            playTurn(game, gambler);
        }
    }

    private void playTurn(Game game, Gambler gambler) {
        while(!gambler.isBust()) {
            AgreementRequestDto agreementRequestDto = inputView.askHitOrStand(gambler.getName());
            if (agreementRequestDto.agreement().equals(HIT)) {
                gambler.addCard(game.pickCard());
                outputView.printParticipantInfo(
                        new ParticipantHandResponseDto(gambler.getName(), gambler.getHandInfo()));
            }
            if (agreementRequestDto.agreement().equals(STAND)) {
                break;
            }
        }
    }

    private void checkDealerHand(Game game) {
        if (game.shouldDealerDraw()) {
            outputView.printDealerCardIsUnder16();
            game.addDealerCard();
        }
    }

    private void printParticipantsResult(Game game) {
        outputView.printParticipantsGameInfo(new ParticipantsGameInfoDto(
                game.getParticipantGameInfos()
        ));
    }

    private void determineFinalGameResult(GamblersGameResult gamblersGameResult) {
        outputView.printDealerResult(new DealerResultDto(gamblersGameResult.getDealerResult()));
        outputView.printGamblerResult(gamblersGameResult.getResultInfo());
    }
}
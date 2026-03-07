package controller;

import static util.Constants.COMMA_DELIMITER;
import static util.Constants.DEALER_NAME;
import static util.Constants.HIT;
import static util.Constants.STAND;

import domain.game.Game;
import domain.player.Gambler;
import dto.AgreementRequestDto;
import dto.CardInfoResponseDto;
import dto.ParticipantCardsResponseDto;
import java.util.List;
import util.Parser;
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
        Game game = initializeGame(names);
        List<Gambler> gamblers = playGame(game);
    }

    private List<String> inputGamblersInfo() {
        String name = inputView.askGamblerNames().name();
        return Parser.parse(name, COMMA_DELIMITER);
    }

    private Game initializeGame(List<String> names) {
        Game game = new Game(DEALER_NAME, names, 1);

        outputView.printInitialDeal(names);
        game.initializeGame();
        outputView.printInitialInfo(new CardInfoResponseDto(game.getParticipantsHandInfo()));

        return game;
    }

    private List<Gambler> playGame(Game game) {
        List<Gambler> gamblers = game.getGamblersList();
        for(Gambler gambler : gamblers) {
            playTurn(game, gambler);
        }
        return gamblers;
    }

    private void playTurn(Game game, Gambler gambler) {
        while(!gambler.isBust()) {
            AgreementRequestDto agreementRequestDto = inputView.askHitOrStand(gambler.getName());
            if (agreementRequestDto.agreement() == HIT) {
                gambler.addCard(game.pickCard());
            }
            if (agreementRequestDto.agreement() == STAND) {
                break;
            }
        }
        outputView.printPaticipantInfo(new ParticipantCardsResponseDto(gambler.getName(), gambler.getHandInfo()));
    }
}
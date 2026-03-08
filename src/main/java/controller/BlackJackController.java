package controller;

import domain.CardCreationStrategy;
import domain.Game;
import dto.GameResultDto;
import dto.ParticipantDto;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import view.InputView;
import view.OutputView;

public class BlackJackController implements GameDelegate {
    private final InputView inputView;
    private final OutputView outputView;
    private final CardCreationStrategy strategy;

    public BlackJackController(InputView inputView, OutputView outputView, CardCreationStrategy strategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.strategy = strategy;
    }

    public void doGame() {
        Game game = retry(Game::ready, this, strategy);
        retry(game::play, this);
        game.end(this);
    }

    @Override
    public List<String> askPlayerNames() {
        outputView.printNamePrompt();
        return inputView.readNames();
    }

    @Override
    public boolean askDrawCard(String playerName) {
        outputView.printHitOrStandPrompt(playerName);
        String input = inputView.readHitOrStand();
        return input.equals("y");
    }

    @Override
    public void showInitialParticipantCards(ParticipantDto dealerDto, List<ParticipantDto> playerDtos) {
        outputView.printInitialCardShareDetail(dealerDto, playerDtos);
    }

    @Override
    public void showPlayerCards(ParticipantDto participantDto) {
        outputView.printUserCardInfo(participantDto);
    }

    @Override
    public void showDealerOneMoreCardMessage() {
        outputView.printAdditionalCardForDealerDescription();
    }

    @Override
    public void showGameResult(GameResultDto resultDto) {
        outputView.printCardInfoWithSum(resultDto);
        outputView.printWinLossResult(resultDto);
    }

    private <T, U, R> R retry(BiFunction<T, U, R> biFunction, T input1, U input2) {
        while (true) {
            try {
                return biFunction.apply(input1, input2);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }

    private <T> void retry(Consumer<T> consumer, T input) {
        while (true) {
            try {
                consumer.accept(input);
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}


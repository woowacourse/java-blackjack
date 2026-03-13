package controller;

import domain.game.BlackjackGame;
import domain.player.Dealer;
import domain.random.RandomValueGeneratorImpl;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.ParticipantsCardsDto;
import dto.StatisticsDto;
import java.util.List;
import util.Parser;
import view.InputView;
import view.OutputView;

public class BlackjackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        BlackjackGame game = createGame();
        printInitialCards(game);
        playPlayers(game);
        playDealer(game);
        printResult(game);
    }

    private BlackjackGame createGame() {
        List<String> names = Parser.parse(inputView.readPlayerName());
        List<Integer> amounts = names.stream()
                .map(inputView::readPlayerBettingAmount)
                .toList();
        return BlackjackGame.create(names, amounts, new RandomValueGeneratorImpl());
    }

    private void printInitialCards(BlackjackGame game) {
        outputView.drawCard(new NamesDto(game.getDealerName(), game.getPlayerNames()));
        outputView.showCard(ParticipantsCardsDto.ofDealerOpen(game.getDealerName(), game.getDealerOpenCard()));

        game.getPlayerNames().forEach(name ->
                outputView.showCard(ParticipantsCardsDto.ofPlayer(name, game.getCardsOf(name))));
    }

    private void playPlayers(BlackjackGame game) {
        for (String name : game.getPlayerNames()) {
            playSinglePlayer(game, name);
        }
    }

    private void playSinglePlayer(BlackjackGame game, String name) {
        if (game.isBlackjack(name)) {
            outputView.congratulateBlackjack(name);
            return;
        }
        hitUntilBustOrStop(game, name);
    }

    private void hitUntilBustOrStop(BlackjackGame game, String name) {
        while (game.canHit(name) && inputView.readNeedToHit(name)) {
            game.hitPlayer(name);
            outputView.showCard(ParticipantsCardsDto.ofPlayer(name, game.getCardsOf(name)));
        }
    }

    private void playDealer(BlackjackGame game) {
        while (game.dealerNeedsToHit() && !game.areAllPlayersBust()) {
            game.hitDealer();
            outputView.drawDealer(new DealerDrawDto(game.getDealerName(), Dealer.HIT_BOUNDARY));
        }
    }

    private void printResult(BlackjackGame game) {
        outputView.showCardsAndScore(
                ParticipantsCardsDto.ofDealerAll(game.getDealerName(), game.getDealerCards()),
                game.getDealerScore()
        );
        game.getPlayerNames().forEach(name ->
                outputView.showCardsAndScore(ParticipantsCardsDto.ofPlayer(name, game.getCardsOf(name)),
                        game.getPlayerScore(name)));

        printProfit(game);
    }

    private void printProfit(BlackjackGame game) {
        List<StatisticsDto> statisticsDtos = game.getPlayerNames().stream()
                .map(name -> new StatisticsDto(name, game.calculatePlayerProfit(name)))
                .toList();

        int dealerProfit = game.calculateDealerProfit();

        outputView.showResultStatistics(statisticsDtos, game.getDealerName(), dealerProfit);
    }
}
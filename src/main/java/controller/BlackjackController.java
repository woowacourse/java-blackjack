package controller;

import domain.game.BlackjackGame;
import domain.player.Dealer;
import domain.player.Player;
import domain.random.RandomValueGeneratorImpl;
import dto.DealerDrawDto;
import dto.NamesDto;
import dto.ParticipantsCardsDto;
import dto.StatisticsDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<String, Integer> bettingInfos = new HashMap<>();
        for (String name : names) {
            bettingInfos.put(name, inputView.readPlayerBettingAmount(name));
        }

        return BlackjackGame.create(bettingInfos, new RandomValueGeneratorImpl());
    }

    private void printInitialCards(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        List<Player> players = game.getPlayers();
        List<String> playerNames = players.stream().map(Player::name).toList();

        outputView.drawCard(new NamesDto(dealer.name(), playerNames));
        outputView.showCard(ParticipantsCardsDto.ofDealerOpen(dealer.name(), dealer.openFirstCard()));

        players.forEach(p ->
                outputView.showCard(ParticipantsCardsDto.ofPlayer(p.name(), p.cards())));
    }

    private void playPlayers(BlackjackGame game) {
        while (game.isNotFinishPlayersRound()) {
            playSingleTurn(game);
        }
    }

    private void playSingleTurn(BlackjackGame game) {
        Player currentPlayer = game.getCurrentPlayer();

        if (!currentPlayer.canHit()) {
            finishCurrentTurn(game, currentPlayer);
            return;
        }
        proceedCurrentTurn(game, currentPlayer);
    }

    private void finishCurrentTurn(BlackjackGame game, Player player) {
        printIfBlackjack(player);
        game.passTurn();
    }

    private void proceedCurrentTurn(BlackjackGame game, Player player) {
        boolean isHit = inputView.readNeedToHit(player.name());
        game.playCurrentPlayerTurn(isHit);

        if (isHit) {
            outputView.showCard(ParticipantsCardsDto.ofPlayer(player.name(), player.cards()));
        }
    }

    private void printIfBlackjack(Player player) {
        if (player.isBlackjack()) {
            outputView.congratulateBlackjack(player.name());
        }
    }

    private void playDealer(BlackjackGame game) {
        Dealer dealer = game.getDealer();
        while (dealer.needsToHit() && !game.areAllPlayersBust()) {
            game.hitDealer();
            outputView.drawDealer(new DealerDrawDto(dealer.name(), Dealer.HIT_BOUNDARY));
        }
    }

    private void printResult(BlackjackGame game) {
        Dealer dealer = game.getDealer();

        outputView.showCardsAndScore(
                ParticipantsCardsDto.ofDealerAll(dealer.name(), dealer.cards()), dealer.totalScore());

        game.getPlayers().forEach(p ->
                outputView.showCardsAndScore(ParticipantsCardsDto.ofPlayer(p.name(), p.cards()), p.totalScore()));

        printProfit(game);
    }

    private void printProfit(BlackjackGame game) {
        Dealer dealer = game.getDealer();

        List<StatisticsDto> statisticsDtos = game.getPlayers().stream()
                .map(p -> new StatisticsDto(p.name(), p.calculateProfit(dealer)))
                .toList();

        int dealerProfit = game.calculateDealerProfit();

        outputView.showResultStatistics(statisticsDtos, dealer.name(), dealerProfit);
    }
}

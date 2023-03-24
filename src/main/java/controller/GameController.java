package controller;

import java.util.ArrayList;
import java.util.List;

import controller.mapper.CardDtoMapper;
import controller.mapper.PlayerDtoMapper;
import controller.mapper.ResultMapper;
import domain.Game;
import domain.Result;
import domain.bank.Money;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.User;
import view.IllegalArgumentExceptionHandler;
import view.InputView;
import view.OutputView;

public class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Participants participants = Participants.of(inputView.readNames());
        Game game = new Game(participants, new Deck());

        placeBet(game);
        start(game);
        play(game);
        showCardsAndScores(game);
        showResultsOf(game);
        showProfitsOf(game);
    }

    private void placeBet(Game game) {
        for (User user : game.getUsers()) {
            placeBet(game, user);
        }
    }

    private void placeBet(Game game, User user) {
        IllegalArgumentExceptionHandler.handleByRepeating(() -> {
            int betAmount = inputView.askBetAmount(user.getName());
            Money money = Money.of(betAmount);
            game.bet(user, money);
        });
    }

    private void start(Game game) {
        game.dealTwice();

        outputView.printDealStatus(PlayerDtoMapper.map(game.getUsers()));
        outputView.printFirstCardOfDealer(CardDtoMapper.mapFirstOf(game.getDealer().getHand().getCards()));
        outputView.printUsersStatus(PlayerDtoMapper.map(game.getUsers()));
    }

    private void play(Game game) {
        for (var user : game.getUsers()) {
            selectHitOrStand(game, user);
        }
        selectHitOrStand(game, game.getDealer());
        game.evaluate();
    }

    private void showCardsAndScores(Game game) {
        List<Player> players = joinPlayers(game.getDealer(), game.getUsers());
        outputView.printCardsAndScores(PlayerDtoMapper.map(players));
    }

    private void showResultsOf(Game game) {
        List<Result> dealerResults = game.getDealerResults();
        outputView.printDealerResults(ResultMapper.map(dealerResults));
        for (var user : game.getUsers()) {
            Result result = game.getResultOf(user);
            outputView.printResult(user.getName(), ResultMapper.map(result));
        }
    }

    private void showProfitsOf(Game game) {
        outputView.printProfitStart();
        outputView.printProfit(game.getDealer().getName(), game.getDealerProfit());
        for (User user : game.getUsers()) {
            outputView.printProfit(user.getName(), game.getProfitOf(user).getValue());
        }
    }

    private void selectHitOrStand(Game game, User user) {
        while (user.canHit() && inputView.askForHit(user.getName())) {
            game.dealTo(user);
            outputView.printPlayerCards(user.getName(), CardDtoMapper.map(user.getHand().getCards()));
        }
    }

    private void selectHitOrStand(Game game, Dealer dealer) {
        int hitCount = 0;
        while (dealer.canHit()) {
            game.dealTo(dealer);
            ++hitCount;
        }
        outputView.noticeDealerHitOrStand(hitCount);
    }

    private List<Player> joinPlayers(Dealer dealer, List<User> users) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(users);
        return players;
    }
}

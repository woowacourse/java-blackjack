package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import domain.Game;
import domain.bank.Money;
import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.participant.User;
import dto.CardDto;
import dto.PlayerDto;
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

        outputView.printDealStatus(createDtosOf(game.getUsers()));
        outputView.printFirstCardOfDealer(createDtosOf(game.getDealer().getHand().getCards()));
        outputView.printUsersStatus(createDtosOf(game.getUsers()));
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
        outputView.printCardsAndScores(createDtosOf(players));
    }

    private void showResultsOf(Game game) {
        List<String> dealerResults = game.getDealerResults()
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
        outputView.printDealerResults(dealerResults);
        for (var user : game.getUsers()) {
            String userResult = game.getResultOf(user).name();
            outputView.printResult(user.getName(), userResult);
        }
    }

    private void showProfitsOf(Game game) {
        System.out.println(System.lineSeparator() + "## 최종 수익");
        outputView.printProfit(game.getDealer().getName(), game.getDealerProfit());
        for (User user : game.getUsers()) {
            outputView.printProfit(user.getName(), game.getProfitOf(user).getValue());
        }
    }

    private void selectHitOrStand(Game game, User user) {
        while (user.canHit() && inputView.askForHit(user.getName())) {
            game.dealTo(user);
            outputView.printPlayerCards(user.getName(), createDtosOf(user.getHand().getCards()));
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

    private List<PlayerDto> createDtosOf(List<? extends Player> players) {
        return players
                .stream()
                .map(this::createDtoOf)
                .collect(Collectors.toList());
    }

    private List<CardDto> createDtosOf(Set<Card> cards) {
        return cards
                .stream()
                .map(this::createDtoOf)
                .collect(Collectors.toList());
    }

    private PlayerDto createDtoOf(Player player) {
        return new PlayerDto(
                player.getName(),
                createDtosOf(player.getHand().getCards()),
                player.getHand().score().getScore());
    }

    private CardDto createDtoOf(Card card) {
        return new CardDto(card.getLetter().getLetter(), card.getFace().getName());
    }
}

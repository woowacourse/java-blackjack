package blackjackgame.controller;

import blackjackgame.domain.UserAction;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.card.ShuffledCardsGenerator;
import blackjackgame.domain.game.BlackJackGame;
import blackjackgame.domain.user.Bet;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Name;
import blackjackgame.domain.user.Names;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.Profit;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.NameDto;
import blackjackgame.domain.user.dto.ProfitDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import blackjackgame.view.InputView;
import blackjackgame.view.OutputView;

public class BlackJackGameController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        BlackJackGame blackJackGame = generateBlackJackGame();
        blackJackGame.drawDefaultCard();
        printSetUpResult(blackJackGame.getSetUpResult());
        progressPlayersTurn(blackJackGame);
        progressDealerTurn(blackJackGame);
        printUsersCardResult(blackJackGame);
        printFinalResult(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        Cards cards = new Cards(new ShuffledCardsGenerator());
        Dealer dealer = new Dealer();
        Names playerNames = repeatForValidInput(this::setUpPlayerNames);
        Players players = repeatForValidInput(() -> setUpPlayers(playerNames));
        return new BlackJackGame(players, dealer, cards);
    }

    private <T> T repeatForValidInput(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e);
            return repeatForValidInput(supplier);
        }
    }

    private Players setUpPlayers(Names playerNames) {
        List<Bet> playerBets = setUpPlayerBets(playerNames);
        return new Players(playerNames, playerBets);
    }

    private Names setUpPlayerNames() {
        outputView.printInputPlayerNamesMessage();
        List<String> playerNames = inputView.readPlayerNames();
        return new Names(playerNames);
    }

    private List<Bet> setUpPlayerBets(Names playerNames) {
        List<Bet> playerBetAmounts = new ArrayList<>();
        for (Name playerName : playerNames.getNames()) {
            playerBetAmounts.add(repeatForValidInput(() -> readPlayerBetAmount(playerName)));
        }
        return playerBetAmounts;
    }

    private Bet readPlayerBetAmount(Name playerName) {
        outputView.printInputPlayerBetAmountMessage(playerName.getName());
        return new Bet(inputView.readPlayerBetAmount());
    }

    private void printSetUpResult(Map<User, List<Card>> cardsByUser) {
        Map<NameDto, List<Card>> cardsByName = new LinkedHashMap<>();

        for (Entry<User, List<Card>> userCardsEntry : cardsByUser.entrySet()) {
            String name = userCardsEntry.getKey().getName();
            cardsByName.put(new NameDto(name), userCardsEntry.getValue());
        }

        outputView.printSetUpResult(cardsByName);
    }

    private void progressPlayersTurn(BlackJackGame blackJackGame) {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            progressPlayerTurn(blackJackGame, player);
        }
    }

    private void progressPlayerTurn(BlackJackGame blackJackGame, Player player) {
        while (player.isHittable()) {
            UserAction action = repeatForValidInput(() -> readDrawCommand(player));
            blackJackGame.takePlayerAction(player, action);
            printDrawResult(player);
        }
    }

    private UserAction readDrawCommand(Player player) {
        outputView.printAskOneMoreCardMessage(player.getName());
        return inputView.readDrawCommand();
    }

    private void printDrawResult(Player player) {
        outputView.printPlayerDrawResult(player.getName(), player.getCards());
    }

    private void progressDealerTurn(BlackJackGame blackJackGame) {
        blackJackGame.hitUntilSatisfyingDealerMinimumScore();

        if (blackJackGame.isDealerDrawExtraCount()) {
            outputView.printDealerDrawResult(blackJackGame.getDealerExtraDrawCount());
        }
    }

    private void printUsersCardResult(BlackJackGame blackJackGame) {
        Map<User, List<Card>> userResult = new LinkedHashMap<>();
        userResult.put(blackJackGame.getDealer(), blackJackGame.getDealer().getCards());
        blackJackGame.getPlayers().forEach(player -> userResult.put(player, player.getCards()));
        outputView.printUsersCardResult(userResult);
    }

    private void printFinalResult(BlackJackGame blackJackGame) {
        Map<NameDto, ProfitDto> finalResult = new LinkedHashMap<>();
        addDealerResult(blackJackGame, finalResult);
        addPlayerResults(blackJackGame, finalResult);
        outputView.printFinalResult(finalResult);
    }

    private void addDealerResult(BlackJackGame blackJackGame, Map<NameDto, ProfitDto> finalResult) {
        String dealerName = blackJackGame.getDealerName();
        int dealerProfit = blackJackGame.calculateDealerProfit();
        finalResult.put(new NameDto(dealerName), new ProfitDto(dealerProfit));
    }

    private void addPlayerResults(BlackJackGame blackJackGame, Map<NameDto, ProfitDto> finalResult) {
        Map<Player, Profit> betResultOfPlayer = blackJackGame.getBetResultOfPlayer();
        for (Entry<Player, Profit> playerProfitEntry : betResultOfPlayer.entrySet()) {
            String playerName = playerProfitEntry.getKey().getName();
            int playerProfit = playerProfitEntry.getValue().getAmount();
            finalResult.put(new NameDto(playerName), new ProfitDto(playerProfit));
        }
    }
}

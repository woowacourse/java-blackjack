package blackjack.game;

import blackjack.card.Deck;
import blackjack.player.Dealer;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = Deck.createShuffledFullDeck();
        Dealer dealer = new Dealer();
        Players players = createPlayers();
        PlayerBettingMoney playerBettingMoney = decideBettingMoney(players);
        initializeGame(deck, dealer, players);
        proceedPlayersTurn(deck, players);
        proceedDealerTurn(deck, dealer);

        showCardsWithScore(dealer, players);
        showMatchResult(dealer, players, playerBettingMoney);
    }

    private Players createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        outputView.printNewLine();
        return players;
    }

    private PlayerBettingMoney decideBettingMoney(Players players) {
        PlayerBettingMoney playerBettingMoney = new PlayerBettingMoney();
        for (Player player : players.getPlayers()) {
            outputView.printBettingRequestMessage(player.getName());
            Money money = new Money(inputView.readBattingAmount());
            playerBettingMoney.addBetting(player, money);
            outputView.printNewLine();
        }
        return playerBettingMoney;
    }

    private void initializeGame(Deck deck, Dealer dealer, Players players) {
        players.doInitialDraw(deck);
        dealer.doInitialDraw(deck);
        outputView.printInitializeBlackJack(players.getNames());
        showInitialCard(dealer, players);
    }

    private void showInitialCard(Dealer dealer, Players players) {
        outputView.printDealerFirstCard(dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
        outputView.printNewLine();
    }

    private void proceedPlayersTurn(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            proceedPlayerTurn(deck, player);
        }
        outputView.printNewLine();
    }

    private void proceedPlayerTurn(Deck deck, Player player) {
        Command command = askPlayerToDrawMore(player);
        if (command.isNo()) {
            return;
        }
        player.drawCard(deck);
        outputView.printPlayerCards(player.getName(), player.getCards());

        if (player.hasDrawableScore()) {
            proceedPlayerTurn(deck, player);
        }
    }

    private Command askPlayerToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
        String input = inputView.readCommand();
        return Command.from(input);
    }

    private void proceedDealerTurn(Deck deck, Dealer dealer) {
        while (dealer.hasDrawableScore()) {
            dealer.drawCard(deck);
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }
    }

    private void showCardsWithScore(Dealer dealer, Players players) {
        outputView.printDealerCardsWithScore(dealer.getCards(), dealer.getScore());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerCardsWithScore(player.getName(), player.getCards(), player.getScore());
        }
        outputView.printNewLine();
    }

    private void showMatchResult(Dealer dealer, Players players, PlayerBettingMoney bettingResults) {
        MatchResults matchResults = calculateMatchResults(dealer, players, bettingResults);
        outputView.printResultStart();
        showDealerResult(matchResults);
        showPlayersResult(players, matchResults);
    }

    private MatchResults calculateMatchResults(Dealer dealer, Players players, PlayerBettingMoney bettingResults) {
        MatchResults matchResults = new MatchResults(dealer.getHand());
        for (Player player : players.getPlayers()) {
            matchResults.addResult(player, bettingResults.getBettingAmountOf(player));
        }
        return matchResults;
    }

    private void showDealerResult(MatchResults matchResults) {
        outputView.printDealerResult(matchResults.getDealerResult());
    }

    private void showPlayersResult(Players players, MatchResults matchResults) {
        for (Player player : players.getPlayers()) {
            int playerResult = matchResults.getResultOf(player);
            outputView.printPlayerResult(player.getName(), playerResult);
        }
    }
}

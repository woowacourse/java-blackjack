package blackjack.game;

import blackjack.card.Deck;
import blackjack.player.Dealer;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        showMatchResult(dealer, playerBettingMoney);
    }

    private Players createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        outputView.printNewLine();
        return players;
    }

    private PlayerBettingMoney decideBettingMoney(Players players) {
        Map<Player, Money> result = players.getPlayers().stream()
                .collect(Collectors.toMap(player -> player, this::inputMoney));
        return new PlayerBettingMoney(result);
    }

    private Money inputMoney(Player player) {
        outputView.printBettingRequestMessage(player.getName());
        Money money = new Money(inputView.readBattingAmount());
        outputView.printNewLine();
        return money;
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
        if (player.hasDrawableScore() && isPlayerWantToDrawMore(player)) {
            player.drawCard(deck);
            outputView.printPlayerCards(player.getName(), player.getCards());
            proceedPlayerTurn(deck, player);
        }
    }

    private boolean isPlayerWantToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
        return inputView.readPlayerWantDrawMore();
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

    private void showMatchResult(Dealer dealer, PlayerBettingMoney bettingResults) {
        PlayerEarnings playerEarnings = calculateMatchResults(dealer, bettingResults);
        outputView.printResultStart();
        showDealerResult(playerEarnings);
        showPlayersResult(playerEarnings);
    }

    private PlayerEarnings calculateMatchResults(Dealer dealer, PlayerBettingMoney bettingResults) {
        return new PlayerEarnings(dealer, bettingResults.getBettingMoney());
    }

    private void showDealerResult(PlayerEarnings playerEarnings) {
        outputView.printDealerResult(playerEarnings.getDealerResult());
    }

    private void showPlayersResult(PlayerEarnings playerEarnings) {
        Map<Player, Integer> results = playerEarnings.getResults();
        for (Map.Entry<Player, Integer> result : results.entrySet()) {
            outputView.printPlayerResult(result.getKey().getName(), result.getValue());
        }
    }
}

package blackjack.game;

import blackjack.card.Deck;
import blackjack.player.Dealer;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    public static final int BLACKJACK_MAX_SCORE = 21;

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
        initGame(deck, dealer, players);
        playersDrawMore(deck, players);
        dealerDrawMore(deck, dealer);

        showCardResultWithScore(dealer, players);
        showMatchResult(dealer, players);
    }

    private Players createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        outputView.printNewLine();
        return players;
    }

    private void initGame(Deck deck, Dealer dealer, Players players) {
        players.initDrawCards(deck);
        dealer.initDrawCards(deck);
        outputView.printInitializeBlackJack(players.getNames());
        showInitCard(dealer, players);
    }

    private void showInitCard(Dealer dealer, Players players) {
        outputView.printDealerFirstCard(dealer.getFirstCard());

        for (Player player : players.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
        outputView.printNewLine();
    }

    private void playersDrawMore(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            playerDrawMore(deck, player);
        }
        outputView.printNewLine();
    }

    private void playerDrawMore(Deck deck, Player player) {
        Command command = askPlayerToDrawMore(player);
        if (command == Command.NO) {
            return;
        }
        player.drawCard(deck);
        outputView.printPlayerCards(player.getName(), player.getCards());

        if (player.hasDrawableScore()) {
            playerDrawMore(deck, player);
        }
    }

    private Command askPlayerToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
        String input = inputView.readCommand();
        return Command.from(input);
    }

    private void dealerDrawMore(Deck deck, Dealer dealer) {
        while (dealer.hasDrawableScore()) {
            dealer.drawCard(deck);
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }
    }

    private void showCardResultWithScore(Dealer dealer, Players players) {
        outputView.printDealerCardsWithResult(dealer.getCards(), dealer.getScore());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerCardsWithResult(player.getName(), player.getCards(), player.getScore());
        }
        outputView.printNewLine();
    }

    private void showMatchResult(Dealer dealer, Players players) {
        MatchResults matchResults = calculateMatchResults(dealer, players);
        outputView.printResultStart();
        showDealerResult(matchResults);
        showPlayersResult(players, matchResults);
    }

    private MatchResults calculateMatchResults(Dealer dealer, Players players) {
        MatchResults matchResults = new MatchResults();
        for (Player player : players.getPlayers()) {
            matchResults.addResult(player.getName(), player.getScore(), dealer.getScore());
        }
        return matchResults;
    }

    private void showDealerResult(MatchResults matchResults) {
        outputView.printDealerResult(
                matchResults.getResultCount(MatchResult.DEALER_WIN),
                matchResults.getResultCount(MatchResult.TIE),
                matchResults.getResultCount(MatchResult.PLAYER_WIN)
        );
    }

    private void showPlayersResult(Players players, MatchResults matchResults) {
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            MatchResult result = matchResults.getResultByName(playerName);
            outputView.printPlayerResult(playerName, result);
        }
    }
}

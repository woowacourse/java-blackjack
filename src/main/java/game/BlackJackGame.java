package game;

import card.Deck;
import player.Dealer;
import player.Player;
import player.Players;
import view.InputView;
import view.OutputView;

import java.util.List;

public class BlackJackGame {

    public static final int BLACKJACK_MAX_SCORE = 21;

    private final InputView inputView;
    private final OutputView outputView;
    private final MatchResults matchResults;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        matchResults = new MatchResults();
    }

    public void play() {
        Deck deck = new Deck();
        Dealer dealer = new Dealer();

        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        outputView.printNewLine();

        players.initDrawCards(deck);
        dealer.initDrawCards(deck);
        outputView.printInitializeBlackJack(players.getNames());
        outputView.printDealerFirstCard(dealer.getFirstCard());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
        outputView.printNewLine();

        for (Player player : players.getPlayers()) {
            while (player.hasDrawableScore()) {
                outputView.printDrawMoreCardRequest(player.getName());
                String input = inputView.readCommand();
                Command command = Command.from(input);
                if (command == Command.NO) {
                    break;
                }
                player.drawCard(deck);
                outputView.printPlayerCards(player.getName(), player.getCards());
            }
        }
        outputView.printNewLine();

        while (dealer.hasDrawableScore()) {
            dealer.drawCard(deck);
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }


        outputView.printDealerCardsWithResult(dealer.getCards(), dealer.getScore());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerCardsWithResult(player.getName(), player.getCards(), player.getScore());
        }
        for (Player player : players.getPlayers()) {
            matchResults.addResult(player.getName(), player.getScore(), dealer.getScore());
        }
        outputView.printNewLine();

        outputView.printResultStart();
        outputView.printDealerResult(
                matchResults.getResultCount(MatchResult.DEALER_WIN),
                matchResults.getResultCount(MatchResult.TIE),
                matchResults.getResultCount(MatchResult.PLAYER_WIN)
        );
        for (Player player : players.getPlayers()) {
            String playerName = player.getName();
            outputView.printPlayerResult(playerName, matchResults.getResultByName(playerName));
        }
    }
}

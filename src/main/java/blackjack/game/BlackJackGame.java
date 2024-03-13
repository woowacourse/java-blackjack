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
    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = Deck.createShuffledFullDeck();
        Players players = createPlayers();
        Dealer dealer = new Dealer();

        initGame(deck, dealer, players);
        drawMoreForPlayers(deck, players);
        drawMoreForDealer(deck, dealer);

        showCardsWithScore(dealer, players);
        showMatchResult(dealer, players);
    }

    private Players createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = Players.create(names);
        outputView.printNewLine();
        return players;
    }

    private void initGame(Deck deck, Dealer dealer, Players players) {
        players.drawCardsForAll(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        dealer.drawCards(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        outputView.printInitializeBlackJack(players.getNames());
        showInitCard(dealer, players);
    }

    private void showInitCard(Dealer dealer, Players players) {
        outputView.printDealerFirstCard(dealer.revealCardsOnFirstPhase());

        for (Player player : players.getPlayers()) {
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
        outputView.printNewLine();
    }

    private void drawMoreForPlayers(Deck deck, Players players) {
        for (Player player : players.getPlayers()) {
            drawMoreForPlayer(deck, player);
        }
        outputView.printNewLine();
    }

    private void drawMoreForPlayer(Deck deck, Player player) {
        while (player.hasDrawableScore() && isPlayerWantsToDrawMore(player)) {
            player.drawCard(deck.draw());
            outputView.printPlayerCards(player.getName(), player.getCards());
        }
    }

    private boolean isPlayerWantsToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
        return inputView.isReadCommandYes();
    }

    private void drawMoreForDealer(Deck deck, Dealer dealer) {
        while (dealer.hasDrawableScore()) {
            dealer.drawCard(deck.draw());
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

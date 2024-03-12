package blackjack.game;

import blackjack.card.Card;
import blackjack.card.Deck;
import blackjack.money.BetMoney;
import blackjack.money.PlayerBet;
import blackjack.money.PlayerBets;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.view.Command;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {
    private static final int BLACKJACK_INIT_CARD_AMOUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        Deck deck = Deck.createShuffledFullDeck();
        Player dealer = Player.createAsDealer();

        Players players = createPlayers();
        PlayerBets playerBets = createPlayerBets(players);

        initGame(deck, dealer, players);
        playersDrawMore(deck, players);
        dealerDrawMore(deck, dealer);

        showResults(dealer, players, playerBets);
    }

    private MatchResults createMatchResults(Player dealer, Players players) {
        MatchResults matchResults = new MatchResults();
        for (Player player : players.getPlayers()) {
            matchResults.addResult(player.getName(), player, dealer);
        }
        return matchResults;
    }

    private PlayerBets createPlayerBets(Players players) {
        PlayerBets playerBets = new PlayerBets();
        for (String name : players.getNames()) {
            outputView.printBetRequest(name);
            int amount = inputView.readBetMoney();
            playerBets.addPlayerBet(name, BetMoney.of(amount));
        }
        return playerBets;
    }

    private Players createPlayers() {
        outputView.printNamesRequest();
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        outputView.printNewLine();
        return players;
    }

    private void initGame(Deck deck, Player dealer, Players players) {
        players.drawCardsForAll(deck, BLACKJACK_INIT_CARD_AMOUNT);
        dealer.drawCards(deck::draw, BLACKJACK_INIT_CARD_AMOUNT);
        outputView.printInitializeBlackJack(players.getNames());
        showInitCard(dealer, players);
    }

    private void showInitCard(Player dealer, Players players) {
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
        if (!player.isDrawable()) {
            return;
        }
        Command command = askPlayerToDrawMore(player);
        if (command.isNo()) {
            player.stand();
            return;
        }
        drawSingleCard(player, deck);

        if (player.isDrawable()) {
            playerDrawMore(deck, player);
        }
    }

    private void drawSingleCard(Player player, Deck deck) {
        Card card = deck.draw();
        player.drawCard(card);
        outputView.printPlayerCards(player.getName(), player.getCards());
    }

    private Command askPlayerToDrawMore(Player player) {
        outputView.printDrawMoreCardRequest(player.getName());
        String input = inputView.readCommand();
        return Command.from(input);
    }

    private void dealerDrawMore(Deck deck, Player dealer) {
        while (dealer.isDrawable()) {
            Card card = deck.draw();
            dealer.drawCard(card);
            outputView.printDealerDrawCard();
            outputView.printNewLine();
        }
    }

    private void showResults(Player dealer, Players players, PlayerBets playerBets) {
        MatchResults matchResults = createMatchResults(dealer, players);
        showCardsWithScore(dealer, players);
        showProfitResult(dealer, playerBets, matchResults);
    }

    private void showCardsWithScore(Player dealer, Players players) {
        outputView.printDealerCardsWithScore(dealer.getCards(), dealer.getScore());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerCardsWithScore(player.getName(), player.getCards(), player.getScore());
        }
        outputView.printNewLine();
    }

    private void showProfitResult(Player dealer, PlayerBets playerBets, MatchResults matchResults) {
        outputView.printResultStart();
        showDealerProfit(dealer, playerBets, matchResults);
        showPlayersResult(playerBets, matchResults);
    }

    private void showDealerProfit(Player dealer, PlayerBets playerBets, MatchResults matchResults) {
        int playersProfit = playerBets.stream()
                .mapToInt(matchResults::calculateProfitByBet)
                .sum();
        int dealerProfit = playersProfit * -1;

        outputView.printPlayerResult(dealer.getName(), dealerProfit);
    }

    private void showPlayersResult(PlayerBets playerBets, MatchResults matchResults) {
        playerBets.stream()
                .forEach(bet -> showSingleResult(matchResults, bet));
    }

    private void showSingleResult(MatchResults matchResults, PlayerBet playerBet) {
        int profit = matchResults.calculateProfitByBet(playerBet);
        outputView.printPlayerResult(playerBet.name(), profit);
    }
}

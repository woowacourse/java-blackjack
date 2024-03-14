package controller;

import domain.BlackJackGame;
import domain.HitOption;
import domain.cards.Card;
import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.gamer.bet.BetAmount;
import domain.gamer.bet.PlayerBet;
import domain.result.Cashier;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGameController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void gameStart() {
        Players players = new Players(generatePlayers(inputView.readPlayersNames()));
        Dealer dealer = new Dealer();
        Cashier cashier = new Cashier(generateCashier(players));
        Deck deck = Deck.createShuffledDeck();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        configureSetup(blackJackGame, players, dealer, deck);
        progressGame(blackJackGame, players, dealer, deck);
        makeFinalResult(blackJackGame, dealer, cashier);
    }

    private List<Player> generatePlayers(List<String> playersNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playersNames) {
            players.add(new Player(playerName));
        }
        return players;
    }

    private List<PlayerBet> generateCashier(Players players) {
        List<PlayerBet> playerBets = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            BetAmount betAmount = new BetAmount(inputView.readPlayerBet(player));
            playerBets.add(new PlayerBet(player, betAmount));
        }
        return playerBets;
    }

    private void configureSetup(BlackJackGame blackJackGame, Players players, Dealer dealer, Deck deck) {
        blackJackGame.shareInitCards(deck);
        resultView.printInitialCards(dealer, players.getPlayers());
    }

    private void progressGame(BlackJackGame blackJackGame, Players players, Dealer dealer, Deck deck) {
        progressPlayersGame(blackJackGame, players, deck);
        progressDealerGame(blackJackGame, dealer, deck);
        resultView.printAllGamersCardsResult(dealer, players);
    }

    private void progressPlayersGame(BlackJackGame blackJackGame, Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            progressPlayerGame(blackJackGame, deck, player);
        }
    }

    private void progressPlayerGame(BlackJackGame blackJackGame, Deck deck, Player player) {
        HitOption hitOption = new HitOption();
        while (blackJackGame.canPlayerMoreHit(player, hitOption)) {
            hitOption = new HitOption(inputView.readHitOrNot(player));
            doHitByOption(blackJackGame, deck, player, hitOption);
        }
    }

    private void doHitByOption(BlackJackGame blackJackGame, Deck deck, Player player, HitOption hitOption) {
        if (hitOption.doHit()) {
            blackJackGame.allowHit(player, deck);
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(BlackJackGame blackJackGame, Dealer dealer, Deck deck) {
        while (blackJackGame.canDealerMoreHit(dealer)) {
            Card hitedCard = blackJackGame.allowHit(dealer, deck);
            resultView.printDealerHitMessage(dealer, hitedCard);
        }
    }

    private void makeFinalResult(BlackJackGame blackJackGame, Dealer dealer, Cashier cashier) {
        Map<Player, BetAmount> result = blackJackGame.makeResultOfGame(cashier);
        resultView.printFinalProfit(result, dealer);
    }
}

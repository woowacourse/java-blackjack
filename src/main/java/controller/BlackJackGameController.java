package controller;

import domain.BlackJackGame;
import domain.HitOption;
import domain.cards.Card;
import domain.cards.CardsGenerator;
import domain.cards.Deck;
import domain.cards.gamercards.DealerCards;
import domain.cards.gamercards.PlayerCards;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import domain.judge.Judge;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void gameStart() {
        Players players = new Players(generatePlayers(inputView.readPlayersNames()));
        Dealer dealer = new Dealer(new DealerCards(new ArrayList<>()));
        Deck deck = new Deck(CardsGenerator.generateRandomCards());
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer);

        configureSetup(blackJackGame, players, dealer, deck);
        progressGame(blackJackGame, players, dealer, deck);

        makeFinalResult(players, dealer);
    }

    private List<Player> generatePlayers(List<String> playersNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playersNames) {
            PlayerCards emptyHand = new PlayerCards(new ArrayList<>());
            players.add(new Player(playerName, emptyHand));
        }
        return players;
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
            hitOption.makeNewOption(inputView.readHitOrNot(player));
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

    private void makeFinalResult(Players players, Dealer dealer) {
        Judge judge = new Judge();
        judge.decideResult(players, dealer);
        resultView.printFinalResults(dealer, judge);
    }
}

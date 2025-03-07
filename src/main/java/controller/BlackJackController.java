package controller;

import domain.Card;
import domain.CardDeck;
import domain.CardShuffler;
import domain.Dealer;
import domain.GameResult;
import domain.Player;
import domain.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = inputView.readPlayerNames();
        Players players = new Players(playerNames);
        Dealer dealer = new Dealer();

        CardShuffler cardShuffler = new CardShuffler();
        CardDeck cardDeck = CardDeck.createCards(cardShuffler);

        startBlackJack(cardDeck, players, dealer);
        playBlackJack(cardDeck, players, dealer);
        judgeGameResult(players, dealer);
    }

    private void startBlackJack(CardDeck cardDeck, Players players, Dealer dealer) {
        players.drawCardWhenStart(cardDeck);
        List<Card> dealerCard = dealer.drawCardWhenStart(cardDeck);

        outputView.printInitialGame(dealerCard, players.getPlayers());
    }

    private void playBlackJack(CardDeck cardDeck, Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            decidePlayerHitOrStand(cardDeck, player);
        }
        decideDealerHitOrStand(cardDeck, dealer);
        outputView.printGameResult(dealer, players);
    }

    private void decidePlayerHitOrStand(CardDeck cardDeck, Player player) {
        while (!player.isOverBurstBound() && inputView.readDrawMoreCard(player)) {
            player.drawCard(cardDeck);
            outputView.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(CardDeck cardDeck, Dealer dealer) {
        while (!dealer.isOverBurstBound() && !dealer.isOverDrawBound()) {
            dealer.drawCard(cardDeck);
            outputView.printDealerDrawMessage();
        }
    }

    private void judgeGameResult(Players players, Dealer dealer) {
        List<GameResult> gameResults = dealer.judgeGameResult(players.getPlayers());
        List<String> playerNames = players.getAllPlayerNames();

        int winCount = GameResult.WIN.countGameResult(gameResults);
        int loseCount = GameResult.LOSE.countGameResult(gameResults);
        int drawCount = GameResult.DRAW.countGameResult(gameResults);

        outputView.printDealerWinningResult(winCount, drawCount, loseCount);
        outputView.printWinningResult(playerNames, gameResults);
    }
}

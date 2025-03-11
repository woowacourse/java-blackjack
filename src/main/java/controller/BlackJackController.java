package controller;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardShuffler;
import domain.game.Dealer;
import domain.game.GameResult;
import domain.game.Player;
import domain.game.Players;
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
        CardShuffler cardShuffler = new CardShuffler();
        CardDeck cardDeck = CardDeck.createCards(cardShuffler);

        List<String> playerNames = inputView.readPlayerNames();
        Players players = new Players(playerNames, cardDeck);
        Dealer dealer = new Dealer(cardDeck);

        startBlackJack(players, dealer);
        playBlackJack(players, dealer);
        judgeGameResult(players, dealer);
    }

    private void startBlackJack(Players players, Dealer dealer) {
        players.drawCard(DRAW_COUNT_WHEN_START);
        dealer.drawCard(DRAW_COUNT_WHEN_START);
        Card dealerCard = dealer.getSingleCard();

        outputView.printInitialGame(dealerCard, players.getPlayers());
    }

    private void playBlackJack(Players players, Dealer dealer) {
        for (Player player : players.getPlayers()) {
            decidePlayerHitOrStand(player);
        }
        decideDealerHitOrStand(dealer);
        outputView.printGameResult(dealer, players);
    }

    private void decidePlayerHitOrStand(Player player) {
        while (!player.isOverBustBound() && inputView.readDrawMoreCard(player)) {
            player.drawCard(DRAW_COUNT_WHEN_HIT);
            outputView.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(Dealer dealer) {
        while (!dealer.isOverBustBound() && !dealer.isOverDrawBound()) {
            dealer.drawCard(DRAW_COUNT_WHEN_HIT);
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

package controller;

import static domain.card.CardDeck.DRAW_COUNT_WHEN_HIT;
import static domain.card.CardDeck.DRAW_COUNT_WHEN_START;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.CardShuffler;
import domain.card.RandomCardShuffler;
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
        CardShuffler randomCardShuffler = new RandomCardShuffler();
        CardDeck cardDeck = CardDeck.createCards(randomCardShuffler);

        List<String> playerNames = inputView.readPlayerNames();
        Players players = new Players(playerNames);
        Dealer dealer = new Dealer();

        startBlackJack(players, dealer, cardDeck);
        playBlackJack(players, dealer, cardDeck);
        judgeGameResult(players, dealer);
    }

    private void startBlackJack(Players players, Dealer dealer, CardDeck cardDeck) {
        players.drawCard(cardDeck);
        dealer.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_START));
        Card dealerCard = dealer.getSingleCard();

        outputView.printInitialGame(dealerCard, players.getPlayers());
    }

    private void playBlackJack(Players players, Dealer dealer, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            decidePlayerHitOrStand(player, cardDeck);
        }
        decideDealerHitOrStand(dealer, cardDeck);
        outputView.printGameResult(dealer, players);
    }

    private void decidePlayerHitOrStand(Player player, CardDeck cardDeck) {
        while (!player.isOverBustBound() && inputView.readDrawMoreCard(player)) {
            player.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputView.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(Dealer dealer, CardDeck cardDeck) {
        while (!dealer.isOverBustBound() && !dealer.isOverDrawBound()) {
            dealer.drawCard(cardDeck.drawCard(DRAW_COUNT_WHEN_HIT));
            outputView.printDealerDrawMessage();
        }
    }

    private void judgeGameResult(Players players, Dealer dealer) {
        List<GameResult> gameResults = players.judgeGameResult(dealer);
        List<String> playerNames = players.getAllPlayerNames();

        int winCount = GameResult.WIN.countReversedGameResult(gameResults);
        int loseCount = GameResult.LOSE.countReversedGameResult(gameResults);
        int drawCount = GameResult.DRAW.countReversedGameResult(gameResults);

        outputView.printDealerWinningResult(winCount, drawCount, loseCount);
        outputView.printWinningResult(playerNames, gameResults);
    }
}

package controller;

import domain.card.Card;
import domain.card.CardDeck;
import domain.game.BlackjackResultEvaluator;
import domain.game.Dealer;
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
        List<String> playerNames = inputView.readPlayerNames();
        List<Integer> playerBettingAmount = readPlayerBettingAmount(playerNames);
        Players players = new Players(playerNames, playerBettingAmount);
        BlackjackResultEvaluator blackjackResultEvaluator = new BlackjackResultEvaluator();
        Dealer dealer = new Dealer();

        CardDeck cardDeck = CardDeck.createCards();

        startBlackJack(cardDeck, players, dealer);
        playBlackJack(cardDeck, players, dealer);
        blackjackResultEvaluator.judgeGameResult(players.getPlayers(), dealer);
        calculateTotalBettingAmount(players, dealer);
    }

    private List<Integer> readPlayerBettingAmount(List<String> playerNames) {
        return playerNames.stream()
                .map(inputView::readPlayerBettingAmount)
                .toList();
    }

    private void startBlackJack(CardDeck cardDeck, Players players, Dealer dealer) {
        players.drawCardWhenStart(cardDeck);
        dealer.drawCardWhenStart(cardDeck);
        Card dealerCard = dealer.openSingleCard();

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
        while (player.isUnderBurstBound() && inputView.readDrawMoreCard(player)) {
            player.drawCard(cardDeck);
            outputView.printPlayerCard(player);
        }
    }

    private void decideDealerHitOrStand(CardDeck cardDeck, Dealer dealer) {
        while (dealer.isUnderDrawBound()) {
            dealer.drawCard(cardDeck);
            outputView.printDealerDrawMessage();
        }
    }

    private void calculateTotalBettingAmount(Players players, Dealer dealer) {
        outputView.printBettingAmount(players.getPlayers(), dealer);
    }
}

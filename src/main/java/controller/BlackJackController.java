package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import view.InputView;
import view.OutputView;

public final class BlackJackController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Deck deck = Deck.initDeck(Card.values());
        Players players = createPlayers();
        Dealer dealer = new Dealer();
        handOverTwoCards(players, dealer, deck);
        hitOrStayByPlayers(players, deck);
        hitOrStayByDealer(dealer, deck);
        showFinalScore(players, dealer);
        showResult(players, dealer);
    }

    private Players createPlayers() {
        try {
            List<String> playerNames = inputView.inputPlayerName();
            return Players.of(inputView.inputBettings(playerNames));
        } catch (IllegalArgumentException exception) {
            outputView.printError(exception.getMessage());
            return createPlayers();
        }
    }

    private void handOverTwoCards(Players players, Dealer dealer, Deck deck) {
        players.runInitialTurn(deck);
        dealer.receiveInitialTwoCards(deck);
        outputView.showInitialTurnStatus(players, dealer);
    }


    private void hitOrStayByPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitOrStayByPlayer(player, deck);
        }
    }

    private void hitOrStayByPlayer(Player player, Deck deck) {
        while (player.canHit() && inputView.inputHitOrStay(player.getName())) {
            player.hit(deck);
            outputView.showPlayerCardStatus(player);
        }
    }

    private void hitOrStayByDealer(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            dealer.hit(deck);
            outputView.showDealerHitCardMessage();
        }
    }

    private void showFinalScore(Players players, Dealer dealer) {
        outputView.showFinalTurnStatus(players, dealer);
    }

    private void showResult(Players players, Dealer dealer) {
        List<Integer> playerIncomes = players.calculateIncomes(dealer);
        outputView.showResult(dealer.getName(), dealer.calculateIncome(playerIncomes),
            players.toNames(), playerIncomes);
    }
}

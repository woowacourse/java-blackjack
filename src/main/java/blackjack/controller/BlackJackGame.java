package blackjack.controller;

import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGame {

    private static final int INIT_DRAW_COUNT = 2;
    private static final String YES = "y";

    public void start() {
        Players players = registerPlayers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffleCard();

        firstDraw(players, dealer, cardDeck);
        playerTurn(players, cardDeck);
        dealerTurn(dealer, cardDeck);
        showResult(players, dealer);
    }

    private Players registerPlayers() {
        try {
            OutputView.enterPlayersName();
            return new Players(InputView.inputName());
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return registerPlayers();
        }
    }

    private void firstDraw(Players players, Dealer dealer, CardDeck cardDeck) {
        eachDrawTwoCards(players, dealer, cardDeck);
        OutputView.distributeCardMessage(players);
        OutputView.showDealerFirstCard(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.showCards(player);
        }
    }

    private void eachDrawTwoCards(Players players, Dealer dealer, CardDeck cardDeck) {
        for (int i = 0; i < INIT_DRAW_COUNT; i++) {
            dealer.receiveCard(cardDeck.drawCard());
            players.getPlayers()
                   .forEach(player -> player.receiveCard(cardDeck.drawCard()));
        }
    }

    private void playerTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            askDraw(player, cardDeck);
        }
    }

    private void askDraw(Player player, CardDeck cardDeck) {
        while (playerCanDraw(player)) {
            player.receiveCard(cardDeck.drawCard());
            OutputView.showCards(player);
        }
    }

    private boolean playerCanDraw(Player player) {
        if (!player.canDraw()) {
            return false;
        }
        OutputView.askOneMoreCard(player);
        if (InputView.inputAnswer().equals(YES)) {
            return true;
        }
        OutputView.showCards(player);
        return false;
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.receiveCard(cardDeck.drawCard());
            OutputView.dealerReceiveOneCard();
        }
    }

    private void showResult(Players players, Dealer dealer) {
        OutputView.showAllCards(players, dealer);
        BlackJackResult blackJackResult = new BlackJackResult(players, dealer);
        OutputView.showFinalResult(blackJackResult);
    }
}

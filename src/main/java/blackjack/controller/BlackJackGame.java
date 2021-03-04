package blackjack.controller;

import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackGame {
    public static final int BLACKJACK_NUMBER = 21;

    private final BlackJackService blackJackService;

    public BlackJackGame(BlackJackService blackJackService) {
        this.blackJackService = blackJackService;
    }

    public void start() {
        try {
            Players players = registerPlayers();
            Dealer dealer = new Dealer();
            CardDeck cardDeck = blackJackService.cardDeckSetting();
            distributeCards(players, dealer, cardDeck);
            playersTurn(players, cardDeck);
            dealerTurn(dealer, cardDeck);
            showResult(players, dealer);
        } catch (IllegalStateException e) {
            OutputView.printError(e.getMessage());
        }
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

    private void distributeCards(Players players, Dealer dealer, CardDeck cardDeck) {
        blackJackService.eachDrawTwoCards(players, dealer, cardDeck);
        OutputView.distributeCardMessage(players);
        OutputView.showDealerFirstCard(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.showCards(player);
        }
    }

    private void playersTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(cardDeck, player);
        }
    }

    private void eachPlayerTurn(CardDeck cardDeck, Player player) {
        while (playerDrawCard(player)) {
            player.receiveCard(cardDeck.drawCard());
            OutputView.showCards(player);
        }
    }

    private boolean playerDrawCard(Player player) {
        return player.canDraw() && askDrawCard(player);
    }

    private boolean askDrawCard(Player player) {
        try {
            OutputView.askOneMoreCard(player);
            boolean isDraw = InputView.inputAnswer();
            showCurrentCard(player, isDraw);
            return isDraw;
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return askDrawCard(player);
        }
    }

    private void showCurrentCard(Player player, boolean isDraw) {
        if (!isDraw) {
            OutputView.showCards(player);
        }
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

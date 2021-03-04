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

    public void start() {
        Players players = registerPlayers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffleCard();
        firstDraw(players, dealer, cardDeck);
        playersTurn(players, cardDeck);
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
            players.eachPlayerDrawCard(cardDeck);
        }
    }

    private void playersTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            eachPlayerTurn(cardDeck, player);
        }
    }

    private void eachPlayerTurn(CardDeck cardDeck, Player player) {
        while (player.canDraw()) {
            try {
                OutputView.askOneMoreCard(player);
                String answer = InputView.inputAnswer();
                if (answer.equals("n")) {
                    OutputView.showCards(player);
                    break;
                }
                player.receiveCard(cardDeck.drawCard());
                OutputView.showCards(player);
            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
                eachPlayerTurn(cardDeck, player);
            } catch (IllegalStateException e) {
                OutputView.printError(e.getMessage());
                break;
            }
        }
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canDraw()) {
            try {
                dealer.receiveCard(cardDeck.drawCard());
                OutputView.dealerReceiveOneCard();
            } catch (IllegalStateException e) {
                OutputView.printError(e.getMessage());
                break;
            }
        }
    }

    private void showResult(Players players, Dealer dealer) {
        OutputView.showAllCards(players, dealer);
        BlackJackResult blackJackResult = new BlackJackResult(players, dealer);
        OutputView.showFinalResult(blackJackResult);
    }
}

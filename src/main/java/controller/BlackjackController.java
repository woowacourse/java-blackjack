package controller;

import domain.BettingMoney;
import domain.BlackjackUserProfit;
import domain.PlayerAnswer;
import domain.card.CardDeck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayerNames;
import domain.user.Players;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackjackController {
    private CardDeck cardDeck = new CardDeck();

    public void run() {
        PlayerNames playerNames = new PlayerNames(Arrays.asList(InputView.requestName()));
        Players players = new Players(createPlayers(playerNames));
        Dealer dealer = new Dealer();

        drawFirstCards(dealer, players);
        drawMoreCards(players);
        drawMoreCards(dealer);

        openBlackjackUserCards(dealer, players);

        displayBlackjackUserProfit(dealer, players);
    }

    private List<Player> createPlayers(PlayerNames playerNames) {
        List<Player> players = new ArrayList<>();
        for (String name : playerNames.get()) {
            String bettingMoneyInput = InputView.requestBettingMoney(name);
            Player player = new Player(name, new BettingMoney(bettingMoneyInput));
            players.add(player);
        }
        return players;
    }

    private void drawFirstCards(Dealer dealer, Players players) {
        OutputView.printFirstDrawMessage(dealer, players.get());
        dealer.firstDraw(cardDeck);
        OutputView.printFirstCardOnly(dealer.getName(), dealer.getCards());
        for (Player player : players.get()) {
            player.firstDraw(cardDeck);
            OutputView.printCards(player.getName(), player.getCards());
        }
    }

    private void drawMoreCards(Dealer dealer) {
        while (dealer.isDrawable()) {
            OutputView.printDealerDraw(dealer.getName());
            dealer.draw(cardDeck);
        }
    }

    private void drawMoreCards(Players players) {
        for (Player player : players.get()) {
            while (player.isDrawable() && isAgreePlayerDraw(player)) {
                player.draw(cardDeck);
                OutputView.printCards(player.getName(), player.getCards());
            }
        }
    }

    private boolean isAgreePlayerDraw(Player player) {
        OutputView.printCardFormat(player.getName());
        PlayerAnswer playerAnswer = PlayerAnswer.of(InputView.requestDraw());
        return playerAnswer.isYes();
    }

    private void openBlackjackUserCards(Dealer dealer, Players players) {
        OutputView.printCardsWithScore(dealer.getName(), dealer.getCards(), dealer.getScore());
        for (Player player : players.get()) {
            OutputView.printCardsWithScore(player.getName(), player.getCards(), player.getScore());
        }
    }

    private void displayBlackjackUserProfit(Dealer dealer, Players players) {
        BlackjackUserProfit blackjackUserProfit = new BlackjackUserProfit(players, dealer);
        OutputView.printBlackjackUserProfit(blackjackUserProfit, dealer.getName());
    }
}
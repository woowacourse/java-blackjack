package blackjack.controller;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    public void run() {
        final CardDeck cardDeck = new CardDeck();
        final Dealer dealer = new Dealer("딜러");
        final List<Player> players = playerSetUp();

        distributeCard(players, dealer, cardDeck);
        showDistributeStatus(players);
        showDistributedCard(players, dealer);
        playerGameProgress(players, cardDeck);
        dealerGameProgress(dealer, cardDeck);
        showFinalCardResult(players, dealer);
        showGameResult(players, dealer);
    }

    private List<Player> playerSetUp() {
        final List<String> names = InputView.requestName();
        final List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        return players;
    }

    private void distributeCard(final List<Player> players, final Dealer dealer, final CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveCard(cardDeck.distribute());
            player.receiveCard(cardDeck.distribute());
        }
        dealer.receiveCard(cardDeck.distribute());
        dealer.receiveCard(cardDeck.distribute());
    }

    private void showDistributeStatus(final List<Player> players) {
        String status = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        OutputView.distributeMessage(status);
    }

    private void showDistributedCard(final List<Player> players, final Dealer dealer) {
        OutputView.showDealerCard(dealer.getName(), dealer.getMyCards().get(0));
        for (final Player player : players) {
            OutputView.showPlayerCard(player.getName(), player.getMyCards());
        }
    }

    private void playerGameProgress(final List<Player> players, final CardDeck cardDeck) {
        for (Player player : players) {
            while (true) {
                String choice = InputView.askMoreCard(player.getName());
                if ("n".equals(choice)) {
                    OutputView.showPlayerCard(player.getName(), player.getMyCards());
                    break;
                }
                player.receiveCard(cardDeck.distribute());
                OutputView.showPlayerCard(player.getName(), player.getMyCards());

                if (player.isBust()) {
                    System.out.println("카드의 합이 21을 넘어, 게임에서 패배하였습니다.");
                    break;
                }
            }
        }
    }

    private void dealerGameProgress(final Dealer dealer, final CardDeck cardDeck) {
        while (dealer.checkMoreCardAvailable()) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.receiveCard(cardDeck.distribute());
        }
    }

    private void showFinalCardResult(final List<Player> players, final Dealer dealer) {
        OutputView.showCardResult(dealer.getName(), dealer.getMyCards(), dealer.calculate());
        for (Player player : players) {
            OutputView.showCardResult(player.getName(), player.getMyCards(), player.calculate());
        }
    }

    private void showGameResult(final List<Player> players, final Dealer dealer) {
        int winCount = 0;
        for (final Player player : players) {
            if (dealer.isWinner(player.calculate()) || player.isBust()) {
                winCount++;
                player.lose();
            }
        }
        OutputView.showGameResult(dealer.getName(), winCount, players.size() - winCount);
        for (final Player player : players) {
            OutputView.showPlayerGameResult(player.getName(), player.getWin());
        }
    }
}

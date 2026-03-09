package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.CardsOfPlayer;
import blackjack.dto.WinningResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Players players = readPlayers();
        Player dealer = new Player("딜러");

        setInitialTwoCards(players, dealer);
        printInitialSettings(players, dealer);

        getMoreCards(players);
        getMoreCardsForDealer(dealer, players);

        printGameResult(players, dealer);
        printWinningResult(players, dealer);
    }

    private Players readPlayers() {
        List<String> playersName = inputView.readPlayersName();
        return Players.from(playersName);
    }

    private void setInitialTwoCards(Players players, Player dealer) {
        Deck.shuffle();
        for (int i = 0; i < 2; i++) {
            players.draw();
            dealer.draw(Deck.top());
        }
    }

    private void printInitialSettings(Players players, Player dealer) {
        OutputView.printInitialSettingsDoneMessage(dealer.getName(), players.getPlayersName());
        OutputView.printSettingResultsByPlayer(CardsOfPlayer.from(players, dealer));
    }

    // TODO : 코드 품질 개선 필요
    private void getMoreCards(Players players) {
        for (Player player : players.getPlayers()) {
            int count = 0;
            while (!player.isBurst() && !player.isBlackjack()) {
                String yesOrNo = inputView.readMoreCard(player.getName());
                if (yesOrNo.equals("y")) {
                    player.draw(Deck.top());
                    OutputView.printSettingResults(player.getName(), player.getCardsName());
                    count++;
                    continue;
                }
                if (count == 0) {
                    OutputView.printSettingResults(player.getName(), player.getCardsName());
                }
                break;
            }
        }
    }

    private void getMoreCardsForDealer(Player dealer, Players players) {
        if (players.isAllPlayersBurst()) {
            return;
        }
        while (dealer.calculateCardsValue() < 17) {
            dealer.draw(Deck.top());
            OutputView.printGetMoreCardsForDealer(dealer.getName());
        }
    }

    // TODO: 총합 계산 로직 필요
    private void printGameResult(Players players, Player dealer) {
        OutputView.printSettingResultsByPlayer(CardsOfPlayer.from(players, dealer));
    }

    private void printWinningResult(Players players, Player dealer) {
        OutputView.printWinningResult(WinningResult.from(players, dealer));
    }

}

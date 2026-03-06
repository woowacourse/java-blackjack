package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.service.GameService;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final GameService gameService;
    private final InputView inputView;

    public BlackjackController(GameService gameService, InputView inputView) {
        this.gameService = gameService;
        this.inputView = inputView;
    }

    public void run() {
        List<Player> players = readUsers();
        Dealer dealer = new Dealer();

        gameService.settingCards(players, dealer);
        printGameSettingResult(players, dealer);

        getMoreCards(players, dealer);
        if (!isAllUserBurst(players)) {
            getMoreCardsForDealer(dealer);
        }

        printGameResult(players, dealer);

        printWinningResult(players, dealer);
    }

    private void printGameSettingResult(List<Player> players, Dealer dealer) {
        OutputView.printSettingCardsResult(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (Player player : players) {
            OutputView.printSettingCardsResult(player.getName(), player.getCardsName());
        }
    }

    private List<Player> readUsers() {
        String userName = inputView.readUserName();
        return InputParser.createUser(userName);
    }

    // TODO : 코드 품질 개선 필요
    private void getMoreCards(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            int count = 0;
            while (!player.isBurst() && !player.isBlackjack()) {
                String yesOrNo = inputView.readMoreCard(player.getName());
                if (yesOrNo.equals("y")) {
                    gameService.getMoreCard(player, dealer);

                    OutputView.printSettingCardsResult(player.getName(), player.getCardsName());
                    count++;

                    continue;
                }
                if (count == 0) {
                    OutputView.printSettingCardsResult(player.getName(), player.getCardsName());
                }
                break;
            }
        }
    }

    private void getMoreCardsForDealer(Dealer dealer) {
        while (dealer.calculateCardsValue() < 17) {
            OutputView.printGetMoreCardsForDealer(dealer.getName());
            gameService.getMoreCardForDealer(dealer);
        }
    }

    private boolean isAllUserBurst(List<Player> players) {
        int burstUserCount = (int) players.stream()
                .filter(Player::isBurst)
                .count();
        return players.size() == burstUserCount;
    }

    private void printGameResult(List<Player> players, Dealer dealer) {
        OutputView.printCardsResult(dealer.getName(), dealer.getCardsName(), dealer.calculateCardsValue());
        for (Player player : players) {
            OutputView.printCardsResult(player.getName(), player.getCardsName(), player.calculateCardsValue());
        }
    }

    private void printWinningResult(List<Player> players, Dealer dealer) {
        Map<String, Boolean> result = new HashMap<>();
        int dealerWinCount = 0;
        for (Player player : players) {
            boolean isDealerWinning = gameService.isDealerWinning(player, dealer);
            result.put(player.getName(), !isDealerWinning);
            if (isDealerWinning) {
                dealerWinCount++;
            }
        }

        OutputView.printWinningResult(result, dealer.getName(), dealerWinCount);
    }

}

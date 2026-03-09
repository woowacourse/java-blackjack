package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.util.InputParser;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;

    public BlackjackController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        List<Player> players = readUsers();
        Dealer dealer = new Dealer();

        settingCards(players, dealer);
        printGameSettingResult(players, dealer);

        getMoreCards(players);
        getMoreCardsForDealer(dealer, players);

        printGameResult(List.of(dealer));
        printGameResult(players);

        printWinningResult(players, dealer);
    }

    private List<Player> readUsers() {
        String userName = inputView.readUserName();
        return InputParser.createUser(userName);
    }

    // TODO: 가독성 향상 필요
    private void settingCards(List<Player> players, Dealer dealer) {
        Deck.shuffle();
        for (int i = 0; i < 2; i++) {
            for (Player player : players) {
                player.draw(Deck.top());
            }
            dealer.draw(Deck.top());
        }
    }

    private void printGameSettingResult(List<Player> players, Dealer dealer) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .toList();

        OutputView.printGameSettingDoneMessage(dealer.getName(), playerNames);

        Map<String, List<String>> cardsResult = new HashMap<>();
        cardsResult.put(dealer.getName(), dealer.getCardsName().subList(0, 1));
        for (Player player : players) {
            cardsResult.put(player.getName(), player.getCardsName());
        }

        OutputView.printSettingCardResultsByPlayer(cardsResult);
    }

    // TODO : 코드 품질 개선 필요
    private void getMoreCards(List<Player> players) {
        for (Player player : players) {
            int count = 0;
            while (!player.isBurst() && !player.isBlackjack()) {
                String yesOrNo = inputView.readMoreCard(player.getName());
                if (yesOrNo.equals("y")) {
                    player.draw(Deck.top());
                    OutputView.printSettingCardResults(player.getName(), player.getCardsName());
                    count++;
                    continue;
                }
                if (count == 0) {
                    OutputView.printSettingCardResults(player.getName(), player.getCardsName());
                }
                break;
            }
        }
    }

    // TODO: Player를 일급컬랙션으로 감싸 상태 확인 로직을 숨긴다
    private boolean isAllUserBurst(List<Player> players) {
        int burstUserCount = (int) players.stream()
                .filter(Player::isBurst)
                .count();
        return players.size() == burstUserCount;
    }

    private void getMoreCardsForDealer(Dealer dealer, List<Player> players) {
        if (isAllUserBurst(players)) {
            return;
        }
        while (dealer.calculateCardsValue() < 17) {
            dealer.draw(Deck.top());
            OutputView.printGetMoreCardsForDealer(dealer.getName());
        }
    }

    private void printGameResult(List<Player> players) {
        for (Player player : players) {
            OutputView.printCardResult(player.getName(), player.getCardsName(), player.calculateCardsValue());
        }
    }

    private void printWinningResult(List<Player> players, Dealer dealer) {
        Map<String, Boolean> result = new HashMap<>();
        int dealerWinCount = 0;
        for (Player player : players) {
            boolean isDealerWinning = dealer.winsAgainst(player);
            result.put(player.getName(), !isDealerWinning);
            if (isDealerWinning) {
                dealerWinCount++;
            }
        }

        OutputView.printWinningResult(result, dealer.getName(), dealerWinCount);
    }

}

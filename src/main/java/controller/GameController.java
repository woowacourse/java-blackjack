package controller;

import domain.*;

import java.util.*;

import domain.card.Card;
import util.HitOption;
import util.InputHitOptionParser;
import util.InputNameParser;
import view.InputView;
import view.OutputView;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        // 시나리오
        // 1. 딜러와 플레이어를 생성한다.
        List<Player> players = participateGame(getInputPlayers());
        Dealer dealer = new Dealer();

        // 2. 딜러가 플레이어에게 카드 2장씩 나누어준다. 이때 딜러도 받아야한다.
        // 딜러가 카드를 나눠준다.? 자연스러운 형태긴 하다.
        for (Player player : players) {
            List<Card> firstHandCards = dealer.handOutFirstHandCards();
            player.receiveFirstHandCards(firstHandCards);
        }
        dealer.receiveFirstHandCards(dealer.handOutFirstHandCards());
        printGameStart(players, dealer);

        // 3. 각 플레이어는 버스트가 되지 않는 한 계속 뽑을 수 있다.
        // 이때 히트 옵션을 계속 수행해야하며, 손패의 점수를 출력해야한다.
        receiveMoreCard(players, dealer);
        outputView.printFinalScore(dealer, players);

        // 4. 딜러는 16이하인 경우 계속 뽑는다.

        // 5. 승패를 계산한다.
        Map<String, GameResult> playerFinalResults = getPlayerFinalResults(players, dealer);
        outputView.printPlayerFinalResults(playerFinalResults);
    }

    private List<String> getInputPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return InputNameParser.parsePlayerNames(rawPlayerNames);
    }

    private List<Player> participateGame(List<String> playerNames) {
        return playerNames
                .stream()
                .map(Player::new)
                .toList();
    }

    private void printGameStart(List<Player> players, Dealer dealer) {
        List<String> playersNames = players.stream().map(Player::getName).toList();
        outputView.printStartCardMessage(playersNames);
        outputView.printDealerStartCard(dealer.getHandCards().getFirst());
        outputView.printStartCard(players);
    }

    private void receiveMoreCard(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            processRound(player, dealer);
        }

        while (dealer.isReceiveCard()) {
            dealer.receiveMoreCard(dealer.handOutMoreCard());
            outputView.printDealerReceiveCard();
        }
    }

    private Map<String, GameResult> getPlayerFinalResults(List<Player> players, Dealer dealer) {
        Map<String, GameResult> playerFinalResults = new HashMap<>();
        int dealerWinningCount = 0;
        int dealerLosingCount = 0;

        for (Player player : players) {
            GameResult gameResult = player.compareScore(dealer.calculateScore());
            playerFinalResults.put(player.getName(), gameResult);

            if (gameResult == GameResult.WIN) {
                dealerLosingCount += 1;
            }
            if (gameResult == GameResult.LOSE) {
                dealerWinningCount += 1;
            }
        }
        outputView.printDealerFinalCount(dealerWinningCount, dealerLosingCount);
        return playerFinalResults;
    }

    private void processRound(Player player, Dealer dealer) {

        HitOption hitOption = inputHitOption(player);

        if (hitOption == HitOption.YES) {
            player.receiveMoreCard(dealer.handOutMoreCard());
        }
        outputView.printCurrentHoldCard(player);

        while (hitOption == HitOption.YES && !player.isBust()) {
            hitOption = inputHitOption(player);
            if (hitOption == HitOption.NO) {
                break;
            }
            player.receiveMoreCard(dealer.handOutMoreCard());
            outputView.printCurrentHoldCard(player);
        }
    }

    private HitOption inputHitOption(Player player) {
        String rawHitOption = inputView.readHitOption(player.getName());
        return InputHitOptionParser.parseHitOption(rawHitOption);
    }
}

package controller;

import domain.Dealer;
import domain.GameResult;
import domain.Player;
import domain.card.Card;
import dto.GameScoreDTO;
import dto.ParticipantHandDTO;
import dto.GameStartDTO;
import util.HitOption;
import util.InputHitOptionParser;
import util.InputNameParser;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GameController {
    private final InputView inputView;
    private final OutputView outputView;


    public GameController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Player> players = participateGame(getInputPlayers());
        Dealer dealer = new Dealer();

        for (Player player : players) {
            List<Card> firstHandCards = dealer.dealInitialCards();
            player.receiveInitialCards(firstHandCards);
        }
        dealer.receiveInitialCards(dealer.dealInitialCards());
        outputView.printStartGame(GameStartDTO.from(players, dealer));


        receiveMoreCard(players, dealer);
        outputView.printFinalScore(GameScoreDTO.from(players, dealer));

        // 5. 승패를 계산한다.
        Map<String, GameResult> playerFinalResults = getPlayerFinalResults(players, dealer);
        outputView.printPlayerFinalResults(playerFinalResults);
    }

    private List<String> getInputPlayers() {
        String rawPlayerNames = inputView.readPlayerNames();
        return InputNameParser.parsePlayerNames(rawPlayerNames);
    }

    private List<Player> participateGame(List<String> playerNames) {
        return playerNames.stream()
                .map(Player::new)
                .toList();
    }

    private void receiveMoreCard(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            processRound(player, dealer);
        }

        while (dealer.isReceiveCard()) {
            dealer.receiveHitCard(dealer.dealHitCard());
            outputView.printDealerReceiveCard();
        }
    }

    private void processRound(Player player, Dealer dealer) {
        while (!player.isBust() && inputHitOption(player) == HitOption.YES) {
            player.receiveHitCard(dealer.dealHitCard());
            outputView.printHandCard(new ParticipantHandDTO(player, player.getHandCards()));
        }
        if (!player.isBust()) {
            outputView.printHandCard(new ParticipantHandDTO(player, player.getHandCards()));
        }
    }

    // TODO: 결과를 만드는 부분 리팩토링 필요
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


    private HitOption inputHitOption(Player player) {
        String rawHitOption = inputView.readHitOption(player.getName());
        return InputHitOptionParser.parseHitOption(rawHitOption);
    }
}

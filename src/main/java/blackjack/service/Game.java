package blackjack.service;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {

    private final CardDistributor cardDistributor;

    public Game(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void dealerDrawsCardsUntilDone(Dealer dealer, CardPicker cardPicker) {
        while (!dealer.isDealerDone()) {
            Card card = cardPicker.drawCard();
            cardDistributor.distributeCardToDealer(dealer, card);
        }
    }

    public GameResult judgeTotalGameResult(List<Player> players, Dealer dealer) {
        Map<ScoreCompareResult, Integer> dealerResult = new HashMap<>();
        Map<Player, ScoreCompareResult> playerResults = new HashMap<>();

        for (Player player : players) {
            ScoreCompareResult result = compareScore(player, dealer);
            playerResults.put(player, toPlayerResult(result));
            dealerResult.merge(toDealerKey(result), 1, Integer::sum);
        }

        return new GameResult(dealerResult, playerResults);
    }

    private ScoreCompareResult toPlayerResult(ScoreCompareResult result) {
        if (result == ScoreCompareResult.DEALER_WIN) {
            return ScoreCompareResult.PLAYER_LOSS;
        }
        return result;
    }

    private ScoreCompareResult toDealerKey(ScoreCompareResult result) {
        if (result == ScoreCompareResult.PLAYER_WIN) {
            return ScoreCompareResult.DEALER_LOSS;
        }
        return result;
    }


    public ScoreCompareResult compareScore(Player player, Dealer dealer) {
        boolean isPlayerBust = player.isBust();
        boolean isDealerBust = dealer.isBust();

        if (isPlayerBust || isDealerBust) {
            return compareScoreWhenBust(isPlayerBust);
        }

        return compareScoreWhenNotBust(player.calculateTotalScore(), dealer.calculateTotalScore());

    }

    private ScoreCompareResult compareScoreWhenBust(boolean isPlayerBust) {
        if (isPlayerBust) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PLAYER_WIN;
    }

    private ScoreCompareResult compareScoreWhenNotBust(int playerTotalScore, int dealerTotalScore) {
        if (playerTotalScore > dealerTotalScore) {
            return ScoreCompareResult.PLAYER_WIN;
        }
        if (playerTotalScore < dealerTotalScore) {
            return ScoreCompareResult.DEALER_WIN;
        }
        return ScoreCompareResult.PUSH;
    }

    public void distributeInitialCards(List<Player> players, Dealer dealer, CardPicker cardPicker) {
        for (Player player : players) {
            List<Card> cards = List.of(cardPicker.drawCard(), cardPicker.drawCard());
            cardDistributor.distributeTwoCardsToPlayer(player, cards);
        }
        List<Card> cards = List.of(cardPicker.drawCard(), cardPicker.drawCard());
        cardDistributor.distributeTwoCardsToDealer(dealer, cards);
    }

    public void processTurn(List<Player> players, CardPicker cardPicker) {
        for (Player player : players) {
            while (true) {
                String hitOrStand = InputView.askHitOrStand(player.getName());
                if (!isHit(hitOrStand)) {
                    break;
                }

                Card card = cardPicker.drawCard();

                cardDistributor.distributeCardToPlayer(player, card);

                OutputView.printDrawnCards(player.getName(), player.getCardNames());
                if (player.isBust()) {
                    break;
                }
            }
        }
    }

    private boolean isHit(String hitOrStand) {
        if (hitOrStand.equals("y")) {
            return true;
        }
        if (hitOrStand.equals("n")) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 입력입니다. y 또는 n을 입력해주세요");
    }
}

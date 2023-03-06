package blackjack.view;

import static blackjack.domain.participant.Dealer.DEALER_CAN_DRAW_SCORE;
import static blackjack.domain.participant.Dealer.INIT_CARD_COUNT;
import static java.util.stream.Collectors.joining;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDistributeCardsMessage(List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(joining(", "));

        System.out.println("딜러와 " + names + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");
    }

    public void printDealerInitCards(Card card) {
        System.out.println("딜러: " + card.combineNumberAndPattern());
    }

    public void printPlayersInitCards(List<Player> players) {
        for (Player player : players) {
            String cards = player.getCards().stream()
                    .map(Card::combineNumberAndPattern)
                    .collect(joining(", "));

            System.out.println(player.getName() + "카드: " + cards);
        }
    }

    public void printCurrentCards(Player player, List<Card> currentCards) {
        String cards = currentCards.stream()
                .map(Card::combineNumberAndPattern)
                .collect(joining(", "));

        System.out.println(player.getName() + "카드: " + cards);
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("딜러는 " + DEALER_CAN_DRAW_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerLastCards(List<Card> finalCards, int score) {
        String cards = finalCards.stream()
                .map(Card::combineNumberAndPattern)
                .collect(joining(", "));

        System.out.println("딜러 카드: " + cards + " - 결과: " + score);
    }

    public void printPlayerLastCards(List<Player> players) {
        for (Player player : players) {
            String cards = player.getCards().stream()
                    .map(Card::combineNumberAndPattern)
                    .collect(joining(", "));

            System.out.println(player.getName() + "카드: " + cards + " - 결과: " + player.calculateTotalScore());
        }
    }

    public void printGameResult(List<Integer> dealerResult, Map<Player, Result> playerResults) {
        printDealerResult(dealerResult);
        printPlayerResult(playerResults);
    }

    private void printDealerResult(List<Integer> dealerResult) {
        System.out.println("딜러: " + dealerResult.get(0) + "승 " + dealerResult.get(1) + "무 " + dealerResult.get(2) + "패 ");
    }

    private void printPlayerResult(Map<Player, Result> playerResults) {
        for (Player player : playerResults.keySet()) {
            System.out.println(player.getName() + ":" + playerResults.get(player));
        }
    }
}

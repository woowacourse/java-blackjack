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

    public void printDistributeCardsMessage(final List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(joining(", "));

        System.out.println("딜러와 " + names + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다.");
    }

    public void printDealerInitCards(final Card card) {
        System.out.println("딜러: " + card.combineNumberAndPattern());
    }

    public void printPlayersInitCards(final List<Player> players) {
        for (Player player : players) {
            String cards = player.getCards().stream()
                    .map(Card::combineNumberAndPattern)
                    .collect(joining(", "));

            System.out.println(player.getName() + "카드: " + cards);
        }
    }

    public void printCurrentCards(final Player player) {
        String cards = player.getCards().stream()
                .map(Card::combineNumberAndPattern)
                .collect(joining(", "));

        System.out.println(player.getName() + "카드: " + cards);
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("딜러는 " + DEALER_CAN_DRAW_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }

    public void printDealerLastCards(final List<Card> finalCards, final int score) {
        String cards = finalCards.stream()
                .map(Card::combineNumberAndPattern)
                .collect(joining(", "));

        System.out.println("딜러 카드: " + cards + " - 결과: " + score);
    }

    public void printPlayerLastCards(final List<Player> players) {
        for (Player player : players) {
            String cards = player.getCards().stream()
                    .map(Card::combineNumberAndPattern)
                    .collect(joining(", "));

            System.out.println(player.getName() + "카드: " + cards + " - 결과: " + player.calculateTotalScore());
        }
    }

    public void printGameResult(final List<Integer> dealerResult, final Map<Player, Result> playerResults) {
        printDealerResult(dealerResult);
        printPlayerResult(playerResults);
    }

    private void printDealerResult(final List<Integer> dealerResult) {
        System.out.println("딜러: " + dealerResult.get(0) + "승 " + dealerResult.get(1) + "무 " + dealerResult.get(2) + "패 ");
    }

    private void printPlayerResult(final Map<Player, Result> playerResults) {
        for (Player player : playerResults.keySet()) {
            System.out.println(player.getName() + ":" + playerResults.get(player));
        }
    }
}

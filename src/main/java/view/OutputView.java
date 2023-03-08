package view;

import domain.deck.Card;
import domain.game.Outcome;
import domain.player.Player;
import domain.player.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public final class OutputView {

    public void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printHitTwice(final Players players) {
        List<String> names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        String joinedName = String.join(", ", names);
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", joinedName);
    }

    public void printDealerCard(final Card card) {
        final String rank = card.getRank().getRank();
        final String suit = card.getSuit().getSuit();
        System.out.printf("딜러: %s%s%n", rank, suit);
    }

    public void printPlayerCard(final String playerName, final List<Card> cards) {
        String toStringCards = toStringCards(cards);

        System.out.printf("%s: %s%n", playerName, toStringCards);
    }

    private String toStringCards(final List<Card> cards) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        cards.forEach(card -> {
            String temp = card.getRank().getRank() + card.getSuit().getSuit();
            stringJoiner.add(temp);
        });
        return stringJoiner.toString();
    }

    public void printOneMoreCard(final String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
    }

    public void printDealerDrawCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printCardResult(final String name, final List<Card> cards, final int score) {
        System.out.printf("%s 카드: %s - 결과: %s%n", name, toStringCards(cards), score);
    }

    public void printEmptyLine() {
        System.out.println();
    }

    public void printGameResult(final EnumMap<Outcome, Integer> dealerOutcome, final Map<String, Outcome> playerOutcome) {
        System.out.println("## 최종 승패");
        printDealerResult(dealerOutcome);
        printPlayerResult(playerOutcome);
    }

    private void printDealerResult(final EnumMap<Outcome, Integer> dealerOutcome) {
        printGameEachResult(
                "딜러",
                dealerOutcome.get(Outcome.WIN),
                dealerOutcome.get(Outcome.DRAW),
                dealerOutcome.get(Outcome.LOSE)
        );
    }

    private void printPlayerResult(final Map<String, Outcome> playerOutcome) {
        playerOutcome.keySet().forEach(name ->
                printEachPlayerResult(playerOutcome, name)
        );
    }

    private void printEachPlayerResult(final Map<String, Outcome> playerOutcome, final String name) {
        if (playerOutcome.get(name).equals(Outcome.WIN)) {
            printGameEachResult(name, 1, 0, 0);
        }
        if (playerOutcome.get(name).equals(Outcome.DRAW)) {
            printGameEachResult(name, 0, 1, 0);
        }
        if (playerOutcome.get(name).equals(Outcome.LOSE)) {
            printGameEachResult(name, 0, 0, 1);
        }
    }

    private void printGameEachResult(final String playerName, final int win, final int draw, final int lose) {
        System.out.printf("%s: %s승 %s무 %s패%n", playerName, win, draw, lose);
    }
}

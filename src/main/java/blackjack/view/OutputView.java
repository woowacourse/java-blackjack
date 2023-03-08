package blackjack.view;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;
import static blackjack.domain.participant.Dealer.CAN_DRAW_SCORE;
import static blackjack.domain.participant.Participant.INIT_CARD_COUNT;
import static java.util.stream.Collectors.joining;

import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDistributeCardsMessage(final List<Player> players) {
        String names = players.stream()
                .map(Player::getName)
                .collect(joining(", "));

        System.out.println(System.lineSeparator() + "딜러와 " + names + "에게 " + INIT_CARD_COUNT + "장을 나누었습니다." + System.lineSeparator());
    }

    public void printParticipantsInitCards(final Card dealerCard, final List<Player> players) {
        printDealerInitCards(dealerCard);
        printPlayersInitCards(players);
    }

    private void printDealerInitCards(final Card card) {
        System.out.println("딜러: " + card.combineNumberAndPattern());
    }

    private void printPlayersInitCards(final List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + joiningCards(player.getCards()));
        }
        System.out.println();
    }

    public void printCurrentCards(final Player player) {
        System.out.println(player.getName() + "카드: " + joiningCards(player.getCards()));
    }

    public void printDealerDrawOneMoreCard() {
        System.out.println("[System]: " + "딜러는 " + CAN_DRAW_SCORE + "이하라 한장의 카드를 더 받았습니다." + System.lineSeparator());
    }

    public void printParticipantsLastCards(final List<Card> dealerCards, final int score, final List<Player> players) {
        printDealerLastCards(dealerCards, score);
        printPlayerLastCards(players);
    }

    private void printDealerLastCards(final List<Card> dealerCards, final int score) {
        System.out.println("딜러 카드: " + joiningCards(dealerCards) + " - 결과: " + score);
    }

    private void printPlayerLastCards(final List<Player> players) {
        for (Player player : players) {
            System.out.println(
                    player.getName() + "카드: " + joiningCards(player.getCards())
                            + " - 결과: " + player.calculateTotalScore());
        }
    }

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<Player, Result> playerResults) {
        System.out.println("---블랙잭 결과---");
        printDealerResult(dealerResult);
        printPlayerResult(playerResults);
    }

    private void printDealerResult(final Map<Result, Integer> dealerResult) {
        System.out.println("딜러: " + dealerResult.get(WIN) + "승 " + dealerResult.get(DRAW) + "무 " + dealerResult.get(LOSE) + "패 ");
    }

    private void printPlayerResult(final Map<Player, Result> playerResults) {
        for (Player player : playerResults.keySet()) {
            System.out.println(player.getName() + ":" + playerResults.get(player));
        }
    }

    private String joiningCards(final List<Card> cards) {
        return cards.stream()
                .map(Card::combineNumberAndPattern)
                .collect(joining(", "));
    }
}

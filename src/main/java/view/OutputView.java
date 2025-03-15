package view;

import domain.GameResult;
import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Map<Integer, String> DEALER_RECEIVED_CARD_COUNT = Map.of(
            1, "한장",
            2, "두장",
            3, "세장",
            4, "네장",
            5, "다섯장"
    );

    public void printDealerAndPlayersCards(final Dealer dealer, final List<Player> players) {
        final StringBuilder sb = new StringBuilder();
        final String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        final String header = String.format("딜러와 %s에게 2장을 나누었습니다.", playerNames);
        sb.append(LINE_SEPARATOR).append(header).append(LINE_SEPARATOR);
        final Card dealerCard = dealer.getCards().getFirst();
        final String dealerMassage = createCardsMessage("딜러", dealerCard);
        final String playerMessage = players.stream()
                .map(player -> createCardsMessage(player.getName(), player.getCards().toArray(new Card[0])))
                .collect(Collectors.joining(LINE_SEPARATOR));
        sb.append(dealerMassage).append(LINE_SEPARATOR);
        sb.append(playerMessage).append(LINE_SEPARATOR);
        System.out.println(sb);
    }

    public void printPlayerCards(final Player player) {
        final String playerCardsMessage = createCardsMessage(player.getName(), player.getCards().toArray(new Card[0]));
        System.out.println(playerCardsMessage);

    }

    public void printDealerReceivedCardCount(final int count) {
        System.out.println(String.format("딜러가 16이하라 %s의 카드를 더 받았습니다.", DEALER_RECEIVED_CARD_COUNT.get(count)));
    }

    public void printGamerCardsAndScore(final Dealer dealer, final List<Player> players) {
        final String messageFormat = "%s - 결과: %d";
        final String dealerMessage = String.format(messageFormat,
                createCardsMessage("딜러", dealer.getCards().toArray(new Card[0])),
                dealer.calculateScore());
        final String playerMessage = players.stream()
                .map(player -> String.format(messageFormat,
                        createCardsMessage(player.getName(), player.getCards().toArray(new Card[0])),
                        player.calculateScore()))
                .collect(Collectors.joining(LINE_SEPARATOR));
        System.out.println(LINE_SEPARATOR + dealerMessage + LINE_SEPARATOR + playerMessage);
    }

    private String createCardsMessage(final String name, final Card... cards) {
        final String cardsMessage = Arrays.stream(cards)
                .map(card -> String.format("%s%s", card.getScore().getSymbol(), card.getType().getName()))
                .collect(Collectors.joining(", "));
        return String.format("%s카드: %s", name, cardsMessage);
    }

    public void printGamerBetResult(int dealerBetResult, Map<String, Integer> playerBetResult) {
        final StringBuilder sb = new StringBuilder();
        sb.append(LINE_SEPARATOR);
        sb.append("## 최종수익").append(LINE_SEPARATOR)
                .append("딜러: ").append(dealerBetResult).append(LINE_SEPARATOR);
        playerBetResult.forEach((key, value) -> sb.append(key).append(": ").append(value).append(LINE_SEPARATOR));
        System.out.println(sb);
    }
}

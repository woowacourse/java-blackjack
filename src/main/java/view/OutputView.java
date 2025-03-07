package view;

import domain.card.Card;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void printDealerAndPlayersCards(final Dealer dealer, final List<Player> players) {
        final StringBuilder sb = new StringBuilder();
        final String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        final String header = String.format("딜러와 %s에게 2장을 나누었습니다.", playerNames);
        sb.append(LINE_SEPARATOR).append(header).append(LINE_SEPARATOR);
        final Card dealerCard = dealer.getCards().getFirst();
        final String dealerMassage = String.format("딜러카드: %s", createPlayerCardsMessage(dealerCard));
        final String playerMessage = players.stream()
                .map(player -> String.format("%s카드: %s", player.getName(),
                        createPlayerCardsMessage(player.getCards().toArray(new Card[0]))))
                .collect(Collectors.joining(LINE_SEPARATOR));
        sb.append(dealerMassage).append(LINE_SEPARATOR);
        sb.append(playerMessage).append(LINE_SEPARATOR);
        System.out.println(sb);
    }

    private String createPlayerCardsMessage(final Card... cards) {
        return Arrays.stream(cards)
                .map(card -> String.format("%s%s", card.getScore().getSymbol(), card.getType().getType().getName()))
                .collect(Collectors.joining(", "));
    }
}

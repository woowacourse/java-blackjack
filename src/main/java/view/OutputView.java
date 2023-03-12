package view;

import domain.card.Card;
import domain.user.CardPool;
import domain.user.Dealer;
import domain.user.Player;
import view.mapper.CardNumberMapper;
import view.mapper.CardTypeMapper;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printPlayersInfoWhenGameStarted(final Dealer dealer, final List<Player> players) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        players.forEach(player -> stringJoiner.add(player.getPlayerName().getName()));
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", dealer.getPlayerName().getName(), stringJoiner);

        printDealerCardWithName(dealer);
        for (Player player : players) {
            printPlayerCardWithName(player);
        }
    }

    public void printRevenueForAll(final Dealer dealer, final List<Player> players) {
        System.out.println("## 최종 수익");
        String format = "%s: %d\n";

        System.out.printf(format, dealer.getPlayerName().getName(), dealer.getRevenue());
        players.forEach(player -> System.out.printf(format, player.getPlayerName().getName(), player.getRevenue()));
    }

    private void printDealerCardWithName(final Dealer dealer) {
        System.out.println(makePlayerName(dealer) + makePlayerCardMessage(dealer.getLimitedCardPool()));
    }

    public void printPlayerCardWithName(final Player player) {
        System.out.println(makePlayerName(player) + makePlayerCardMessage(player.getCardPool()));
    }

    private String makePlayerName(final Player player) {
        return player.getPlayerName().getName();
    }

    public String makePlayerCardMessage(final CardPool cardPool) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Card card : cardPool.getCards()) {
            stringJoiner.add(CardNumberMapper.ofCardNumber(card.getNumber()) + CardTypeMapper.ofCardType(card.getType()));
        }
        return "카드: " + stringJoiner;
    }

    public void printDealerHitMessage() {
        System.out.println("딜러는 16 이하라 한장의 카드를 더 받았습니다.");
    }

    public void printGameScore(final Dealer dealer, final List<Player> players) {
        printScore(dealer);
        for (Player player : players) {
            printScore(player);
        }
    }

    private void printScore(final Player player) {
        System.out.println(makePlayerName(player) + makePlayerCardMessage(player.getCardPool()) + " - 결과 : " + player.sumCardPool());
    }

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }
}

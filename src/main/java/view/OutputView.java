package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.PlayersInfo;
import domain.user.User;
import view.dto.GameResultDto;

public class OutputView {

    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    public static void printFirstDealOutResult(Dealer dealer, PlayersInfo playersInfo) {
        printFirstDealOut(playersInfo);
        printDealerFirstDealOutResult(dealer);
        printPlayersFirstDealOutResult(playersInfo);
    }

    private static void printFirstDealOut(PlayersInfo playersInfo) {
        String allNames = playersInfo.getPlayers()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE, Dealer.NAME, allNames);
    }

    private static void printDealerFirstDealOutResult(Dealer dealer) {
        Card firstCard = dealer.getCards().get(FIRST_CARD);
        String dealerFirstDealOutResult = firstCard.getType() + firstCard.getSymbol();
        System.out.printf("%s카드: %s"+ NEWLINE, Dealer.NAME, dealerFirstDealOutResult);
    }

    private static void printPlayersFirstDealOutResult(PlayersInfo playersInfo) {
        playersInfo.getPlayers()
                .forEach(player -> System.out.println(printUserDealOutResult(player)));
        System.out.println();
    }

    public static void printPlayerDealOutResult(Player player) {
        System.out.println(printUserDealOutResult(player));
    }

    public static void printDealerDealOut(Dealer dealer) {
        System.out.printf(NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE, dealer.getName());
    }

    public static void printTotalResult(GameResultDto gameResultDto) {
        Map<User, Integer> cardPoint = gameResultDto.getUserToCardPoint();
        System.out.println();
        cardPoint.forEach((user, point) -> System.out.printf("%s - 결과: %d" + NEWLINE, printUserDealOutResult(user), point));

        printTotalProfit(gameResultDto);
    }

    private static void printTotalProfit(GameResultDto gameResultDto) {
        System.out.println(NEWLINE + "## 최종 수익");
        System.out.printf("%s: %d" + NEWLINE, Dealer.NAME, gameResultDto.getProfitOfDealer());
        gameResultDto.getProfitOfPlayers()
                .forEach((key, value) -> System.out.printf("%s: %d" + NEWLINE, key.getName(), value));
    }

    private static String printUserDealOutResult(User user) {
        return String.format("%s카드: %s", user.getName(), cardsJoin(user));
    }

    private static String cardsJoin(User user) {
        return String.join(DELIMITER, printCard(user.getCards()));
    }

    private static String printCard(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getType() + card.getSymbol())
                .collect(Collectors.joining(DELIMITER));
    }
}

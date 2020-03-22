package view;

import domain.card.Card;
import domain.user.Dealer;
import view.dto.GameResultDto;
import view.dto.PlayersDto;
import view.dto.UserDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static final String NEWLINE = System.lineSeparator();
    public static final String DELIMITER = ", ";
    private static final int FIRST_CARD = 0;

    public static void printFirstDealOutResult(UserDto dealerDto, PlayersDto playersDto) {
        printFirstDealOut(playersDto);
        printDealerFirstDealOutResult(dealerDto);
        printPlayersFirstDealOutResult(playersDto);
    }

    private static void printFirstDealOut(PlayersDto playersDto) {
        String allNames = playersDto.getPlayers()
                .stream()
                .map(UserDto::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.printf(NEWLINE + "%s와 %s에게 2장을 나누었습니다." + NEWLINE, Dealer.NAME, allNames);
    }

    private static void printDealerFirstDealOutResult(UserDto dealerDto) {
        Card firstCard = dealerDto.getCards().get(FIRST_CARD);
        String dealerFirstDealOutResult = firstCard.getType() + firstCard.getSymbol();
        System.out.printf("%s카드: %s" + NEWLINE, Dealer.NAME, dealerFirstDealOutResult);
    }

    private static void printPlayersFirstDealOutResult(PlayersDto playersDto) {
        playersDto.getPlayers()
                .forEach(playerDto -> System.out.println(printUserDealOutResult(playerDto)));
        System.out.println();
    }

    public static void printPlayerDealOutResult(UserDto playerDto) {
        System.out.println(printUserDealOutResult(playerDto));
    }

    public static void printDealerDealOut(UserDto dealerDto) {
        System.out.printf(NEWLINE + "%s는 16이하라 한장의 카드를 더 받았습니다." + NEWLINE, dealerDto.getName());
    }

    public static void printTotalResult(GameResultDto gameResultDto) {
        Map<UserDto, Integer> cardPoint = gameResultDto.getUserToCardPoint();
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

    private static String printUserDealOutResult(UserDto userDto) {
        return String.format("%s카드: %s", userDto.getName(), cardsJoin(userDto));
    }

    private static String cardsJoin(UserDto userDto) {
        return String.join(DELIMITER, printCard(userDto.getCards()));
    }

    private static String printCard(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getType() + card.getSymbol())
                .collect(Collectors.joining(DELIMITER));
    }
}

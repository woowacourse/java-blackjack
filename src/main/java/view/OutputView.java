package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.user.Dealer;
import view.dto.GameResultDto;
import view.dto.PlayersDto;
import view.dto.UserDto;

public class OutputView {

    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String COMMA = ", ";
    private static final String CARD = "카드: ";
    private static final String RESULT = " - 결과: ";
    private static final String COLON = ": ";

    public static void printFirstDealOutResult(UserDto dealerDto, PlayersDto playersDto) {
        printFirstDealOut(playersDto);
        printDealerFirstDealOutResult(dealerDto);
        printPlayersFirstDealOutResult(playersDto);
    }

    private static void printFirstDealOut(PlayersDto playersDto) {
        String allNames = playersDto.getPlayers()
                .stream()
                .map(UserDto::getName)
                .collect(Collectors.joining(COMMA));

        System.out.printf(NEWLINE + "딜러와 %s에게 2장을 나누었습니다." + NEWLINE, allNames);
    }

    private static void printDealerFirstDealOutResult(UserDto dealerDto) {
        String dealerFirstDealOutResult =
                Dealer.NAME + CARD + dealerDto.getCards().get(0).getType() + dealerDto.getCards().get(0).getSymbol();
        System.out.println(dealerFirstDealOutResult);
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
        cardPoint.forEach((user, point) -> System.out.println(printUserDealOutResult(user) + RESULT + point));

        printTotalProfit(gameResultDto);
    }

    private static void printTotalProfit(GameResultDto gameResultDto) {
        System.out.println(NEWLINE + "## 최종 수익");
        System.out.printf("%s%s%.0f" + NEWLINE, Dealer.NAME, COLON, gameResultDto.getProfitOfDealer());
        gameResultDto.getProfitOfPlayers()
                .forEach((key, value) -> System.out.printf("%s%s%.0f" + NEWLINE, key.getName(), COLON, value));
    }

    private static String printUserDealOutResult(UserDto userDto) {
        return userDto.getName() + CARD + cardsJoin(userDto);
    }

    private static String cardsJoin(UserDto userDto) {
        return String.join(COMMA, printCard(userDto.getCards()));
    }

    private static String printCard(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getType() + card.getSymbol())
                .collect(Collectors.joining(COMMA));
    }
}

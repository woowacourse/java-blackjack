package blackjack.view;

import java.util.Map;

import blackjack.dto.UserProfitsDto;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;

public class OutputView {

    private static class StringFormatter {

        private static final String USER_CARDS_FORMAT = "%s카드 : %s";
        private static final String USER_CARDS_SCORE_FORMAT = "%s카드 : %s - 결과 : %d";
        private static final String USER_PROFIT_FORMAT = "%s : %d";
        public static String formatUserCards(UserDto userDto) {
            return String.format(USER_CARDS_FORMAT, userDto.getUserName(), userDto.getCardNames());
        }

        public static String formatUserCardsWithScore(UserDto userDto) {
            return String.format(USER_CARDS_SCORE_FORMAT,
                userDto.getUserName(), userDto.getCardNames(), userDto.getScore());
        }

        public static String formatUserProfit(String name, int profit) {
            return String.format(USER_PROFIT_FORMAT, name, profit);
        }

    }
    public void printInitCards(UsersDto usersDto) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.\n", usersDto.getDealerName(), usersDto.getPlayerNames());
        usersDto.getAllUserDto()
            .forEach(this::printUserCards);
    }

    public void printUserCards(UserDto userDto) {
        System.out.println(StringFormatter.formatUserCards(userDto));
    }

    public void printDealerBlackJack(UserDto userDto) {
        System.out.printf("%s가 블랙잭입니다.\n", userDto.getUserName());
    }

    public void printDealerHit(UserDto userDto) {
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.\n", userDto.getUserName());
    }

    public void printAllUserCardsWithScore(UsersDto usersDto) {
        usersDto.getAllUserDto()
            .forEach(userDto -> System.out.println(StringFormatter.formatUserCardsWithScore(userDto)));
    }

    public void printPlayerMoney(UserProfitsDto userProfitsDto) {
        System.out.println("## 최종 수익");
        for (Map.Entry<String, Integer> entry : userProfitsDto.getNameProfits().entrySet()) {
            System.out.println(StringFormatter.formatUserProfit(entry.getKey(), entry.getValue()));
        }
    }
}

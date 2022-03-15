package blackjack.view;

import blackjack.dto.MatchRecordDto;
import blackjack.dto.UserDto;
import blackjack.dto.UsersDto;

public class OutputView {

    private static class StringFormatter {
        private static final String USER_CARDS_FORMAT = "%s카드 : %s";
        private static final String USER_CARDS_SCORE_FORMAT = "%s카드 : %s - 결과 : %d";
        private static final String USER_MATCH_RECORD_FORMAT = "%s : %s";

        public static String formatUserCards(UserDto userDto) {
            return String.format(USER_CARDS_FORMAT, userDto.getUserName(), userDto.getCardNames());
        }

        public static String formatUserCardsWithScore(UserDto userDto) {
            return String.format(USER_CARDS_SCORE_FORMAT,
                userDto.getUserName(), userDto.getCardNames(), userDto.getScore());
        }

        public static String formatUserMatchRecord(String name, String record) {
            return String.format(USER_MATCH_RECORD_FORMAT, name, record);
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

    public void printDealerHit(UserDto userDto) {
        System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.\n", userDto.getUserName());
    }

    public void printAllUserCardsWithScore(UsersDto usersDto) {
        usersDto.getAllUserDto()
            .forEach(userDto -> System.out.println(StringFormatter.formatUserCardsWithScore(userDto)));
    }

    public void printMatchResult(MatchRecordDto matchResult) {
        System.out.println("## 최종승패");
        System.out.println(StringFormatter.formatUserMatchRecord("딜러", matchResult.getDealerRecord()));
        matchResult.getPlayerRecords()
            .forEach((key, value) -> System.out.println(StringFormatter.formatUserMatchRecord(key, value)));
    }
}

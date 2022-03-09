package BlackJack.view;

import BlackJack.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DRAW_MESSAGE = "딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String DEALER = "딜러";
    private static final String DELIMITER = ", ";

    public static void printDrawMessage(List<String> userNames){
        System.out.printf(DRAW_MESSAGE, userNames.stream().collect(Collectors.joining(DELIMITER)));
    }

    public static void printTotalUserCards(List<UserDto> users) {
        for (UserDto userDto : users) {
            if (userDto.getName().equals(DEALER)) {
                System.out.println(String.format(CARD_FORMAT, userDto.getName(), userDto.getCards().get(0)));
                continue;
            }
            String cards = userDto.getCards().stream().collect(Collectors.joining(DELIMITER));
            System.out.println(String.format(CARD_FORMAT, userDto.getName(), cards));
        }
    }
}

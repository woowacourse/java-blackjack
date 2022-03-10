package BlackJack.view;

import BlackJack.domain.Player;
import BlackJack.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DRAW_MESSAGE = "딜러와 %s에게 2장의 나누었습니다.\n";
    private static final String CARD_FORMAT = "%s카드: %s";
    private static final String DEALER = "딜러";
    private static final String DELIMITER = ", ";
    private static final String ADD_DEALER_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";

    public static void printDrawMessage(List<String> userNames){
        System.out.printf(DRAW_MESSAGE, userNames.stream().collect(Collectors.joining(DELIMITER)));
    }

    public static void printTotalUserCards(List<UserDto> users) {
        for (UserDto userDto : users) {
            if (userDto.getName().equals(DEALER)) {
                System.out.println(String.format(CARD_FORMAT, userDto.getName(), userDto.getCards().get(0)));
                continue;
            }
            printPlayerCard(userDto);
        }
    }

    public static void printPlayerCard(UserDto userDto) {
        String cards = userDto.getCards().stream().collect(Collectors.joining(DELIMITER));
        System.out.println(String.format(CARD_FORMAT, userDto.getName(), cards));
    }

    public static void printAddDealerCard() {
        System.out.println(ADD_DEALER_CARD_MESSAGE);
    }
}

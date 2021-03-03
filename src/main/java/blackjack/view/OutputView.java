package blackjack.view;

import blackjack.domain.player.UserDto;
import java.util.StringJoiner;

public class OutputView {
    public static void printUserCards(UserDto userDto) {
        System.out.print(userDto.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(", ");
        userDto.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.println(stringJoiner.toString());
    }
}

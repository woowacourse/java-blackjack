package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends User {

    private static final String YES_OR_NO_ERROR = "[ERROR] y 혹은 n만 입력할 수 있습니다.";
    private static final List<String> YES_OR_NO_VALIDATION = new ArrayList<>(
        Arrays.asList("y", "n", "Y", "N"));

    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        super.DRAWABLE_NUMBER = UserDeck.BLACK_JACK_NUMBER;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isYes(String input) {
        validateYorN(input);
        input = input.toLowerCase();
        return input.equals("y");
    }

    private static void validateYorN(String input) {
        if (!YES_OR_NO_VALIDATION.contains(input)) {
            throw new IllegalArgumentException(YES_OR_NO_ERROR);
        }
    }
}

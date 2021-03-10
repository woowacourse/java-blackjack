package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Player extends User {

    private static final String YES_OR_NO_ERROR = "[ERROR] y 혹은 n만 입력할 수 있습니다.";
    private static final List<String> YES_OR_NO_VALIDATION = new ArrayList<>(
        Arrays.asList("y", "n", "Y", "N"));

    private final Name name;
    private final Money money;

    public Player(UserDeck userDeck, Name name, Money money) {
        super(userDeck);
        super.DRAWABLE_NUMBER = UserDeck.BLACK_JACK_NUMBER;
        this.name = name;
        this.money = money;
    }

    public Player(UserDeck userDeck, String name, Money money) {
        this(userDeck, new Name(name), money);
    }

    public String getName() {
        return name.getName();
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

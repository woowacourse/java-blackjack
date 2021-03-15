package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import blackjack.domain.state.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends User {

    private static final String YES_OR_NO_ERROR = "[ERROR] y 혹은 n만 입력할 수 있습니다.";
    private static final List<String> YES_OR_NO_VALIDATION = new ArrayList<>(
        Arrays.asList("y", "n", "Y", "N"));

    private final Name name;
    private final Money money;

    public Player(UserDeck userDeck, Name name, Money money) {
        super(userDeck);
        this.name = name;
        this.money = money;
    }

    public Player(UserDeck userDeck, String name, Money money) {
        this(userDeck, new Name(name), money);
    }

    public String getName() {
        return name.getName();
    }

    public Double getMoney(double earningRate) {
        return money.getEarning(earningRate);
    }

    public boolean isDrawable(String input) {
        validateYorN(input);
        input = input.toLowerCase();
        return "y".equals(input);
    }

    private static void validateYorN(String input) {
        if (!YES_OR_NO_VALIDATION.contains(input)) {
            throw new IllegalArgumentException(YES_OR_NO_ERROR);
        }
    }
}

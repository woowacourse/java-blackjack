package blackjack;

import blackjack.model.user.Users;
import java.util.function.Supplier;

public class BlackjackGame {

    public Users createUsers(Supplier<String> readUsername) {
        String input = readUsername.get();
        return new Users(input);
    }
}

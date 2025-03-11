package view;

import domain.participant.Player;
import java.util.function.Function;

public interface InputUntilValid<T> {

    static <T> T validatePlayerAnswer(final Player player, final Function<Player, T> function) {
        while (true) {
            try {
                return function.apply(player);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

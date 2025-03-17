package view;

import domain.participant.Player;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface InputUntilValid<T> {
    T validate(Supplier<T> supplier);

    static <T> T validatePlayer(final Player player, final Function<Player, T> function) {
        while (true) {
            try {
                return function.apply(player);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

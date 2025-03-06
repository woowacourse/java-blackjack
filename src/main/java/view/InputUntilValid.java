package view;

import domain.participant.Player;
import java.util.function.Function;
import java.util.function.Supplier;

@FunctionalInterface
public interface InputUntilValid<T> {
    T validate(Supplier<T> supplier);

    static <T> T validatePlayerAnswer(Player player, Function<Player, T> function){
        {
            while(true){
                try{
                    return function.apply(player);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}

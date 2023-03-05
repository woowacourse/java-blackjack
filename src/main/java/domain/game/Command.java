package domain.game;

import domain.card.Card;
import domain.user.Player;
import view.dto.PlayerParameter;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public enum Command {
    y("y", Player::draw),
    n("n", (player, card) -> {});

    private final String value;
    private final BiConsumer<Player, Card> function;

    Command(String value, BiConsumer<Player, Card> function) {
        this.value = value;
        this.function = function;
    }

    public static Command getCommand(String input) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(input))
                .findAny()
                .orElseThrow();
    }

    public Command apply(Player player, Deck deck, Consumer<PlayerParameter> consumer) {
        function.accept(player,deck.serve());
        consumer.accept(PlayerParameter.from(player));
        if(player.isBust()) {
            return n;
        }

        return this;
    }
}

package domain.game;

import domain.card.Card;
import domain.user.Player;
import view.dto.PlayerParameter;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public enum HitCommand {
    y("y", Player::draw),
    n("n", (player, card) -> {});

    private static final String WRONG_COMMAND_ERROR_MESSAGE = "[ERROR] 잘못된 명령어 입니다.";

    private final String value;
    private final BiConsumer<Player, Card> drawCard;

    HitCommand(String value, BiConsumer<Player, Card> drawCard) {
        this.value = value;
        this.drawCard = drawCard;
    }

    public static HitCommand findCommand(String input) {
        return Arrays.stream(values())
                .filter(it -> it.value.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_COMMAND_ERROR_MESSAGE));
    }

    public HitCommand hitByCommand(Player player, Deck deck, Consumer<PlayerParameter> outputPlayer) {
        drawCard.accept(player,deck.serve());
        outputPlayer.accept(PlayerParameter.from(player));
        if(player.isBust()) {
            return n;
        }

        return this;
    }
}

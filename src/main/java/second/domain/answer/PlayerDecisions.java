package second.domain.answer;

import second.domain.player.Gamer;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class PlayerDecisions {
    private final Function<Gamer, Choice> choice;
    private final Consumer<Gamer> hands;

    public PlayerDecisions(final Function<Gamer, Choice> choice,
                           final Consumer<Gamer> hands) {
        Objects.requireNonNull(choice, "choice는 필수 입니다.");
        Objects.requireNonNull(hands, "hands는 필수 입니다.");
        this.choice = choice;
        this.hands = hands;
    }

    public boolean isHit(final Gamer player) {
        return choice.apply(player)
                .isHit();
    }

    public void hands(final Gamer player) {
        hands.accept(player);
    }
}
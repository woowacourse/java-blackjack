package blackjack.domain;

import blackjack.domain.card.UserCards;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Outcome {
    PLAYER_WIN("승", "패", (player, dealer) ->
        (player.getScore() > dealer.getScore() && !player.isBust()) || dealer.isBust()),
    PLAYER_DRAW("무", "무", (player, dealer) ->
        player.getScore() == dealer.getScore() && !player.isBust()),
    PLAYER_LOSE("패", "승", (player, dealer) ->
        player.getScore() < dealer.getScore() || player.isBust());

    private final String name;
    private final String converseName;
    private final BiPredicate<UserCards, UserCards> compare;

    Outcome(String name, String converseName, BiPredicate<UserCards, UserCards> compare) {
        this.name = name;
        this.converseName = converseName;
        this.compare = compare;
    }

    public static Outcome from(UserCards playerCards, UserCards dealerCards) {
        return Arrays.stream(values())
            .filter(outcome -> outcome.compare.test(playerCards, dealerCards))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
    }

    public String getName() {
        return name;
    }

    public String getConverseName() {
        return converseName;
    }
}

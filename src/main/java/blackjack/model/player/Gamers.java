package blackjack.model.player;

import blackjack.dto.GamerDto;
import blackjack.dto.PlayerDto;
import blackjack.model.card.CardDeck;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Gamers {
    private static final int START_CARD_COUNT = 2;
    private static final String HIT_SIGN = "y";

    private final List<Player> values;

    public Gamers(List<String> names) {
        this.values = names.stream()
                .map(Gamer::new)
                .collect(Collectors.toList());
    }

    public void giveCardsToGamer() {
        for (Player gamer : values) {
            giveCardsTo(gamer);
        }
    }

    private void giveCardsTo(Player gamer) {
        CardDeck deck = CardDeck.getInstance();
        for (int i = 0; i < START_CARD_COUNT; i++) {
            gamer.receive(deck.draw());
        }
    }

    public void hitOrStayToGamer(UnaryOperator<String> operator, Consumer<PlayerDto> consumer) {
        for (Player gamer : values) {
            hitOrStayTo(gamer, operator, consumer);
        }
    }

    private void hitOrStayTo(Player gamer, UnaryOperator<String> operator, Consumer<PlayerDto> consumer) {
        CardDeck deck = CardDeck.getInstance();
        while (canHit(gamer) && isHitSign(operator.apply(gamer.getName()))) {
            gamer.receive(deck.draw());
            consumer.accept(GamerDto.from(gamer));
        }
    }

    private boolean isHitSign(String apply) {
        return apply.equals(HIT_SIGN);
    }

    private boolean canHit(Player gamer) {
        return !gamer.isBlackJack() && !gamer.isImpossibleHit();
    }

    public List<Player> getValues() {
        return values;
    }
}


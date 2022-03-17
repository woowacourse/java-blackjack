package blackjack.domain.game;

import blackjack.dto.GamerDto;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Player extends Gamer {

    public Player(final String name) {
        super(name);
        validateEqualsDealerName(name);
    }

    public void bet(final Consumer<String> inputBetting) {
        inputBetting.accept(name);
    }

    public void draw(final CardDeck cardDeck,
                     final Predicate<Player> drawing, final BiConsumer<String, List<String>> biConsumer) {
        while (isDrawable() && isDrawing(drawing)) {
            drawCard(cardDeck.pick());
            openCards(biConsumer);
        }
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(Dealer.NAME)) {
            throw new IllegalArgumentException("딜러와 동일한 이름은 사용할 수 없습니다.");
        }
    }

    private boolean isDrawing(final Predicate<Player> drawing) {
        return drawing.test(this);
    }

    private void openCards(final BiConsumer<String, List<String>> biConsumer) {
        biConsumer.accept(this.name, GamerDto.getCards(this));
    }

    @Override
    public boolean isDrawable() {
        return playingCards.isUnderBlackjack();
    }
}

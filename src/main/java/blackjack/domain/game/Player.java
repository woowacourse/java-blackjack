package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.dto.GamerDto;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Player extends Gamer {

    public Player(final String name) {
        super(name);
        validateEqualsDealerName(name);
    }

    public void bet(final Consumer<String> inputBetting, final Supplier<String> betting) {
        inputBetting.accept(name);
        state.bet(betting.get());
    }

    public void draw(final Card card,
                     final Predicate<String> drawing, final BiConsumer<String, List<String>> openCards) {
        while (isDrawable()) {
            hitOrStay(card, drawing);
            openCards(openCards);
        }
    }

    public void compareCards(final Dealer dealer) {
        state.decideRate(findRate(dealer));
    }

    public double earning() {
        return state.getEarning();
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("딜러와 동일한 이름은 사용할 수 없습니다.");
        }
    }

    private double findRate(final Dealer dealer) {
        if (dealer.isBlackjack() || isBlackjack()) {
            return EarningRate.rateBlackjack(dealer, this);
        }
        return EarningRate.rateStay(dealer, this);
    }

    private void hitOrStay(final Card card, final Predicate<String> drawing) {
        if (isDrawing(drawing)) {
            draw(card);
            return;
        }
        stay();
    }

    private boolean isDrawing(final Predicate<String> drawing) {
        return drawing.test(name);
    }

    private void openCards(final BiConsumer<String, List<String>> openCards) {
        openCards.accept(name, GamerDto.getCards(this));
    }

    @Override
    public boolean isDrawable() {
        return state.isRunning();
    }
}

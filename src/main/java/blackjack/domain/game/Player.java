package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.dto.GamerDto;
import blackjack.util.Regex;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Player extends Gamer {

    private Betting betting = new Betting(0);

    public Player(final String name) {
        super(name);
        validateEqualsDealerName(name);
    }

    public void bet(final Consumer<String> inputBetting, final Supplier<String> betting) {
        inputBetting.accept(getName());

        String input = getInputBetting(betting);
        validateNumber(input);
        this.betting = new Betting(input);
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
        return betting.getEarning(state.getEarningRate());
    }

    private void validateEqualsDealerName(final String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException("딜러와 동일한 이름은 사용할 수 없습니다.");
        }
    }

    private String getInputBetting(final Supplier<String> betting) {
        return betting.get();
    }

    private void validateNumber(final String string) {
        if (!Regex.NUMBER.matcher(string).matches()) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
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
        return drawing.test(getName());
    }

    private void openCards(final BiConsumer<String, List<String>> openCards) {
        openCards.accept(getName(), GamerDto.getCards(this));
    }

    @Override
    public boolean isDrawable() {
        return state.isRunning();
    }
}

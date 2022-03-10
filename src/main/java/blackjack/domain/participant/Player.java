package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.GameOutcome;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Objects;

public class Player {

    private final String name;
    private State state;

    private Player(final String name, final State state) {
        Objects.requireNonNull(name, "플레이어의 이름은 null이 들어올 수 없습니다.");
        validateEmptyName(name);
        this.name = name;
        this.state = state;
    }

    public Player(final String name, final List<Card> cards) {
        this(name, State.create(new Cards(cards)));
    }

    private void validateEmptyName(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 들어올 수 없습니다.");
        }
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public int calculateResultScore() {
        checkTurnIsOver();
        return state.cards().calculateScore();
    }

    private void checkTurnIsOver() {
        if (canDraw()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 계산할 수 없습니다.");
        }
    }

    public GameOutcome fightResult(final Dealer dealer) {
        return state.compare(State.create(new Cards(dealer.getCards())));
    }

    public List<Card> getCards() {
        return List.copyOf(state.cards().values());
    }

    public String getName() {
        return name;
    }
}

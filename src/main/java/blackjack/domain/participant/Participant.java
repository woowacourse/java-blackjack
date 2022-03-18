package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.BettingMoney;
import blackjack.domain.participant.vo.ParticipantName;
import blackjack.domain.state.State;
import java.util.List;

public abstract class Participant {

    final ParticipantName name;
    State state;

    Participant(final ParticipantName name, final State state) {
        this.name = name;
        this.state = state;
    }

    Participant(final ParticipantName name, final BettingMoney bettingMoney, final List<Card> cards) {
        this(name, State.create(new Cards(cards), bettingMoney));
    }

    public String getName() {
        return name.getValue();
    }

    public int getScore() {
        checkTurnOver();
        return state.cards().getScore();
    }

    private void checkTurnOver() {
        if (canDraw()) {
            throw new IllegalStateException("턴이 종료되지 않아 카드의 합을 반환할 수 없습니다.");
        }
    }

    public abstract List<Card> getInitCards();

    public abstract List<Card> getCards();

    public abstract boolean canDraw();
}

package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.state.Ready;
import blackjack.model.state.State;
import blackjack.view.GameSign;
import java.util.List;

public abstract class Participant {
    private final String name;
    private State state;

    protected Participant(final String name) {
        checkEmpty(name);
        this.name = name;
        this.state = new Ready();
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }

    public void drawFrom(final CardDeck cardDeck) {
        while (this.state.isReady()) {
            this.state = this.state.add(cardDeck.draw());
        }
    }

    public void hitFrom(final CardDeck cardDeck, final GameSign gameSign) {
        while (this.state.isHit() && isNotStay(gameSign)) {
            this.state = this.state.add(cardDeck.draw());
        }
    }

    private boolean isNotStay(final GameSign gameSign) {
        String sign = gameSign.choiceSignTo(name);
        if (sign.equals("y")) {
            return true;
        }
        if (sign.equals("n")) {
            this.state = state.stay();
            return false;
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력해주세요");
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return state.getCards();
    }
}

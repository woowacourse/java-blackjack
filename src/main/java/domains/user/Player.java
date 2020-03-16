package domains.user;

import domains.card.Deck;
import domains.result.ResultType;

import java.util.Objects;

public class Player extends User {
    private static final String YES = "y";
    private static final String NO = "n";

    private PlayerName name;

    public Player(String name, Deck deck) {
        this.name = new PlayerName(name);
        this.hands = new Hands(deck);
    }

    public Player(String name, Hands hands) {
        this.name = new PlayerName(name);
        this.hands = hands;
    }

    public boolean needMoreCard(String answer, Deck deck) {
        checkNullOrEmpty(answer);
        checkYesOrNo(answer);

        if (answer.equals(YES)) {
            hit(deck);
            return true;
        }
        return false;
    }

    private void checkNullOrEmpty(String answer) {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new InvalidPlayerException(InvalidPlayerException.NULL_OR_EMPTY);
        }
    }

    private void checkYesOrNo(String answer) {
        if (answer.equals(YES) || answer.equals(NO)) {
            return;
        }
        throw new InvalidPlayerException(InvalidPlayerException.INVALID_INPUT);
    }

    public ResultType checkResultType(Dealer dealer) {
        if (dealer.isBurst() && !this.isBurst()) {
            return ResultType.WIN;
        }
        if (this.score() > dealer.score()) {
            return ResultType.WIN;
        }
        if (this.score() < dealer.score()) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }

    public String getName() {
        return name.toString();
    }

    @Override
    public void hit(Deck deck) {
        hands.draw(deck);
        if (hands.isBurst()) {
            this.burst = true;
        }
    }
}

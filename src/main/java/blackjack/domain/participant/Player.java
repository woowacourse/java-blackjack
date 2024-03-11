package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.util.Constants;
import java.util.regex.Pattern;

public class Player extends Gamer {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";

    private final String name;

    public Player(final String name) {
        super();

        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < Constants.BLACKJACK_BOUND;
    }

    public String getName() {
        return name;
    }
}

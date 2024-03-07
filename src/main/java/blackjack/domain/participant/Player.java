package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.regex.Pattern;

public class Player extends Participant {

    private static final Pattern PATTERN = Pattern.compile("^[0-9a-zA-Z가-힣]+(?:\\s+[0-9a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";
    private static final int BLACKJACK_BOUND = 21;
    private final String name;

    public Player(final String name, final Dealer dealer) {
        super();

        validate(name);
        this.name = name;

        draw(dealer);
        draw(dealer);
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
    }

    public void draw(final Dealer dealer) {
        Card card = dealer.draw();
        hand.add(card);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }

    public String getName() {
        return name;
    }
}

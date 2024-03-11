package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.Set;
import java.util.regex.Pattern;

public class Player extends Gamer {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";
    private static final String RESERVED_NAME_EXCEPTION = "%s라는 이름으로 지을 수 없습니다. 다른 이름을 입력해주세요.";
    private static final Set<String> reservedNames = Set.of("딜러");

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
        if (reservedNames.contains(name)) {
            throw new IllegalArgumentException(String.format(RESERVED_NAME_EXCEPTION, name));
        }
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < Hand.BLACKJACK_BOUND;
    }

    public String getName() {
        return name;
    }
}

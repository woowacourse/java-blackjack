package blackjack.domain.participant;

import blackjack.domain.Hand;
import blackjack.domain.card.TrumpCard;

import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Player extends Gamer {

    private static final Pattern PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";

    private final String name;

    public Player(final String name, final Dealer dealer) {
        super();

        validate(name);
        this.name = name;

        initialDraw(dealer);
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
    }

    private void initialDraw(final Dealer dealer) {
        IntStream.range(0, 2)
                .forEach(i -> draw(dealer));
    }

    public void draw(final Dealer dealer) {
        if (canReceiveCard()) {
            TrumpCard trumpCard = dealer.draw();
            hand.add(trumpCard);
        }
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < Hand.BLACKJACK_BOUND;
    }

    public String getName() {
        return name;
    }
}

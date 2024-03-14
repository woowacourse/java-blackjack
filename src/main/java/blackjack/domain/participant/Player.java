package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Player extends Gamer {

    private static final Pattern NAME_VALID_PATTERN = Pattern.compile("^[a-zA-Z가-힣]+(?:\\s+[a-zA-Z가-힣]+)*$");
    private static final String INVALID_NAME_FORMAT_EXCEPTION = "이름 형식에 맞지 않습니다. 이름은 영어 또는 한글로 입력해주세요.";
    private static final Pattern BETTING_VALID_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private static final String INVALID_BETTING_FORMAT_EXCEPTION = "배팅 형식에 맞지 않습니다. 0 초과의 숫자만 입력해주세요.";

    private final String name;
    private final BigDecimal betting;

    public Player(final String name, final Dealer dealer, final String betting) {
        super();

        validateName(name);
        this.name = name;

        validateBetting(betting);
        this.betting = BigDecimal.valueOf(Integer.parseInt(betting));

        initialDraw(dealer);
    }

    private void validateName(final String name) {
        if (!NAME_VALID_PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(INVALID_NAME_FORMAT_EXCEPTION);
        }
    }

    private void validateBetting(final String betting) {
        if (!BETTING_VALID_PATTERN.matcher(betting).matches()) {
            throw new IllegalArgumentException(INVALID_BETTING_FORMAT_EXCEPTION);
        }
    }

    private void initialDraw(final Dealer dealer) {
        for (int i = 0; i < 2; i++) {
            draw(dealer);
        }
    }

    public void draw(final Dealer dealer) {
        if (canReceiveCard()) {
            Card card = dealer.draw();
            hand.add(card);
        }
    }

    @Override
    public boolean canReceiveCard() {
        return !hand.isBust() && !hand.isBlackjack();
    }

    public BigDecimal calculateProfit(final GameResult gameResult) {
        return gameResult.getProfit(betting, isBlackjack());
    }

    public String getName() {
        return name;
    }
}

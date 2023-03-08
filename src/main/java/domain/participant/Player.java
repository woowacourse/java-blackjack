package domain.participant;

import domain.card.Card;
import domain.game.GameResult;
import java.math.BigDecimal;
import java.util.List;

public final class Player extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 21;

    private final PlayerInfo playerInfo;

    private Player(final PlayerInfo playerInfo) {
        super();
        this.playerInfo = playerInfo;
    }

    public static Player create(final String name) {
        validatePlayerName(name);

        final PlayerInfo playerInfo = PlayerInfo.create(name);

        return new Player(playerInfo);
    }

    private static void validatePlayerName(final String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException("플레이어는 '딜러'라는 이름을 가질 수 없습니다.");
        }
    }

    public void bet(final int betAmount) {
        playerInfo.bet(betAmount);
    }

    @Override
    public BigDecimal calculateBenefit(final GameResult gameResult) {
        return playerInfo.calculateBenefit(gameResult);
    }

    @Override
    public GameResult calculateResult(final Participant participant) {
        throw new UnsupportedOperationException("플레이어는 게임의 결과를 계산할 수 없습니다.");
    }

    @Override
    public boolean canDraw() {
        return participantCard.canDraw(STANDARD_GIVEN_SCORE);
    }

    @Override
    public List<Card> getStartCard() {
        return getCard();
    }

    @Override
    public String getName() {
        return playerInfo.getName();
    }
}

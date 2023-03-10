package blackjack.domain.player;

import blackjack.domain.card.Cards;

public final class Participant extends Player {

    private static final int MAX_NAME_LENGTH = 10;
    public static final int IN_PLAY = 21;

    private final String name;
    private final BetAmount betAmount;

    private Participant(final String name, final BetAmount betAmount, final Cards cards) {
        super(cards);
        validateBlank(name);
        validateLength(name);
        this.name = name;
        this.betAmount = betAmount;
    }

    public static Participant from(final String name, final BetAmount betAmount) {
        return new Participant(name, betAmount, new Cards());
    }

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }

    public int getBetValue() {
        return betAmount.getValue();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isInPlaying() {
        return cards.calculateScore() < IN_PLAY;
    }
}

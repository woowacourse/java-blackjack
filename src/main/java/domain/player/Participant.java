package domain.player;

import domain.card.Cards;

public final class Participant extends Player {

    private Participant(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Participant of(final String name, final int score) {
        validateImpersonate(name);

        return new Participant(Name.of(name), Cards.from(score));
    }

    private static void validateImpersonate(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
    }

    public boolean isKeepGaming(boolean isHit) {
        return !isBust() && isHit;
    }
}

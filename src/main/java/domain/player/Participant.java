package domain.player;

import domain.card.Card;
import domain.card.Cards;

import java.util.List;

public final class Participant extends Player {

    private Participant(final Name name, final Cards cards) {
        super(name, cards);
    }

    public static Participant of(final String name, final int score) {
        validateImpersonate(name);

        return new Participant(Name.of(name), Cards.from(score));
    }

    @Override
    public List<Card> showCards() {
        return getCards();
    }

    public boolean isHit(boolean wantHit) {
        return canHit() && wantHit;
    }

    @Override
    public boolean canHit() {
        return getScore().isUnderMaxScore();
    }

    private static void validateImpersonate(final String name) {
        if (name.equals("딜러")) {
            throw new IllegalArgumentException("참가자의 이름은 딜러가 될 수 없습니다.");
        }
    }
}

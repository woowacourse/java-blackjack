package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Player extends Participant {

    private Player(final String name) {
        validateNameNotBlack(name);
        this.name = name;
    }

    private static void validateNameNotBlack(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어명은 공백이 될 수 없습니다.");
        }
    }

    public static Player readyToPlay(final String name, final Deck deck) {
        final Player player = new Player(name);
        player.drawTwoCard(deck);
        return player;
    }

    @Override
    public boolean isPossibleToDrawCard() {
        return isNotBlackjack() && isNotBurst();
    }

    private boolean isNotBlackjack() {
        return !isBlackjack();
    }

    private boolean isNotBurst() {
        return !isBurst();
    }

}

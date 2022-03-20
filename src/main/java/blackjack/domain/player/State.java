package blackjack.domain.player;

public interface State {

    boolean isBlackJack(Player player);

    boolean isHit();

    boolean isBust();
}

package domain.gamer;

@FunctionalInterface
public interface PlayerTurn {
    public void playTurn(Player player, Dealer dealer);
}

package domain.gamer;

public class DealerWallet extends PlayerWallet {
    public DealerWallet(Dealer dealer, PlayerWallets playerWallets) {
        super(dealer, -playerWallets.getPlayerWallets().stream()
                .mapToInt(PlayerWallet::getMoney)
                .sum());
    }
}

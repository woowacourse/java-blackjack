package blackjack.domain;

public class GameRound {

    private final Player player;
    private final Dealer dealer;

    public GameRound(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public void play() {
        CardPicker cardPicker = new CardPicker();
    }
}

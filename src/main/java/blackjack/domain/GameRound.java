package blackjack.domain;

public class GameRound {

    private final Player player;
    private final Dealer dealer;

    public GameRound(Player player, Dealer dealer) {
        this.player = player;
        this.dealer = dealer;
    }

    public void deal(CardPicker cardPicker) {
        cardPicker.pick(2)
                .forEach(player::addCard);
    }

    public void hit(CardPicker cardPicker) {
        cardPicker.pick(1)
                .forEach(player::addCard);
    }
}

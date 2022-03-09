package blackjack.domain;

public class BlackJack {
    private final Dealer dealer;
    private final PlayerGroup playerGroup;
    private final CardPack cardPack;

    public BlackJack(PlayerGroup playerGroup) {
        this.playerGroup = playerGroup;
        this.dealer = new Dealer();
        this.cardPack = new CardPack();
    }

    public void divideCards() {
        playerGroup.addTwoCards(cardPack.pickOne(), cardPack.pickOne());
        dealer.addTwoCards(cardPack.pickOne(), cardPack.pickOne());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void addCard(Player player) {
        player.addCard(cardPack.pickOne());
    }
}

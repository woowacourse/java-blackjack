public class CardDistributor {

    private final RandomCardPicker randomCardPicker;

    public CardDistributor(RandomCardPicker randomCardPicker) {
        this.randomCardPicker = randomCardPicker;
    }

    public void distributeCardToPlayer(Player player) {
        Card card = randomCardPicker.drawCard();
        player.receiveOneCard(card);
    }

    public void distributeCardToDealer(Dealer dealer) {
        Card card = randomCardPicker.drawCard();
        dealer.receiveOneCard(card);
    }
}

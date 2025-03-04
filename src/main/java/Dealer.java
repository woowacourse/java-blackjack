public class Dealer extends Gamer {

    @Override
    public boolean canReceiveAdditionalCards() {
        return cards.sum() <= 16;
    }
}

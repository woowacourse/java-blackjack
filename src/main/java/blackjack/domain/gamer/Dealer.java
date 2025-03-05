package blackjack.domain.gamer;

public class Dealer extends Gamer {

    // TODO: 상수화
    @Override
    public boolean canReceiveAdditionalCards() {
        return cards.sum() <= 16;
    }
}

package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.WinTieLose;
import blackjack.domain.participant.comparator.Comparator;
import blackjack.domain.participant.comparator.InitialComparator;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private final Comparator comparator;

    public Dealer() {
        super(new Name(DEALER_NAME));
        this.comparator = new InitialComparator(this);
    }

    public boolean canNotHit() {
        return getTotalScore() > 16 || getTotalScore() < 0;
    }

    public void reverseAllExceptOne() {
        this.getCards().forEach(Card::closeCard);
        this.getCards().get(0).openCard();
    }

    public void openAllCard() {
        this.getCards().forEach(Card::openCard);
    }

    public WinTieLose compareScoreWith(Player player) {
        return comparator.compareWithPlayer(player);
    }
}

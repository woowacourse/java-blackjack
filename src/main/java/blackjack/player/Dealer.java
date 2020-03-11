package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.CardBundle;

public class Dealer extends Player {

    private static final int HIT_VALUE = 16;

    public Dealer(CardBundle cardBundle) {
        super(cardBundle, "딜러");
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isGambler() {
        return false;
    }

    @Override
    public boolean isDrawable() {
        return cardBundle.calculateScore() <= HIT_VALUE;
    }

    @Override
    public GameReport getReport(Player player) {
        int gameResult = player.cardBundle.compare(this.cardBundle);
        return new GameReport(player.name, gameResult);
    }
}

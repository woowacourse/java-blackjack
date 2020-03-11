package blackjack.player;

import blackjack.GameReport;
import blackjack.player.card.CardBundle;

public class Dealer extends Player {

    public Dealer(CardBundle cardBundle) {
        super(cardBundle, "딜러");
    }

    @Override
    public GameReport getReport(Player player) {
        int gameResult = player.cardBundle.compare(this.cardBundle);
        return new GameReport(player.name, gameResult);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isGambler() {
        return false;
    }
}

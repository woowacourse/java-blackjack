package blackjack.domain.user;

import blackjack.util.BlackJackRule;
import java.util.List;

public class Dealer extends User {

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canDrawCard() {
        return BlackJackRule.isDealerDraw(cards.getScore());
    }

    public String getFirstCardInfo() {
        List<String> cardsInfos = cards.getInfos();
        return cardsInfos.get(0);
    }
}

package blackjack.domain.user;

import blackjack.domain.PlayerResult;
import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends User {
    private static final int DEALER_CRITICAL_SCORE = 16;
    private static final int COUNT_OF_INITIAL_OPEN_CARDS = 1;
    private static final String KOREAN_NAME = "딜러";

    public Dealer() {
        this.name = KOREAN_NAME;
    }

    public static int getCriticalScore() {
        return DEALER_CRITICAL_SCORE;
    }

    public Integer calculatePlayerProfit(Player player) {
        PlayerResult playerResult = PlayerResult.of(this.cards, player.getCards());
        return playerResult.calculateProfit(player.getBettingMoney());
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.subList(START_INDEX, COUNT_OF_INITIAL_OPEN_CARDS);
    }

    @Override
    public boolean isReceivableOneMoreCard() {
        return cards.isScoreUnder(DEALER_CRITICAL_SCORE);
    }
}
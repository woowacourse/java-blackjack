package blackjack.domain.gambler;

import blackjack.constant.GamblerStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public abstract class Gambler {

    public static final int INITIAL_CARD_COUNT = 2;

    protected Cards cards;
    protected GamblerStatus status;
    private double betAmount;

    public abstract List<Card> openInitialCards();

    public void drawInitializeHand(Cards cards) {
        validateInitialCardsSize(cards);
        this.cards = cards;
        this.status = GamblerStatus.IN_PROGRESS;
    }

    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards());
    }

    public void updateBetAmount(double betAmount) {
        validateBetAmount(betAmount);
        this.betAmount = betAmount;
    }

    public void applyBetResult(double profit) {
        this.betAmount = profit;
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int sumCardScores() {
        return cards.sumCardScores();
    }

    public boolean isInProgress() {
        return status == GamblerStatus.IN_PROGRESS;
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }

    private void validateBetAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 0원 이상이어야 합니다.");
        }
    }

    public Cards getCards() {
        return cards;
    }

    public int getBetAmount() {
        return (int) betAmount;
    }

}

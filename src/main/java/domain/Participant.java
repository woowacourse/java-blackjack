package domain;

import java.util.List;

public abstract class Participant {

    private static final int MAXIMUM_SUM_OF_CARD = 21;
    private static final int INITIAL_CARD_SIZE = 2;

    protected final Cards cards;

    public boolean selectToPickOtherCard(final int cardBoxIndex) {
        return pickOtherCard(cardBoxIndex);
    }
    private boolean pickOtherCard(final int cardBoxIndex) {
        return this.cards.addCard(CardBox.get(cardBoxIndex));
    }

    public PlayerState askPlayerState() {
        if (sumOfParticipantCards() > MAXIMUM_SUM_OF_CARD) {
            return PlayerState.MORE;
        }
        if (sumOfParticipantCards() == MAXIMUM_SUM_OF_CARD) {
            return PlayerState.FLAT;
        }
        return PlayerState.LESS;
    }

    public Participant(Cards cards) {
        this.cards = cards;
    }

    public int sumOfParticipantCards() {
        return cards.sumOfCards();
    }

    public boolean isBlackJack() {
        return sumOfParticipantCards() == MAXIMUM_SUM_OF_CARD && cards.sizeOfCards() == INITIAL_CARD_SIZE;
    }

    public static Result checkWinningResult(final Participant standardPlayer,final Participant opponentPlayer) {
        int sumOfDealerCards = standardPlayer.sumOfParticipantCards();
        int sumOfPlayerCards = opponentPlayer.sumOfParticipantCards();
        return dealerCompareToPlayer(sumOfDealerCards, sumOfPlayerCards);
    }

    private static Result dealerCompareToPlayer(final int sumOfStandardPlayerCards, final int sumOfOpponentPlayerCards) {
        if (sumOfStandardPlayerCards > MAXIMUM_SUM_OF_CARD && sumOfOpponentPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.DRAW;
        }
        if (sumOfStandardPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.LOSE;
        }
        if (sumOfOpponentPlayerCards > MAXIMUM_SUM_OF_CARD) {
            return Result.WIN;
        }
        return compareNumber(sumOfStandardPlayerCards, sumOfOpponentPlayerCards);
    }

    private static Result compareNumber(int sumOfDealerCards, int sumOfPlayerCards) {
        int resultNum = Integer.compare(sumOfDealerCards, sumOfPlayerCards);
        if (resultNum == 1) {
            return Result.WIN;
        }
        if (resultNum == 0) {
            return Result.DRAW;
        }
        return Result.LOSE;
    }

    public List<String> copyCards() {
        return cards.copyCards();
    }
}

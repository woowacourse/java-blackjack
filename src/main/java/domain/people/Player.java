package domain.people;

import java.util.ArrayList;
import java.util.List;

import domain.Number;
import domain.card.Card;
import domain.game.BetAmount;
import domain.game.ProfitRatio;
import view.ErrorMessage;

public final class Player {
    private static final int MAX_PLAYER_NAME_LENGTH = 10;
    private static final String COMMA = ",";

    private final Participant participant;
    private BetAmount betAmount;

    public Player(final String name) {
        validate(name);
        participant = new Participant(new ArrayList<>(), name);
    }

    private void validate(final String name) {
        validateNotNull(name);
        validateNotEmpty(name);
        validateNoDealer(name);
        validateDoesNotContainComma(name);
        validateNameLength(name);
    }

    private void validateNotNull(final String name) {
        if (name == null) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_NULL.getMessage());
        }
    }

    private void validateNotEmpty(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_EMPTY.getMessage());
        }
    }

    private void validateNoDealer(final String name) {
        if (InvalidNames.isInvalidNames(name)) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DEALER.getMessage());
        }
    }

    private void validateDoesNotContainComma(final String name) {
        if (name.contains(COMMA)) {
            throw new IllegalArgumentException(ErrorMessage.NAME_CONTAINS_COMMA.getMessage());
        }
    }

    private void validateNameLength(final String name) {
        if (name.length() > MAX_PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NAME_LENGTH.getMessage());
        }
    }

    public String fetchPlayerName() {
        return participant.getName();
    }

    public boolean hasBlackJack(final int initHandCount) {
        return participant.fetchHandValue() == Number.BLACKJACK_HAND_VALUE.get()
            && participant.fetchHand().size() == initHandCount;
    }

    public void receiveCard(final Card card) {
        participant.receiveCard(card);
    }

    public List<Card> fetchHand() {
        return participant.fetchHand();
    }

    public int fetchHandValue() {
        int handValue = participant.fetchHandValue();
        if (hasBlackJack(Number.INIT_HAND_COUNT.get())) {
            handValue = Number.BLACKJACK_RESULT_VALUE.get();
        }
        return handValue;
    }

    public boolean isBust() {
        return participant.isBust();
    }

    public void assignBetAmount(final int betAmount) {
        this.betAmount = new BetAmount(betAmount);
    }

    public int fetchProfit(final Dealer dealer) {
        return betAmount.calculateProfit(
            ProfitRatio.fetchProfitRatio(
                fetchHandValue(), dealer.fetchHandValue()));
    }
}

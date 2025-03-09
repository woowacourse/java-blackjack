package domain;

import except.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackParticipant {

    private static final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";
    private static final int BUST_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private static final String INVALID_NAME = "닉네임은 공백일 수 없습니다";

    private final List<TrumpCard> trumpCards = new ArrayList<>();
    private final String name;

    public BlackjackParticipant(String name) {
        this.name = name;
        validateNickname();
    }

    private void validateNickname() {
        if (name == null || name.isBlank()) {
            throw new BlackJackException(INVALID_NAME);
        }
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBust(calculateCardSum())) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        trumpCards.add(trumpCard);
    }

    public int calculateCardSum() {
        int sum = trumpCards.stream()
                .map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        int aceCount = (int) trumpCards.stream()
                .filter(TrumpCard::isAce)
                .count();
        if (aceCount != 0) {
            return calculateAceIncludeSum(aceCount, sum);
        }
        return sum;
    }

    private int calculateAceIncludeSum(int aceCount, int sum) {
        if (isBust(sum) && aceCount != 0) {
            return calculateAceIncludeSum(aceCount - 1, sum - ACE_DIFF);
        }
        return sum;
    }

    private boolean isBust(int number) {
        return BUST_STANDARD < number;
    }

    public boolean isDrawable() {
        int sum = calculateCardSum();
        return !isBust(sum);
    }

    public List<TrumpCard> trumpCards() {
        return trumpCards;
    }

    public String name() {
        return name;
    }
}

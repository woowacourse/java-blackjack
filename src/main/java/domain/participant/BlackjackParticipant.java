package domain.participant;

import domain.blackjackgame.TrumpCard;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackParticipant {

    private static final int BURST_STANDARD = 21;
    private static final int ACE_DIFF = 10;
    private static final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";
    private static final String INVALID_NAME = "닉네임은 공백일 수 없습니다";
    private static final String DEALER_NAME = "딜러";
    private static final String INVALID_PLAYER_NAME = "일반 플레이어는 딜러일 수 없습니다.";

    private final List<TrumpCard> cardHands = new ArrayList<>();
    private final String name;

    protected BlackjackParticipant(String name) {
        this.name = name;
        validateNickname();
    }

    protected static String dealerName() {
        return DEALER_NAME;
    }

    private void validateNickname() {
        if (name == null || name.isBlank()) {
            throw new BlackJackException(INVALID_NAME);
        }
    }

    public void addDraw(TrumpCard trumpCard) {
        if (isBurst(calculateCardSum())) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        cardHands.add(trumpCard);
    }

    public int calculateCardSum() {
        int sum = cardHands.stream().map(TrumpCard::cardNumberValue)
                .reduce(Integer::sum)
                .orElse(0);
        int aceCount = (int) cardHands.stream()
                .filter(TrumpCard::isAce)
                .count();
        if (aceCount != 0) {
            return calculateAceIncludeSum(aceCount, sum);
        }
        return sum;
    }

    private int calculateAceIncludeSum(int aceCount, int sum) {
        if (isBurst(sum) && aceCount != 0) {
            return calculateAceIncludeSum(aceCount - 1, sum - ACE_DIFF);
        }
        return sum;
    }

    protected void validatePlayerName() {
        if (name.equals(dealerName())) {
            throw new BlackJackException(INVALID_PLAYER_NAME);
        }
    }

    public boolean isBurst(int number) {
        return BURST_STANDARD < number;
    }

    abstract public boolean isDrawable();

    public List<TrumpCard> cardHands() {
        return cardHands;
    }

    public String name() {
        return name;
    }
}

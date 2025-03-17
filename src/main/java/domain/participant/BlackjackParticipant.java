package domain.participant;

import domain.blackjackgame.TrumpCard;
import exception.BlackJackException;
import java.util.List;

public abstract class BlackjackParticipant {

    private static final String INVALID_CARD_STATE = "비정상적인 카드 추가입니다. 플레이어는 21장 이상 받을 수 없습니다";
    private static final String INVALID_NAME = "닉네임은 공백일 수 없습니다";
    private static final String DEALER_NAME = "딜러";

    private final String name;
    private final BlackjackBet blackjackBet;
    private BlackjackHands hands;

    protected BlackjackParticipant(String name, BlackjackBet blackjackBet, List<TrumpCard> cards) {
        this.name = name;
        this.blackjackBet = blackjackBet;
        this.hands = new BlackjackHands(cards);
        validateNickname();
    }

    protected BlackjackParticipant(String name, List<TrumpCard> cards) {
        this.name = name;
        this.hands = new BlackjackHands(cards);
        this.blackjackBet = new BlackjackBet(0);
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
        if (isBust()) {
            throw new IllegalStateException(INVALID_CARD_STATE);
        }
        hands = hands.addCard(trumpCard);
    }

    protected boolean isBust() {
        return hands.isBust();
    }

    public int calculateCardSum() {
        return hands.calculateCardSum();
    }

    abstract public boolean isDrawable();

    public BlackjackHands hands() {
        return hands;
    }

    public String name() {
        return name;
    }

    public List<TrumpCard> cards() {
        return hands.cards();
    }

    public BlackjackBet bet() {
        return blackjackBet;
    }
}


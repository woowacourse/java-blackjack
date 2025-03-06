package domain;

import domain.constant.WinDrawLose;
import java.util.List;

public class Player {

    public static final int BUST_STANDARD = 21;

    private final Nickname nickname;
    private Cards cards;

    public Player(Nickname nickname, Cards cards) {
        validateInitialCardsSize(cards);
        this.nickname = nickname;
        this.cards = cards;
    }

    public boolean addOneCard(Card card) {
        return cards.addOneCard(card);
    }

    public int sumCardNumbers() {
        return cards.sumCardNumbers();
    }

    public WinDrawLose compareTo(int dealerScore) {
        int sum = sumCardNumbers();

        if ((sum > BUST_STANDARD && dealerScore > BUST_STANDARD) || sum == dealerScore) {
            return WinDrawLose.DRAW;
        }
        if (sum > BUST_STANDARD) {
            return WinDrawLose.LOSE;
        }
        if (dealerScore > BUST_STANDARD) {
            return WinDrawLose.WIN;
        }
        if (sum > dealerScore) {
            return WinDrawLose.WIN;
        }
        return WinDrawLose.LOSE;
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != 2) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 두 장을 받아야 합니다.");
        }
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public String getNickname() {
        return nickname.getValue();
    }
}

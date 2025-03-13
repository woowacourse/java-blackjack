package participant;

import card.Card;
import card.Cards;
import constant.WinningResult;

import java.util.List;

public class Player {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Nickname nickname;
    private final Cards cards;

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

    public WinningResult compareTo(int dealerScore) {
        int playerScore = sumCardNumbers();
        return WinningResult.getWinningResult(playerScore, dealerScore);
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    private void validateInitialCardsSize(Cards cards) {
        if (cards.getSize() != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("[ERROR] 초기 카드는 " + INITIAL_CARD_COUNT + "장을 받아야 합니다.");
        }
    }

    public String getNickname() {
        return nickname.getValue();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }
}

package domain.user;

import static domain.money.GameResult.LOSE;
import static domain.money.GameResult.WIN;

import domain.card.Card;
import domain.card.Number;
import domain.money.GameResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK_CONDITION = 21;
    private static final int START_CARDS_COUNT = 2;
    public static final int FIRST_INDEX = 0;
    private final List<Card> cards;

    public Hand(Card... cards) {
        this.cards = new ArrayList<>(List.of(cards));
    }

    public void receiveCard(Card card) {
        cards.add(card);
    }

    public int sumCard() {
        int sum = cards.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
        return addSumByAce(sum);
    }

    private int addSumByAce(int sum) {
        if (hasAce()) {
            return Number.sumContainingSoftAce(sum);
        }
        return sum;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public GameResult generateResult(Hand opponent) {
        if (busted() || isOpponentBlackjackOnly(opponent)) {
            return LOSE;
        }
        if (opponent.busted() || isCurrentBlackjackOnly(opponent)) {
            return WIN;
        }
        return compare(sumCard(), opponent.sumCard());
    }

    private GameResult compare(int current, int opponent) {
        return Arrays.stream(GameResult.values())
                .filter(result -> result.getCondition().test(current, opponent))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("입력에 따른 결과가 존재하지 않습니다."));
    }

    private boolean isOpponentBlackjackOnly(Hand opponent) {
        return !isBlackjack() && opponent.isBlackjack();
    }

    private boolean isCurrentBlackjackOnly(Hand opponent) {
        return isBlackjack() && !opponent.isBlackjack();
    }

    public boolean isBlackjack() {
        return cards.size() == START_CARDS_COUNT && sumCard() == BLACK_JACK_CONDITION;
    }

    public boolean busted() {
        return sumCard() > BLACK_JACK_CONDITION;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }
}

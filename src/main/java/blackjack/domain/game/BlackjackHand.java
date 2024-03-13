package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static blackjack.domain.game.BlackjackGame.STARTING_CARDS_AMOUNT;

public class BlackjackHand {

    private static final int BLACKJACK_SCORE = 21;
    private static final int ACE_SPECIAL_SCORE = 10;

    private final List<Card> cards;

    public BlackjackHand() {
        this.cards = new ArrayList<>();
    }

    public void receive(Card receivedCard) {
        cards.add(receivedCard);
    }

    public void receive(List<Card> receivedCards) {
        cards.addAll(receivedCards);
    }

    public int calculateScore() {
        final int score = calculateInitialScore();
        if (score > BLACKJACK_SCORE) {
            return score;
        }

        return IntStream.rangeClosed(0, countAce())
                .map(index -> (score + index * ACE_SPECIAL_SCORE))
                .filter(number -> number <= BLACKJACK_SCORE)
                .max()
                .orElseThrow(() -> new IllegalStateException("점수 계산에 실패했습니다."));
    }

    public boolean isOverBlackjackScore() {
        return calculateInitialScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == STARTING_CARDS_AMOUNT && calculateScore() == BLACKJACK_SCORE;
    }

    private int calculateInitialScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    private int countAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public List<Card> getCards() {
        return cards;
    }
}

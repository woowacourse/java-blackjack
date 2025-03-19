package card;

import score.Score;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int BLACK_JACK_CARD_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        this.cards.add(card);
    }

    public Score calculateScore() {
        int totalScore = cards.stream()
                .mapToInt(Card::getScore)
                .sum();
        return new Score(totalScore);
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_SIZE && hasSoftAce() && calculateScore().isSatisfiedBlackJackScore();
    }

    public boolean isBust() {
        return calculateScore().isBust();
    }

       public boolean isHit(int condition) {
        return calculateScore().isOver(condition);
    }

    public boolean hasSoftAce() {
        return cards.stream()
                .anyMatch(card -> card.matchesRank(AceRank.SOFT_ACE));
    }

    public void applyAceRule() {
        if (isBust() && hasSoftAce()) {
            convertSoftAceToHardAce();
        }
    }

    public void convertSoftAceToHardAce() {
        Card existedSoftAce = cards.stream()
                .filter(card -> card.matchesRank(AceRank.SOFT_ACE))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("보유한 카드 중 ACE 가 존재하지 않습니다"));

        cards.removeIf(card -> card.matchesRank(AceRank.SOFT_ACE));
        cards.add(new Card(existedSoftAce.getSuit(), AceRank.HARD_ACE));
    }

    public List<Card> getCards() {
        return cards;
    }
}

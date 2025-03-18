package blackjack.domain.card;

import blackjack.domain.game.GameScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addAll(List<Card> cards) {
        cards.forEach(this::add);
    }

    private void add(Card card) {
        validateCanAddCard(card);
        cards.add(card);
    }

    public GameScore getGameScore() {
        GameScore gameScore = new GameScore(cards.stream()
                .mapToInt(Card::getValue)
                .sum(),
                cards.size());
        if (hasAce()) {
            gameScore = gameScore.addAce();
        }
        return gameScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private void validateCanAddCard(Card card) {
        if (getGameScore().isBust()) {
            throw new IllegalArgumentException("버스트 상태에서는 카드를 받을 수 없습니다");
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("중복된 카드를 받을 수 없습니다");
        }
    }
}

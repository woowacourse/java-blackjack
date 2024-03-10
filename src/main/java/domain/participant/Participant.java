package domain.participant;

import controller.dto.HandStatus;
import domain.BlackJackGame;
import domain.Deck;
import domain.card.Card;
import domain.constants.CardCommand;
import java.util.List;

public abstract class Participant {
    private final String name;
    private final Hand hand;

    protected Participant(final String name) {

        Validator.validateName(name);
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> pickCard(final Deck deck, final int count) {
        for (int index = 0; index < count; index++) {
            hand.saveCard(deck.pick());
        }
        return hand.getCards();
    }

    public boolean isBusted() {
        return hand.calculateScore() > BlackJackGame.BLACKJACK_SCORE;
    }

    protected boolean isNotBusted() {
        return !isBusted();
    }

    protected int calculateScore() {
        return hand.calculateScore();
    }

    private int calculateResultScore() {
        return hand.calculateResultScore();
    }

    public HandStatus createHandStatus() {
        return new HandStatus(name, hand);
    }

    public String getName() {
        return this.name;
    }

    public boolean isNotSameScoreAs(final Player other) {
        return calculateResultScore() != other.calculateResultScore();
    }

    public boolean hasMoreScoreThan(final Player other) {
        return calculateResultScore() > other.calculateResultScore();
    }

    public boolean hasLessOrSameCardThan(final Player other) {
        return getCardSize() <= other.getCardSize();
    }

    public int getCardSize() {
        return hand.size();
    }


    public Hand getHand() {
        return this.hand;
    }


    abstract public boolean canPickCard(final CardCommand cardCommand);


    // TODO: 검증 로직을 뷰 단으로 옮기기
    private static class Validator {
        private static void validateName(final String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("이름으로 빈 문자열이 입력되었습니다.");
            }
        }
    }
}

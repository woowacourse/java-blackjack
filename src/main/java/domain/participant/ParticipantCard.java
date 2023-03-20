package domain.participant;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public final class ParticipantCard {

    private static final int BLACKJACK_SIZE = 2;
    private static final int FIRST_CARD_INDEX = 0;

    private final List<Card> cards;

    private ParticipantCard() {
        this.cards = new ArrayList<>();
    }

    public static ParticipantCard create() {
        return new ParticipantCard();
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    ParticipantScore calculateScore() {
        final ParticipantScore participantScore = sumCards();

        if (hasAce()) {
            return participantScore.processIncludeAce();
        }
        return participantScore;
    }

    boolean canDraw(final int score) {
        final ParticipantScore standardScore = ParticipantScore.scoreOf(score);
        final ParticipantScore participantScore = calculateScore();

        return standardScore.checkGreaterThan(participantScore);
    }

    boolean checkBust() {
        final ParticipantScore participantScore = calculateScore();

        return participantScore.checkBust();
    }

    boolean checkBlackJack() {
        final ParticipantScore participantScore = calculateScore();

        return cards.size() == BLACKJACK_SIZE && participantScore.checkBlackJack();
    }

    boolean checkGreaterScoreThan(final ParticipantCard target) {
        final ParticipantScore participantScore = this.calculateScore();
        final ParticipantScore targetScore = target.calculateScore();

        return participantScore.checkGreaterThan(targetScore);
    }

    private ParticipantScore sumCards() {
        return cards.stream()
                .map(card -> ParticipantScore.scoreOf(card.findCardNumber()))
                .reduce(ParticipantScore.defaultOf(), ParticipantScore::add);
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::checkAce);
    }

    Card getFirstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    List<Card> getCards() {
        return List.copyOf(cards);
    }
}

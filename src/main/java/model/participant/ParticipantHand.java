package model.participant;

import constant.ErrorMessage;
import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;

public class ParticipantHand {
    private static final Integer MAX_ACE_SCORE = 11;
    private static final Integer ADDITIONAL_ACE_SCORE = 10;
    private static final Integer BUST_SCORE = 21;
    private static final Integer BLACK_JACK_SCORE = 21;
    private static final Integer BLACK_JACK_CARD_SIZE = 2;

    private final Score score = new Score();
    private final Cards deck = new Cards();

    public void addDeck(Card card) {
        validateCardDuplicate(card);
        deck.add(card);
        score.add(card.cardNumber().getScore());
    }

    public Integer getScore() {
        if(deck.hasAce() && score.getScore() <= MAX_ACE_SCORE) {
            return score.getScore() + ADDITIONAL_ACE_SCORE;
        }
        return score.getScore();
    }

    public List<String> getDeck() {
        return deck.get().stream()
                .map(Card::getString)
                .toList();
    }

    public String getFirstCard() {
        return deck.getFirstCard().getString();
    }

    public boolean isBust() {
        Integer score = getScore();
        return score > BUST_SCORE;
    }

    public boolean isBlackJack() {
        Integer score = getScore();
        return Objects.equals(score, BLACK_JACK_SCORE) && Objects.equals(deck.getSize(), BLACK_JACK_CARD_SIZE);
    }

    private void validateCardDuplicate(Card card) {
        if(deck.get().contains(card)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_IN_DECK.getMessage());
        }
    }
}

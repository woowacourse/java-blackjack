package dto;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Participant;
import java.util.List;
import java.util.stream.Collectors;

public class ParticipantDto {
    private static final String DIAMOND = "다이아몬드";
    private static final String CLUB = "클로버";
    private static final String SPADE = "스페이드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final int DEALER_VISIBLE_CARD_INDEX = 0;

    private final String name;
    private final List<String> cards;
    private final int score;

    private ParticipantDto(Participant participant, List<String> cards) {
        this.name = participant.getName();
        this.cards = cards;
        this.score = participant.calculateScore();
    }

    public static ParticipantDto from (Participant participant){
        List<String> cards = participant.getCards().stream()
                .map(card -> makeCardView(card))
                .collect(Collectors.toList());
        return new ParticipantDto(participant, cards);
    }

    private static String makeCardView(Card card) {
        return makeDenominationView(card.getDenomination()) + makeSuitView(card.getSuit());
    }

    private static String makeSuitView(Suit suit) {
        if (Suit.DIAMOND.equals(suit)) {
            return DIAMOND;
        }
        if (Suit.HEART.equals(suit)) {
            return HEART;
        }
        if (Suit.SPADE.equals(suit)) {
            return SPADE;
        }

        return CLUB;
    }

    private static String makeDenominationView(Denomination denomination) {
        if (Denomination.ACE.equals(denomination)) {
            return ACE;
        }
        if (Denomination.JACK.equals(denomination)) {
            return JACK;
        }
        if (Denomination.QUEEN.equals(denomination)) {
            return QUEEN;
        }
        if (Denomination.KING.equals(denomination)) {
            return KING;
        }

        return String.valueOf(denomination.getScore());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public String getDealerFirstCard() {
        return cards.get(DEALER_VISIBLE_CARD_INDEX);
    }
    
    public int getScore() {
        return score;
    }
}

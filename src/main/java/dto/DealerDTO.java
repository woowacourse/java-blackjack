package dto;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class DealerDTO {
    private static final String DIAMOND = "다이아몬드";
    private static final String CLUB = "클로버";
    private static final String SPADE = "스페이드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";
    private static final int FIRST_CARD_INDEX = 0;

    private final String name;
    private final List<String> cards;
    private final int score;

    public DealerDTO(Dealer dealer) {
        this.name = dealer.getName();
        this.cards = dealer.getCards().stream()
                .map(card -> makeCardView(card))
                .collect(Collectors.toList());
        this.score = dealer.calculateScore();
    }

    private String makeCardView(Card card) {
        return makeDenominationView(card.getDenomination()) + makeSuitView(card.getSuit());
    }

    private String makeSuitView(Suit suit) {
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

    private String makeDenominationView(Denomination denomination) {
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

    public String getFirstCards() {
        return cards.get(FIRST_CARD_INDEX);
    }
    
    public int getScore() {
        return score;
    }
}

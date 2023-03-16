package dto;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;
import view.OutputView;

public class PlayerDTO {
    private static final String DIAMOND = "다이아몬드";
    private static final String CLUB = "클로버";
    private static final String SPADE = "스페이드";
    private static final String HEART = "하트";
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";

    private final String name;
    private final List<String> cards;
    private final int score;

    public PlayerDTO(Player player) {
        this.name = player.getName();
        this.cards = player.getCards().stream()
                .map(card -> makeCardView(card))
                .collect(Collectors.toUnmodifiableList());
        this.score = player.calculateScore();
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

    public int getScore() {
        return score;
    }
}
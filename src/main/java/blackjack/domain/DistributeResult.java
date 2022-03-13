package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;

import java.util.List;
import java.util.stream.Collectors;

public class DistributeResult {

    private static final String DISPLAY_DELIMITER = ", ";
    private static final String DEALER_NAME = "딜러";

    private String name;
    private List<Card> cards;

    public DistributeResult(Participant participant) {
        this.name = participant.getName();
        this.cards = List.copyOf(participant.getCards());
    }

    public String getName() {
        return name;
    }

    public String getConcatNameAndCardNames() {
        return name + ":" + String.join(DISPLAY_DELIMITER, cards.stream().map(Card::getCardText).collect(Collectors.toList()));
    }

    public String getConcatNameAndCardsExcludeOneCard() {
        if (this.name.equals(DEALER_NAME))
            return name + ":" + String.join(DISPLAY_DELIMITER,
                    cards.subList(1, cards.size()).stream().map(Card::getCardText).collect(Collectors.toList()));
        return getConcatNameAndCardNames();
    }
}

package model.result;

import java.util.List;
import model.card.Card;
import model.participant.Dealer;
import model.participant.Participant;

public class ParticipantCard {

    private final String name;
    private final List<String> cards;

    private ParticipantCard(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static ParticipantCard createWithFirstCard(Dealer dealer) {
        Card firstCard = dealer.getFirstCard();
        return new ParticipantCard(dealer.getName(), List.of(firstCard.toString()));
    }

    public static ParticipantCard createWithAllCard(Participant participant) {
        List<String> cards = participant.getCards()
            .stream()
            .map(Card::toString)
            .toList();
        return new ParticipantCard(participant.getName(), cards);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}

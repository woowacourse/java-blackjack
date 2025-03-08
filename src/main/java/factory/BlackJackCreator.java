package factory;


import domain.card.Card;
import domain.card.CardBundle;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.InputSplitter;

public class BlackJackCreator {

    private BlackJackCreator() {
    }

    public static Participants createParticipants(String inputUserNames) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        List<String> userNames = InputSplitter.split(inputUserNames);
        for (String userName : userNames) {
            Participant participant = new Player(userName);
            participants.add(participant);
        }
        return new Participants(participants);
    }

    public static CardDeck createCardDeck(CardBundle cardBundle) {
        List<Card> allCards = cardBundle.getAllCards();
        List<Card> shuffledAllCards = new ArrayList<>(allCards);
        Collections.shuffle(shuffledAllCards);
        return new CardDeck(shuffledAllCards);
    }

    public static CardBundle createCardBundle() {
        return new CardBundle();
    }
}

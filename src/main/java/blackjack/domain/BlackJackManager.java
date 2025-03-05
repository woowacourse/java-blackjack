package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackManager {

    private final CardDeck cardDeck;
    private final List<Participant> participants;

    public BlackJackManager(CardDeck cardDeck, List<Participant> participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public static BlackJackManager createByPlayerNames(List<String> names) {
        CardDeck cardDeck = CardDeck.createCardDeck();

        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        for (String name : names) {
            Player player = new Player(name);
            participants.add(player);
        }
        return new BlackJackManager(cardDeck, participants);
    }

    public void addExtraCard(Participant participant) {
        Card card = cardDeck.pickRandomCard();
        participant.addCards(card);
    }

    public void initCardsToParticipants() {
        for (Participant participant : participants) {
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }

    public List<String> getPlayerNames() {
        return participants.stream()
                .filter(participant -> participant instanceof Player)
                .map(participant -> (Player) participant)
                .map(Player::getName)
                .toList();
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}

package blackjack.manager;

import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Deck;
import java.util.List;

public class BlackJackInitManager {

    private final CardsGenerator cardsGenerator;

    public BlackJackInitManager(CardsGenerator cardsGenerator) {
        this.cardsGenerator = cardsGenerator;
    }

    public Deck generateDeck() {
        return new Deck(cardsGenerator.generate());
    }

    public Players savePlayers(List<String> names) {
        List<Player> players = names.stream()
                .map(name -> new Player(name, new Hand()))
                .toList();

        return new Players(players);
    }

    public Dealer saveDealer() {
        return new Dealer(new Hand());
    }

    public Participants saveParticipants(List<String> names) {
        List<Participant> participants = new java.util.ArrayList<>(names.stream()
                .map(name -> (Participant) new Player(name, new Hand()))
                .toList());
        participants.add(new Dealer(new Hand()));
        return new Participants(participants);
    }
}

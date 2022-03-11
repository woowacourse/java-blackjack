package blackjack.model.blackjack;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.cards.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Name;
import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Blackjack {

    private CardDispenser cardDispenser;
    private Participants participants;

    public Blackjack(CardDispenser cardDispenser, Name... names) {
        this.cardDispenser = cardDispenser;
        this.participants = new Participants(createDealer(), createPlayers(cardDispenser, names));
    }

    private Dealer createDealer() {
        return new Dealer(cardDispenser.issue(), cardDispenser.issue());
    }

    private List<Player> createPlayers(CardDispenser cardDispenser, Name... names) {
        return Arrays.stream(names)
            .map(name -> createPlayer(cardDispenser, name))
            .collect(toUnmodifiableList());
    }

    private Player createPlayer(CardDispenser cardDispenser, Name name) {
        return new Player(name, cardDispenser.issue(), cardDispenser.issue());
    }

    public void dealerTakeCard() {
        participants.takeCardByName(Dealer.dealerName(), cardDispenser.issue());
    }

    public void playerTakeCard(Name name) {
        participants.takeCardByName(name, cardDispenser.issue());
    }

    public Records records() {
        return new Records(recordsMap());
    }

    private Map<Name, Record> recordsMap() {
        return participants.records().stream()
            .collect(toMap(Record::name, record -> record));
    }

    public boolean isDealerHittable() {
        return participants.isHittableByName(Dealer.dealerName());
    }

    public boolean isPlayerHittable(Name name) {
        return participants.isHittableByName(name);
    }

    public Cards dealerCards() {
        return participants.takenCardsByName(Dealer.dealerName());
    }
}

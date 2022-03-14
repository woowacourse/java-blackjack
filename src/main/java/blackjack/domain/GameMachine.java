package blackjack.domain;

import blackjack.domain.card.Card;
<<<<<<< HEAD
import blackjack.domain.card.Deck;
=======
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckGeneratorImpl;
>>>>>>> step1
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Players;

import java.util.List;
import java.util.stream.Collectors;

public class GameMachine {

    private final Deck deck;
    private final Players players;

    public GameMachine(final List<String> names) {
        validationNames(names);
<<<<<<< HEAD
        this.deck = new Deck();
=======
        this.deck = new Deck(new DeckGeneratorImpl());
>>>>>>> step1
        this.players = new Players(createParticipants(names), createDealer());
    }

    private void validationNames(final List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 참여자의 이름을 입력해주세요.");
        }
    }

    public List<Participant> createParticipants(final List<String> names) {
        return names.stream()
<<<<<<< HEAD
                .map(name -> new Participant(deck.initDistributeCard(), name))
=======
                .map(name -> new Participant(Cards.createInitCards(deck), name))
>>>>>>> step1
                .collect(Collectors.toList());
    }

    private Dealer createDealer() {
<<<<<<< HEAD
        return new Dealer(deck.initDistributeCard());
=======
        return new Dealer(Cards.createInitCards(deck));
>>>>>>> step1
    }

    public Card playDraw() {
        return deck.draw();
    }

    public boolean isDealerGetCard() {
        if (players.getDealer().acceptableCard()) {
            players.addDealerCard(deck.draw());
            return true;
        }
        return false;
    }

    public Players getPlayers() {
        return this.players;
    }

    public List<Participant> getParicipants() {
        return players.getParticipants();
    }
}

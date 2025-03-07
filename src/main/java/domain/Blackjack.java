package domain;

import domain.dto.NameAndCards;
import java.util.List;
import java.util.Map;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    // 초기 카드 분배
    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public NameAndCards openDealerCards() {
        Player dealer = getDealer();
        return new NameAndCards(dealer.getName(), dealer.openInitialCards());
    }

    public List<NameAndCards> openParticipantsCards() {
        return getParticipants().stream()
                .map(participant -> new NameAndCards(participant.getName(), participant.openInitialCards()))
                .toList();
    }

    // 추가 카드 분배
    public NameAndCards addCardToCurrentParticipant(String name) {
        Player participant = players.getPlayerByName(name);
        participant.drawOneCard(deck);
        return new NameAndCards(
                participant.getName(),
                participant.getCards().getCards()
        );
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public NameAndCards getNameAndCardsByName(String name) {
        Player player = players.getPlayerByName(name);
        return new NameAndCards(
                player.getName(),
                player.getCards().getCards()
        );
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
    }

    public NameAndCards getDealerNameAndCards() {
        Player dealer = getDealer();
        return new NameAndCards(dealer.getName(), dealer.getCards().getCards());
    }

    public List<NameAndCards> getParticipantsNameAndCards() {
        return getParticipants().stream()
                .map(participant -> new NameAndCards(participant.getName(), participant.getCards().getCards()))
                .toList();
    }

    public List<String> getParticipantNames() {
        return getParticipants().stream()
                .map(Player::getName)
                .toList();
    }

    private Dealer getDealer() {
        return players.getDealer();
    }

    private List<Participant> getParticipants() {
        return players.getParticipants()
                .stream()
                .map(player -> (Participant) player)
                .toList();
    }
}

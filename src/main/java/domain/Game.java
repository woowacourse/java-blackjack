package domain;

import controller.dto.HandStatus;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Participant participant;
    private final Deck deck;

    public Game(final Dealer dealer, final List<String> playerNames) {
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .toList();
        this.participant = new Participant(dealer, players);
        deck = new Deck();
    }

    public Game(final Participant participant, final Deck deck) {
        this.participant = participant;
        this.deck = deck;
    }

    public List<HandStatus> initiateGameCondition() {
        List<HandStatus> status = new ArrayList<>();
        Dealer dealer = participant.dealer();
        status.add(new HandStatus(dealer.getName(), new Hand(pickTwoCards(dealer))));
        for (Player player : participant.players()) {
            status.add(new HandStatus(player.getName(), new Hand(pickTwoCards(player))));
        }
        return status;
    }

    private List<Card> pickTwoCards(final Player player) {
        player.drawCard(deck.pick());
        player.drawCard(deck.pick());
        return player.getHand().getCards();
    }

    public HandStatus pickOneCard(final String name) {
        List<Player> players = participant.players();
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        foundPlayer.drawCard(deck.pick());
        return new HandStatus(foundPlayer.getName(), foundPlayer.getHand());
    }

    public List<String> getPlayerNames() {
        List<Player> players = participant.players();
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public HandStatus getCurrentCardStatus(final String name) {
        List<Player> players = participant.players();
        Player foundPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        return new HandStatus(foundPlayer.getName(), foundPlayer.getHand());
    }

    // TODO: 매직 넘버 상수화
    public int giveCardsToDealer() {
        Dealer dealer = participant.dealer();
        int threshold = 16;
        int currentScore = dealer.calculateResultScore(21);

        int count = 0;
        while (currentScore <= threshold) {
            dealer.drawCard(deck.pick());
            currentScore = dealer.calculateResultScore(21);
            count++;
        }
        return count;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Player getPlayer(final String name) {
        return participant.players()
                .stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}

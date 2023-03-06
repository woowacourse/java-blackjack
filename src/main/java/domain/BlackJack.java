package domain;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.strategy.ShuffleStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJack {
    private final Deck deck;
    private final List<Player> players;

    public BlackJack(String participantNames, ShuffleStrategy shuffleStrategy) {
        this.deck = Deck.from(shuffleStrategy);
        this.players = initPlayers(participantNames);
    }

    private List<Player> initPlayers(String playerNames) {
        List<Player> players = new ArrayList<>(List.of(new Dealer()));
        players.addAll(initParticipants(playerNames));
        return players;
    }

    private List<Participant> initParticipants(String playerNames) {
        return Arrays.stream(playerNames.split(","))
                .map(Participant::new)
                .collect(Collectors.toUnmodifiableList());
    }

    public void giveTwoCardToPlayers() {
        for (Player player : players) {
            giveTwoCardToPerPlayer(player);
        }
    }

    private void giveTwoCardToPerPlayer(Player player) {
        for (int divideCardCount = 0; divideCardCount < 2; divideCardCount++) {
            player.addCard(findAnyOneCard());
        }
    }

    private Card findAnyOneCard() {
        return deck.draw();
    }

    public Map<Player, List<Card>> getPlayersCards() {
        HashMap<Player, List<Card>> cardsPerPlayer = new HashMap<>();
        putPlayerCards(cardsPerPlayer);
        return cardsPerPlayer;
    }

    private void putPlayerCards(HashMap<Player, List<Card>> cardsPerPlayer) {
        players.forEach(player -> cardsPerPlayer.put(player, player.getCards()));
    }

    public void giveCard(String playerName) {
        players.stream()
                .filter(player -> player.isNameEqualTo(playerName))
                .forEach(player -> player.addCard(deck.draw()));
    }

    public List<Card> getCardsFrom(String playerName) {
        return players.stream()
                .filter(player -> player.isNameEqualTo(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이름입니다."))
                .getCards();
    }

    public boolean shouldDealerGetCard() {
        return getDealer().getTotalScore() <= 16;
    }

    public void giveDealerCard() {
        getDealer().addCard(findAnyOneCard());
    }

    public Player getDealer() {
        return players.get(0);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }

    public List<Player> getParticipants() {
        List<Player> players = new ArrayList<>(getPlayers());
        players.remove(0);
        return players;
    }

    public void battle() {
        for (Player participant : getParticipants()) {
            participant.battle(getDealer());
            getDealer().battle(participant);
        }
    }
}

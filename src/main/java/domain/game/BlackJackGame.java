package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.strategy.ShuffleStrategy;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BlackJackGame {
    private static final int INIT_GIVE_CARD_COUNT = 2;
    private static final int DEALER_GIVE_CARD_STATE_MAX_SCORE = 16;
    
    private final Deck deck;
    private final List<Player> players;

    public BlackJackGame(String participantNames, ShuffleStrategy shuffleStrategy) {
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
        for (int divideCardCount = 0; divideCardCount < INIT_GIVE_CARD_COUNT; divideCardCount++) {
            player.addCard(findAnyOneCard());
        }
    }

    private Card findAnyOneCard() {
        return deck.draw();
    }

    public void giveCard(Player player) {
        player.addCard(deck.draw());
    }

    public boolean shouldDealerGetCard() {
        return getDealer().getTotalScore() <= DEALER_GIVE_CARD_STATE_MAX_SCORE;
    }

    public void giveDealerCard() {
        getDealer().addCard(findAnyOneCard());
    }
    
    public Player getDealer() {
        return players.stream()
                .filter(Player::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 존재하지 않습니다."));
    }
    
    public List<Player> getParticipants() {
        return players.stream()
                .filter(Predicate.not(Player::isDealer))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}

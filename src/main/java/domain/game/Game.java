package domain.game;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {
    private static final int MAX_PLAYER_COUNT = 5;

    private final Dealer dealer;
    private final List<Player> players = new ArrayList<>();

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        validatePlayerNames(playerNames);
        playerNames.forEach(this::registerPlayer);
    }

    public void playerHit(String playerName) {
        Card card = dealer.pickCard();
        findPlayerByName(playerName).hit(card);
    }

    public void dealerHit() {
        Card card = dealer.pickCard();
        dealer.hit(card);
    }

    public boolean doesDealerNeedCard() {
        return dealer.doesNeedCard();
    }

    public boolean canHit(String playerName) {
        return findPlayerByName(playerName).canHit();
    }

    public List<GameParticipant> getParticipants() {
        List<GameParticipant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);
        return participants;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getPlayerCards(String playerName) {
        return findPlayerByName(playerName).getCards();
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(GameParticipant::getName)
                .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    private void registerPlayer(String playerName) {
        CardHand initialDeal = dealer.pickInitialDeal();
        Player player = new Player(playerName, initialDeal);
        players.add(player);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerNames(List<String> playerNames) {
        if (playerNames.contains("딜러")) {
            throw new IllegalArgumentException("[ERROR] '딜러'는 플레이어 이름으로 사용할 수 없습니다.");
        }
    }

    private GameParticipant findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }
}

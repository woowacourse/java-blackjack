package domain.game;

import domain.card.Card;
import domain.card.CardHand;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.GameParticipant;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Game {
    private static final int MAX_PLAYER_COUNT = 5;

    private final Dealer dealer;
    private final Players players;

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        this.players = new Players();
        playerNames.forEach(this::registerPlayer);
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

    public void playerHit(String playerName) {
        Card card = dealer.pickCard();
        players.findPlayerByName(playerName).hit(card);
    }

    public void dealerHit() {
        Card card = dealer.pickCard();
        dealer.hit(card);
    }

    public boolean canHit(String playerName) {
        return players.findPlayerByName(playerName).canHit();
    }

    public boolean doesDealerNeedCard() {
        return dealer.doesNeedCard();
    }

    private void registerPlayer(String playerName) {
        CardHand initialDeal = dealer.pickInitialDeal();
        Player player = new Player(playerName, initialDeal);
        players.addPlayer(player);
    }

    public List<GameParticipant> getParticipants() {
        List<GameParticipant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public List<Card> getPlayerCards(String playerName) {
        return players.findPlayerByName(playerName).getCards();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Dealer getDealer() {
        return dealer;
    }
}

package blackjack.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackGame(final CardDeck cardDeck, Dealer dealer, final List<Player> players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public static BlackjackGame createByPlayerNames(final List<String> names) {
        CardDeck cardDeck = CardDeck.createCardDeck();
        List<Player> players = new ArrayList<>();

        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }
        return new BlackjackGame(cardDeck, new Dealer(), players);
    }

    public void initCardsToParticipants() {
        List<Participant> participants = new ArrayList<>(players);
        participants.add(dealer);
        for (Participant participant : participants) {
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }

    public void addExtraCard(final Participant participant) {
        Card card = cardDeck.pickRandomCard();
        participant.addCards(card);
    }

    public boolean addExtraCardToDealer() {
        if (dealer.isPossibleToAdd()) {
            addExtraCard(dealer);
            return true;
        }
        return false;
    }

    public Map<Player, GameResult> calculateStatisticsForPlayer() {
        Map<Player, GameResult> playerResult = new HashMap<>();
        for (Player player : players) {
            GameResult gameResult = GameResult.playerResultFrom(dealer, player);
            playerResult.put(player, gameResult);
        }
        return playerResult;
    }

    public Map<GameResult, Integer> calculateStatisticsForDealer() {
        Map<GameResult, Integer> result = new HashMap<>();

        for (Player player : players) {
            GameResult playerResult = GameResult.playerResultFrom(dealer, player);
            GameResult dealerResult = playerResult.changeStatusOpposite();
            result.put(dealerResult, result.getOrDefault(dealerResult, 0) + 1);
        }
        return result;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

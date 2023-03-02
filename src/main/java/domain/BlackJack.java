package domain;

import domain.card.Card;
import domain.card.CardRepository;
import domain.player.Dealer;
import domain.player.Participant;
import domain.player.Player;
import domain.strategy.IndexGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJack {
    private final CardRepository cardRepository;
    private final List<Player> players;

    public BlackJack(String playerNames, IndexGenerator indexGenerator) {
        this.cardRepository = CardRepository.create(indexGenerator);
        this.players = initPlayers(playerNames);
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

    public void startGame() {
        giveCardToPlayers();
    }

    private void giveCardToPlayers() {
        for (Player player : players) {
            giveCardToPerPlayer(player);
        }
    }

    private void giveCardToPerPlayer(Player player) {
        for (int divideCardCount = 0; divideCardCount < 2; divideCardCount++) {
            Card card = cardRepository.findAnyOneCard();
            player.addCard(card);
        }
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
                .forEach(player -> player.addCard(cardRepository.findAnyOneCard()));
    }

    public List<Card> getCardsFrom(String playerName) {
        return players.stream()
                .filter(player -> player.isNameEqualTo(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이름입니다."))
                .getCards();
    }
}

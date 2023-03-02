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

    public BlackJack(String playerNames) {
        this.cardRepository = CardRepository.create();
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

    public void startGame(IndexGenerator indexGenerator) {
        giveCardToPlayers(indexGenerator);
    }

    private void giveCardToPlayers(IndexGenerator indexGenerator) {
        for (Player player : players) {
            giveCardToPerPlayer(indexGenerator, player);
        }
    }

    private void giveCardToPerPlayer(IndexGenerator indexGenerator, Player player) {
        for (int divideCardCount = 0; divideCardCount < 2; divideCardCount++) {
            Card card = cardRepository.findCardByIndex(indexGenerator.generate(cardRepository.size()));
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
}

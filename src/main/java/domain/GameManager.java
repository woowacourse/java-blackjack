package domain;

import dto.ParticipantCardsDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public GameManager(List<Player> players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    public Dealer getDealer() {
        return dealer;
    }

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    public void distributeInitialCards() {
        distributeCardToDealer(dealer);
        distributeCardToPlayers(players);
    }

    private void distributeCardToDealer(Dealer dealer) {
        distributeInitialCards(dealer);
    }

    private void distributeCardToPlayers(List<Player> players) {
        for (Player player : players) {
            distributeInitialCards(player);
        }
    }

    private void distributeInitialCards(Participant participant) {
        drawCardTo(participant);
        drawCardTo(participant);
    }

    public ParticipantCardsDto getDealerDto() {
        return dealer.getParticipantCardsDto();
    }

    public List<ParticipantCardsDto> getPlayerDtos() {
        List<ParticipantCardsDto> participantCardsDtos = new ArrayList<>();
        for (Player player : players) {
            participantCardsDtos.add(player.getParticipantCardsDto());
        }

        return participantCardsDtos;
    }

    public void drawCardTo(Participant participant) {
        Card card = deck.drawCard();
        participant.receiveCard(card);
    }

    public Map<String, GameResult> getGameResult() {
        Map<String, GameResult> gameResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = player.judgeResult(this.dealer);
            gameResult.put(player.getName(), result);
        }
        return gameResult;
    }
}

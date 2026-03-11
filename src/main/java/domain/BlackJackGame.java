package domain;

import dto.ParticipantCardsDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private final Deck deck;
    private final List<Player> players;
    private final Dealer dealer;

    public BlackJackGame(Deck deck, List<Player> players, Dealer dealer) {
        this.deck = deck;
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeInitialCards() {
        distributeInitialCard(dealer);
        players.forEach(this::distributeInitialCard);
    }

    public void playGameWithDealer() {
        distributeCard(dealer);
    }

    public void playGameWithPlayer(Player player) {
        distributeCard(player);
    }

    public Map<String, Boolean> getGameResult() {
        Map<Participant, Integer> participantScores = getParticipantScores();
        Map<String, Boolean> gameResult = Result.calculateResult(participantScores);
        return gameResult;
    }

    public ParticipantCardsDto getDealerCardsDto() {
        return dealer.getParticipantCardsDto();
    }

    public ParticipantCardsDto getPlayerCardsDto(Player player) {
        return player.getParticipantCardsDto();
    }

    public boolean canPlayerReceiveCard(Player player) {
        return player.canReceiveCard();
    }

    public boolean canDealerReceiveCard(Dealer dealer) {
        return dealer.canReceiveCard();
    }

    private void distributeInitialCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCard(participant);
        }
    }

    private void distributeCard(Participant participant) {
        Card card = deck.distributeCard();
        participant.receiveCard(card);
    }

    private Map<Participant, Integer> getParticipantScores() {
        Map<Participant, Integer> participantScores = new HashMap<>();
        participantScores.put(dealer, dealer.getScore());
        players.forEach(player -> participantScores.put(player, player.getScore()));
        return participantScores;
    }
}

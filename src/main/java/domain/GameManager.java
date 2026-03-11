package domain;

import dto.ParticipantCardsDto;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static final int MAX_PLAYER = 8;
    private static final int BURST_THRESHOLD = 21;

    private final Dealer dealer;
    private final List<Player> players;
    private final Deck deck;

    public GameManager(List<Player> players) {
        this.dealer = initDealer();
        this.players = players;
        this.deck = new Deck();
    }

    public void distributeInitialCards() {
        distributeCardToDealer(dealer, deck);
        distributeCardToPlayers(players, deck);
    }

    private void distributeCardToDealer(Dealer dealer, Deck deck) {
        distributeInitialCards(dealer, deck);
    }

    private void distributeCardToPlayers(List<Player> players, Deck deck) {
        for (Player player : players) {
            distributeInitialCards(player, deck);
        }
    }

    private void distributeInitialCards(Participant participant, Deck deck) {
        distributeCard(participant, deck);
        distributeCard(participant, deck);
    }


    private void distributeCard(Participant participant, Deck deck) {
        Card card = deck.drawCardFromDeck();
        participant.receiveCard(card);
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

    private Dealer initDealer() {
        Name name = new Name("딜러");
        Dealer dealer = new Dealer(name);
        return dealer;
    }

    public static boolean isOverBurstThreshold(int score) {
        if (score > BURST_THRESHOLD) {
            return true;
        }
        return false;
    }

    public static void validatePlayersNumber(List<String> playerNames) {
        validateMinimumPlayers(playerNames);
        validateMaximumPlayers(playerNames);
    }

    private static void validateMaximumPlayers(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER) {
            throw new IllegalArgumentException("플레이어의 수는 8명을 초과할 수 없습니다.");
        }
    }

    private static void validateMinimumPlayers(List<String> playerNames) {
        if (playerNames.isEmpty()) {
            throw new IllegalArgumentException("플레이어의 수는 1명 이상이어야 합니다.");
        }
    }
}

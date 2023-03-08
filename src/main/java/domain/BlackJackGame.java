package domain;

import domain.card.Deck;
import domain.participant.Participants;
import domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    public static final int BLACKJACK_NUMBER = 21;
    public static final int DEALER_REPEAT_NUMBER = 16;

    private final Deck deck;
    private Participants participants;

    public BlackJackGame(List<String> playerNames) {
        this.deck = new Deck();
        deck.shuffleDeck();
        initializeParticipants(playerNames, deck);
    }

    private void initializeParticipants(List<String> playerNames, Deck deck) {
        try {
            this.participants = new Participants(playerNames, deck);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<Player, WinningStatus> calculatePlayersResult() {
        Map<Player, WinningStatus> playersResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            WinningStatus playerWinningStatus = decideWinningStatus(player, participants.getDealer().calculateScore());
            playersResult.put(player, playerWinningStatus);
        }
        return playersResult;
    }

    public Map<WinningStatus, Integer> calculateDealerResult(Map<Player, WinningStatus> playersResult) {
        Map<WinningStatus, Integer> dealerResult = new HashMap<>();
        for (WinningStatus playerWinningStatus : playersResult.values()) {
            dealerResult.put(playerWinningStatus.reverse(),
                    dealerResult.getOrDefault(playerWinningStatus.reverse(), 0) + 1);
        }
        return dealerResult;
    }

    private WinningStatus decideWinningStatus(final Player player, int dealerScore) {
        int playerScore = player.calculateScore();
        if (dealerScore > BLACKJACK_NUMBER) {
            return decideWinningStatusDealerBust(playerScore);
        }
        return decideWinningStatusDealerNotBust(dealerScore, playerScore);
    }

    private WinningStatus decideWinningStatusDealerNotBust(int dealerScore, int score) {
        if (score <= BLACKJACK_NUMBER && score > dealerScore) {
            return WinningStatus.WIN;
        }
        if (score == dealerScore) {
            return WinningStatus.TIE;
        }
        return WinningStatus.LOSE;
    }

    private WinningStatus decideWinningStatusDealerBust(int score) {
        if (score > BLACKJACK_NUMBER) {
            return WinningStatus.TIE;
        }
        return WinningStatus.WIN;
    }

    public Participants getParticipants() {
        return participants;
    }

    public Deck getDeck() {
        return deck;
    }
}

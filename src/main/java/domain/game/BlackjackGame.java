package domain.game;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.Dealer;
import domain.participant.DealerWinStatus;
import domain.participant.ParticipantName;
import domain.participant.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다.";

    private final Participants participants;
    private final Deck deck;
    public BlackjackGame(List<ParticipantName> names, Deck deck) {
        validatePlayerSize(names.size());
        this.deck = deck;
        this.participants = new Participants(names, new Dealer());
        initiateGame();
    }

    private void validatePlayerSize(int playerSize) {
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new IllegalArgumentException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
    }

    private void initiateGame() {
        for (ParticipantName name : participants.getPlayerNames()) {
            dealCard(name);
            dealCard(name);
        }
        dealDealerCard();
        dealDealerCard();
    }

    public List<TrumpCard> playerCards(ParticipantName name) {
        return participants.playerCards(name);
    }

    public TrumpCard dealerCardFirst() {
        return dealerCards().getFirst();
    }

    public List<TrumpCard> dealerCards() {
        return participants.dealerCards();
    }

    public ParticipantName dealerName() {
        return participants.dealerName();
    }

    public List<ParticipantName> playerNames() {
        return participants.getPlayerNames();
    }

    public void dealCard(ParticipantName name) {
        participants.addCard(name, deck.drawCard());
    }

    private void dealDealerCard() {
        participants.dealDealerCard(deck.drawCard());
    }

    public boolean isBust(ParticipantName name) {
        return participants.isBust(name);
    }

    public void dealerHit() {
        if (dealerDrawable()) {
            ParticipantName dealerName = participants.dealerName();
            participants.addCard(dealerName, deck.drawCard());
        }
    }

    public boolean dealerDrawable() {
        ParticipantName dealerName = participants.dealerName();
        return participants.isDrawable(dealerName);
    }

    public List<GameResult> currentPlayerBlackjackResult() {
        return participants.calculatePlayerResults();
    }

    public GameResult currentDealerBlackjackResult() {
        ParticipantName dealerName = participants.dealerName();
        return participants.playerResult(dealerName);
    }

    public Map<ParticipantName, WinStatus> getPlayerWinStatuses() {
        Map<ParticipantName, WinStatus> winStatuses = new HashMap<>();
        List<ParticipantName> playerNames = participants.getPlayerNames();

        for (ParticipantName playerName : playerNames) {
            WinStatus winStatus = participants.determinePlayerResult(playerName);
            winStatuses.put(playerName, winStatus);
        }
        return winStatuses;
    }

    public DealerWinStatus calculateDealerWinStatus(Map<ParticipantName, WinStatus> playerWinStatuses) {
        int playerCount = playerWinStatuses.size();
        int winCount = playerCount - countWinStatus(playerWinStatuses, WinStatus.LOSE);
        int loseCount = playerCount - countWinStatus(playerWinStatuses, WinStatus.WIN);

        return new DealerWinStatus(winCount, loseCount);
    }

    private int countWinStatus(Map<ParticipantName, WinStatus> playerWinStatues, WinStatus winStatus) {
        return (int) playerWinStatues.values()
                .stream()
                .filter(status -> status.equals(winStatus))
                .count();
    }

}

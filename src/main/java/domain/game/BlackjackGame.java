package domain.game;

import domain.card.Deck;
import domain.card.TrumpCard;
import domain.participant.ParticipantName;
import domain.participant.Participants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private static final int MAX_PEOPLE_WITHOUT_DEALER = 7;
    private static final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 1-7명만 이용하실 수 있습니다.";

    private final Participants participants;
    private final Deck deck;

    public BlackjackGame(List<String> names, List<Integer> betAmounts, Deck deck) {
        validatePlayerSize(names.size());
        this.deck = deck;
        this.participants = new Participants(names, betAmounts, deck);
    }

    private void validatePlayerSize(int playerSize) {
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new IllegalArgumentException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
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

    public void dealerHit() {
        if (dealerDrawable()) {
            participants.dealDealerCard(deck.drawCard());
        }
    }

    public boolean dealerDrawable() {
        return participants.isDealerDrawable();
    }

    public List<GameResult> currentPlayerBlackjackResult() {
        return participants.calculatePlayerResults();
    }

    public GameResult currentDealerBlackjackResult() {
        return participants.dealerResult();
    }

    public Map<String, Integer> getPlayersProfit() {
        Map<String, Integer> playersProfit = new HashMap<>();
        for (ParticipantName playerName : participants.getPlayerNames()) {
            playersProfit.put(playerName.name(), participants.getPlayerProfit(playerName));
        }
        return playersProfit;
    }

    public Map<String, Integer> getDealerProfit(Map<String, Integer> playersProfit) {
        Map<String, Integer> dealerProfit = new HashMap<>();
        int playerProfitSum = playersProfit.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        dealerProfit.put(dealerName().name(), playerProfitSum * -1);
        return dealerProfit;
    }

    public void stayPlayer(ParticipantName name) {
        participants.stayPlayer(name);
    }

    public boolean isFinished(ParticipantName name) {
        return participants.isFinished(name);
    }
}

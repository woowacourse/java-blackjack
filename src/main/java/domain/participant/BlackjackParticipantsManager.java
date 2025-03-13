package domain.participant;

import domain.blackjackgame.TrumpCard;
import domain.except.BlackJackStateException;
import exception.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackjackParticipantsManager {

    private static final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";
    private static final String INVALID_HANDS_STATE = "아직 카드를 받지 않은 참여자입니다.";
    private static final String DUPLICATE_NAME = "중복된 이름이 존재합니다.";

    private final List<BlackjackParticipant> players;
    private final BlackjackParticipant dealer;
    
    public BlackjackParticipantsManager(List<BlackjackParticipant> players, BlackjackParticipant dealer) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
        validateDuplicateName();
    }

    private void validateDuplicateName() {
        List<String> playerNames = getPlayerNames();
        Set<String> duplicateNames = new HashSet<>(playerNames);
        if (duplicateNames.size() != playerNames.size()) {
            throw new BlackJackException(DUPLICATE_NAME);
        }
    }

    private BlackjackParticipant findPlayer(String name) {
        return players.stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlackJackException(INVALID_PLAYER));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(BlackjackParticipant::name)
                .collect(Collectors.toList());
    }

    public List<TrumpCard> playerCards(String name) {
        return Collections.unmodifiableList(findPlayer(name).cardHands());
    }

    public List<TrumpCard> dealerCards() {
        return Collections.unmodifiableList(dealer.cardHands());
    }

    private void validateEmptyCards(List<TrumpCard> trumpCards) {
        if (trumpCards.isEmpty()) {
            throw new BlackJackStateException(INVALID_HANDS_STATE);
        }
    }

    public TrumpCard firstDealerCards() {
        validateEmptyCards(dealerCards());
        return dealer.cardHands().getFirst();
    }

    public int calculateCardSum(String name) {
        BlackjackParticipant player = findPlayer(name);
        return player.calculateCardSum();
    }

    public int calculateDealerSum() {
        return dealer.calculateCardSum();
    }

    public String dealerName() {
        return dealer.name();
    }

    public boolean isBust(String name) {
        BlackjackParticipant player = findPlayer(name);
        return player.isBust();
    }

    public void addCard(String name, TrumpCard trumpCard) {
        BlackjackParticipant player = findPlayer(name);
        player.addDraw(trumpCard);
    }

    public void addInitiateCardsToPlayer(String name, List<TrumpCard> trumpCards) {
        for (TrumpCard trumpCard : trumpCards) {
            addCard(name, trumpCard);
        }
    }

    public void addDealerCard(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public boolean dealerDrawable() {
        return dealer.isDrawable();
    }

    public void addInitiateCardsToDealer(List<TrumpCard> trumpCards) {
        for (TrumpCard trumpCard : trumpCards) {
            addDealerCard(trumpCard);
        }
    }

    public Map<String, Double> participantsBettingResult() {
        String dealerName = dealerName();
        Map<String, Double> bettingResult = new LinkedHashMap<>(Map.of(dealerName, (double) 0));
        for (String name : getPlayerNames()) {
            BlackjackParticipant player = findPlayer(name);
            double playerBetEarnMoney = player.earnMoney(dealer);
            double dealerEarnMoney = dealer.earnMoney(player);
            bettingResult.put(name, playerBetEarnMoney);
            bettingResult.put(dealerName, bettingResult.getOrDefault(dealerName, (double) 0) + dealerEarnMoney);
        }
        return bettingResult;
    }
}

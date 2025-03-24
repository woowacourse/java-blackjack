package user;

import card.Card;
import card.CardDeck;
import game.GameResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private static final int USER_MIN_COUNT = 2;
    private static final int USER_MAX_COUNT = 8;

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validate(participants);
        this.participants = new ArrayList<>(participants);
    }

    private void validate(List<Participant> participants) {
        long distinctUserCount = participants.stream()
                .map(Participant::getName)
                .distinct()
                .count();

        if (distinctUserCount != participants.size()) {
            throw new IllegalArgumentException("유저는 중복될 수 없습니다.");
        }
        if (participants.size() < USER_MIN_COUNT || participants.size() > USER_MAX_COUNT) {
            throw new IllegalArgumentException("유저는 " + USER_MIN_COUNT + "명 이상 " + USER_MAX_COUNT + "명 이하로 등록해야 합니다.");
        }
    }

    public Map<Participant, GameResult> calculateGameResults() {
        Map<Participant, GameResult> gameResult = new LinkedHashMap<>();
        Participant dealer = this.getDealer();

        if (dealer.isBust()) {
            this.participants.stream()
                    .filter(Participant::isPlayer)
                    .forEach(player -> putGameResultBust(player, gameResult));
            return gameResult;
        }
        this.participants.stream()
                .filter(Participant::isPlayer)
                .forEach(player -> gameResult.put(player, compareScore(player)));
        return gameResult;
    }

    public GameResult compareScore(Participant player) {
        Participant dealer = this.getDealer();
        int dealerScore = dealer.calculateScore();
        int playerScore = player.calculateScore();

        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (dealer.isBust() || dealerScore < playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        return compareSameScore(player);
    }

    private GameResult compareSameScore(Participant player) {
        Participant dealer = this.getDealer();
        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    private void putGameResultBust(Participant participant, Map<Participant, GameResult> gameResult) {
        if (participant.isBust()) {
            gameResult.put(participant, GameResult.LOSE);
            return;
        }
        if (participant.isBlackjack()) {
            gameResult.put(participant, GameResult.BLACKJACK);
            return;
        }
        gameResult.put(participant, GameResult.WIN);
    }

    public void drawCardAllParticipant(CardDeck cardDeck) {
        for (Participant participant : participants) {
            participant.drawCard(cardDeck.drawCard());
        }
    }

    public Participant getDealer() {
        return participants.stream()
                .filter(participant -> !participant.isPlayer())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 게임에 참여하지 않았습니다."));
    }

    public List<Participant> getPlayers() {
        return participants.stream()
                .filter(participant -> !participant.isPlayer())
                .toList();
    }

    public Map<String, List<Card>> getPlayersAllCard() {
        Map<String, List<Card>> firstPlayersCard = new HashMap<>();
        List<Participant> players = this.getPlayers();
        for (Participant player : players) {
            List<Card> cards = player.openAllCard();
            firstPlayersCard.put(player.getName(), cards);
        }
        return firstPlayersCard;
    }

    public int calculateScore(Participant participant) {
        return participant.calculateScore();
    }
}

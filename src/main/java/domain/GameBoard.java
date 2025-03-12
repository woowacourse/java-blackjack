package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.BattleResult;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Score;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Participant, CardDeck> cardDeckOfParticipant;
    private final CardDeck playingCard;

    public GameBoard(final List<Participant> participants) {
        this.playingCard = CardDeck.generateFullPlayingCard();
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    public static GameBoard generateOf(Dealer dealer, List<Player> players) {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players);

        return new GameBoard(participants);
    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck cardDeck = entry.getValue();

            Card firstCard = playingCard.draw();
            Card secondCard = playingCard.draw();
            cardDeck.addCard(firstCard);
            cardDeck.addCard(secondCard);
        }
    }

    public void drawCardTo(Participant participant) {
        Card drawedCard = playingCard.draw();
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        ownedCardDeck.addCard(drawedCard);
    }

    public boolean ableToDraw(Participant participant) {
        Score score = getScoreOf(participant);
        return participant.ableToDraw(score.getScore());
    }

    public void shufflePlayingCard() {
        playingCard.shuffle();
    }

    public Score getScoreOf(Participant participant) {
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        return ownedCardDeck.getScore();
    }

    public void calculateBattleResult() {
        Entry<Participant, CardDeck> cardDeckOfDealer = cardDeckOfParticipant.entrySet()
                .stream()
                .filter(entry -> entry.getKey().areYouDealer())
                .findFirst()
                .orElseThrow();

        Participant dealer = cardDeckOfDealer.getKey();
        Score dealerScore = getScoreOf(dealer);

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            updateBattleResultBetween(dealer, participant, dealerScore);
        }
    }

    public CardDeck getCardDeckOf(Participant participant) {
        CardDeck cardDeck = cardDeckOfParticipant.get(participant);
        return new CardDeck(cardDeck);
    }

    public Map<Participant, CardDeck> getCardDeckOfParticipant() {
        return Collections.unmodifiableMap(cardDeckOfParticipant);
    }

    public CardDeck getPlayingCard() {
        return playingCard;
    }

    public List<Participant> getParticipants() {
        return cardDeckOfParticipant.keySet().stream()
                .toList();
    }

    public List<Participant> getPlayers() {
        return cardDeckOfParticipant.keySet().stream()
                .filter(participant -> !participant.areYouDealer())
                .toList();
    }

    public Participant getDealer() {
        return cardDeckOfParticipant.keySet().stream()
                .filter(Participant::areYouDealer)
                .findFirst()
                .orElseThrow();
    }

    private Map<Participant, CardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> CardDeck.generateEmptySet(),
                        (existing, replacement) -> replacement,
                        LinkedHashMap::new));

    }

    private void updateBattleResultBetween(Participant dealer, Participant participant, Score dealerScore) {
        if (participant.areYouDealer()) {
            return;
        }

        Score playerScore = getScoreOf(participant);

        if (playerScore.isBust()) {
            // 무조건 플레이어 패배 (베팅 금액 잃음)
            updateBattleResult(dealer, participant);
            return;
        }

        if (playerScore.isBlackJack() && !dealerScore.isBlackJack()) {
            // 플레이어 1.5배
            updateBattleResult(participant, dealer);
            return;
        }

        if (playerScore.getScore() > dealerScore.getScore()) {
            // 플레이어 승리(1배)
            updateBattleResult(participant, dealer);
            return;
        }

        if (playerScore.getScore() < dealerScore.getScore()) {
            // 플레이어 패배(베팅 금액 잃음)
            updateBattleResult(dealer, participant);
            return;
        }

        // 무승부
        updateBattleResultDraw(dealer, participant);
    }

    private void updateBattleResultDraw(Participant dealer, Participant player) {
        dealer.addGameRecord(BattleResult.DRAW);
        player.addGameRecord(BattleResult.DRAW);
    }

    private void updateBattleResult(Participant winner, Participant loser) {
        winner.addGameRecord(BattleResult.WIN);
        loser.addGameRecord(BattleResult.LOSE);
    }
}

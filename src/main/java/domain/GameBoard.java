package domain;

import domain.card.Card;
import domain.card.CardDeck;
import domain.participant.BattleResult;
import domain.participant.Participant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameBoard {
    private final Map<Participant, CardDeck> cardDeckOfParticipant;
    private final CardDeck gameCardDeck;

    public GameBoard(final List<Participant> participants) {
        this.gameCardDeck = CardDeck.generateFullSet();
        this.cardDeckOfParticipant = initializeCardDeckOfParticipant(participants);
    }

    private Map<Participant, CardDeck> initializeCardDeckOfParticipant(final List<Participant> participants) {
        return participants.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        participant -> CardDeck.generateEmptySet(),
                        (existing, replacement) -> replacement,
                        LinkedHashMap::new));

    }

    public void drawTwoCards() {
        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            CardDeck cardDeck = entry.getValue();

            Card firstCard = gameCardDeck.draw();
            Card secondCard = gameCardDeck.draw();
            cardDeck.addCard(firstCard);
            cardDeck.addCard(secondCard);
        }
    }

    public void drawCardTo(Participant participant) {
        Card drawedCard = gameCardDeck.draw();
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        ownedCardDeck.addCard(drawedCard);
    }

    public CardDeck getCardDeckOf(Participant participant) {
        CardDeck cardDeck = cardDeckOfParticipant.get(participant);
        return new CardDeck(cardDeck);
    }

    public Map<Participant, CardDeck> getCardDeckOfParticipant() {
        return Collections.unmodifiableMap(cardDeckOfParticipant);
    }

    public CardDeck getPlayingCard() {
        return gameCardDeck;
    }

    public boolean ableToDraw(Participant participant) {
        int score = getScoreOf(participant);
        return participant.ableToDraw(score);
    }

    public void shufflePlayingCard() {
        gameCardDeck.shuffle();
    }

    public int getScoreOf(Participant participant) {
        CardDeck ownedCardDeck = cardDeckOfParticipant.get(participant);
        List<Card> ownedCards = ownedCardDeck.getCards();
        int totalScore = 0;
        int aceCounts = 0;
        for (Card card : ownedCards) {
            totalScore += card.getNumber();
            if (card.isAceCard()) {
                aceCounts++;
            }
        }
        while (aceCounts-- > 0) {
            if (totalScore + 10 <= 21) {
                totalScore += 10;
            }
        }
        return totalScore;
    }

    public void calculateBattleResult() {
        Entry<Participant, CardDeck> cardDeckOfDealer = cardDeckOfParticipant.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().areYouPlayer())
                .findFirst()
                .orElseThrow();

        Participant dealer = cardDeckOfDealer.getKey();
        int dealerScore = getScoreOf(dealer);

        for (Map.Entry<Participant, CardDeck> entry : cardDeckOfParticipant.entrySet()) {
            Participant participant = entry.getKey();
            if (!participant.areYouPlayer()) {
                continue;
            }

            int score = getScoreOf(participant);
            if (score > 21) {
                if (dealerScore > 21) {
                    updateBattleResultDraw(dealer, participant);
                    continue;
                }
                updateBattleResult(dealer, participant);
                continue;
            }

            if (score > dealerScore) {
                updateBattleResult(participant, dealer);
                continue;
            }

            if (score < dealerScore) {
                if (dealerScore > 21) {
                   updateBattleResult(participant, dealer);
                   continue;
                }
                updateBattleResult(dealer, participant);
            }

            // 드로우
            updateBattleResultDraw(dealer, participant);
        }
    }

    private void updateBattleResultDraw(Participant dealer, Participant player) {
        Map<BattleResult, Integer> dealerResult = dealer.getBattleResult();
        Map<BattleResult, Integer> playerResult = player.getBattleResult();
        dealerResult.merge(BattleResult.DRAW, 1, Integer::sum);
        playerResult.merge(BattleResult.DRAW, 1, Integer::sum);
    }

    private void updateBattleResult(Participant winner, Participant loser) {
        Map<BattleResult, Integer> winnerResult = winner.getBattleResult();
        Map<BattleResult, Integer> loserResult = loser.getBattleResult();
        winnerResult.merge(BattleResult.WIN, 1, Integer::sum);
        loserResult.merge(BattleResult.LOSE, 1, Integer::sum);
    }
}

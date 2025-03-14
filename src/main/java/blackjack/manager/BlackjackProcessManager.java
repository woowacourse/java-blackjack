package blackjack.manager;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.ParticipantResults;
import java.util.List;

public class BlackjackProcessManager {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;

    private final ParticipantResults participantResults;

    public BlackjackProcessManager(Deck deck, ParticipantResults participantResults) {
        this.deck = deck;
        this.participantResults = participantResults;
    }

    public void giveStartingCards(Participants participants) {
        participants.getParticipants().forEach(this::giveStartingCards);
    }

    private void giveStartingCards(Participant participant) {
        List<Card> cards = deck.takeCards(STARTING_CARD_SIZE);
        cards.forEach(participant::takeCard);
    }

    public void giveMoreCard(Participant participant) {
        List<Card> cards = deck.takeCards(ADDITIONAL_CARD_SIZE);
        cards.forEach(participant::takeCard);
    }

    public void calculateAllResults(Participants participants, GameRuleEvaluator gameRuleEvaluator) {
        Participant defender = participants.findDefender();

        for (Participant participant : participants.getParticipants()) {
            if (participant.equals(defender)) {
                continue;
            }
            calculateResult(defender, participant, gameRuleEvaluator);
        }
    }

    private void calculateResult(Participant defender, Participant challenger, GameRuleEvaluator gameRuleEvaluator) {
        boolean isDefenderBusted = gameRuleEvaluator.isBusted(defender);
        boolean isChallengerBusted = gameRuleEvaluator.isBusted(challenger);

        int challengerValue = challenger.getOptimisticValue();

        if (isDefenderBusted) {
            processWhenDefenderIsBusted(challenger, defender, isChallengerBusted, challengerValue);
            return;
        }

        if (isChallengerBusted) {
            saveResult(challenger, defender, GameResultType.LOSE, challengerValue);
            return;
        }

        GameResultType resultOfChallenger = decideResultOfChallenger(challenger, defender);
        saveResult(challenger, defender, resultOfChallenger, challengerValue);
    }

    private void processWhenDefenderIsBusted(Participant challenger, Participant defender, boolean isBustedPlayer,
                                             int playerValue) {
        if (isBustedPlayer) {
            saveResult(challenger, defender, GameResultType.TIE, playerValue);
            return;
        }

        saveResult(challenger, defender, GameResultType.WIN, playerValue);
    }

    private GameResultType decideResultOfChallenger(Participant challenger, Participant defender) {
        int playerValue = challenger.getOptimisticValue();
        int dealerValue = defender.getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    private void saveResult(Participant challenger, Participant defender, GameResultType resultTypeOfChallenger,
                            int challengerValue) {
        GameResultType resultTypeOfDefender = resultTypeOfChallenger.getOppositeType();

        ParticipantResult challengerResult = new ParticipantResult(challenger, resultTypeOfChallenger, challengerValue);
        ParticipantResult defenderResult = new ParticipantResult(defender, resultTypeOfDefender, challengerValue);

        participantResults.add(challengerResult);
        participantResults.add(defenderResult);
    }

    public ParticipantResults getParticipantResults() {
        return participantResults;
    }
}

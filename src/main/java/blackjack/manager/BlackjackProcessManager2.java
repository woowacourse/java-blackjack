package blackjack.manager;

import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.result.DealerResult;
import blackjack.domain.result.GameResultType;
import blackjack.domain.result.ParticipantResult;
import blackjack.domain.result.ParticipantResults;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.PlayersResults;
import java.util.List;

public class BlackjackProcessManager2 {

    private static final int STARTING_CARD_SIZE = 2;
    private static final int ADDITIONAL_CARD_SIZE = 1;

    private final Deck deck;
    private final PlayersResults playersResults;
    private final ParticipantResults participantResults;

    public BlackjackProcessManager2(Deck deck, PlayersResults playersResults, ParticipantResults participantResults) {
        this.deck = deck;
        this.playersResults = playersResults;
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

    public void calculateChallengerResult(Participants participants, GameRuleEvaluator gameRuleEvaluator) {
        Participant defender = participants.findDefender();

        for (Participant participant : participants.getParticipants()) {
            saveResult(defender, participant, gameRuleEvaluator);
        }
    }

    private void saveResult(Participant defender, Participant challenger, GameRuleEvaluator gameRuleEvaluator) {
        boolean isDefenderBusted = gameRuleEvaluator.isBusted(defender);
        boolean isChallengerBusted = gameRuleEvaluator.isBusted(challenger);

        int challengerValue = challenger.getOptimisticValue();

        if (isDefenderBusted) {
            processWhenDefenderIsBusted(challenger, isChallengerBusted, challengerValue);
            return;
        }

        if (isChallengerBusted) {
            saveChallengerResult(challenger, GameResultType.LOSE, challengerValue);
            return;
        }

        GameResultType resultOfChallenger = decideResultOfChallenger(challenger, defender);
        saveChallengerResult(challenger, resultOfChallenger, challengerValue);
    }

    private void processWhenDefenderIsBusted(Participant participant, boolean isBustedPlayer, int playerValue) {
        if (isBustedPlayer) {
            saveChallengerResult(participant, GameResultType.TIE, playerValue);
            return;
        }

        saveChallengerResult(participant, GameResultType.WIN, playerValue);
    }

    private GameResultType decideResultOfChallenger(Participant challenger, Participant defender) {
        int playerValue = challenger.getOptimisticValue();
        int dealerValue = defender.getOptimisticValue();

        return GameResultType.find(playerValue, dealerValue);
    }

    private void saveChallengerResult(Participant challenger, GameResultType gameResultOfPlayer,
                                      int challengerValue) {
        // TODO: 결과 저장시 카운트 관련해서 사용하는 것으로 바꾸기
        PlayerResult playerResult = new PlayerResult((Player) challenger, gameResultOfPlayer, challengerValue);
        ParticipantResult participantResult = new ParticipantResult(challenger, gameResultOfPlayer, challengerValue);
        participantResults.add(participantResult);
        playersResults.save(playerResult);
    }

    public DealerResult calculateDefenderResult(Participant participant) {
        int dealerValue = participant.getOptimisticValue();
        DealerResult dealerResult = new DealerResult(dealerValue);

        for (PlayerResult playerResult : playersResults.getAllResult()) {
            GameResultType gameResultOfPlayer = playerResult.getGameResultType();
            GameResultType gameResultOfDealer = gameResultOfPlayer.getOppositeType();

            dealerResult.addCountOf(gameResultOfDealer);
        }

        return dealerResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return playersResults.getAllResult();
    }
}

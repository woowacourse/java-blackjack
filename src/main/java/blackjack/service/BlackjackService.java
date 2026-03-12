package blackjack.service;

import blackjack.model.BustPolicy;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.DealerHitPolicy;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Player;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.ResultJudgement;
import blackjack.model.Score;
import blackjack.model.ScoreCalculator;

public class BlackjackService {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final CardsGenerator cardsGenerator;
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;
    private final ScoreCalculator scoreCalculator;
    private final ResultJudgement resultJudgement;

    public BlackjackService(CardsGenerator cardsGenerator, DealerHitPolicy dealerHitPolicy,
                            BustPolicy bustPolicy, ScoreCalculator scoreCalculator,
                            ResultJudgement resultJudgement) {
        this.cardsGenerator = cardsGenerator;
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.scoreCalculator = scoreCalculator;
        this.resultJudgement = resultJudgement;
    }

    public Deck createDeck() {
        return Deck.create(cardsGenerator);
    }

    public void initialDeal(Participants participants, Deck deck) {
        for (int i = 0; i < INITIAL_DEAL_COUNT; i++) {
            deal(participants, deck);
        }
    }

    private void deal(Participants participants, Deck deck) {
        for (Participant participant : participants) {
            participant.hit(deck.draw());
        }
    }

    public void hit(Participant participant, Deck deck) {
        participant.hit(deck.draw());
    }

    public boolean canHit(Player player) {
        Score score = calculateScoreOf(player);
        return !bustPolicy.isBust(score);
    }

    public boolean shouldHit(Dealer dealer) {
        Score score = calculateScoreOf(dealer);
        return dealerHitPolicy.shouldHit(score);
    }

    public PlayerBlackjackResult judge(Player player, Dealer dealer) {
        return resultJudgement.judge(calculateScoreOf(player), calculateScoreOf(dealer));
    }

    public Score calculateScoreOf(Participant participant) {
        return scoreCalculator.calculateOptimalTotalOf(participant.getHandSum(),
            participant.hasAce());
    }
}

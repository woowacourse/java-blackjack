package blackjack.service;

import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.BustPolicy;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.DealerHitPolicy;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.ResultJudgement;
import blackjack.model.Score;
import blackjack.model.ScoreCalculator;

public class BlackjackService {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final ScoreCalculator scoreCalculator;
    private final DealerHitPolicy dealerHitPolicy;
    private final BustPolicy bustPolicy;

    private final CardsGenerator cardsGenerator;

    public BlackjackService(ScoreCalculator scoreCalculator, DealerHitPolicy dealerHitPolicy, BustPolicy bustPolicy,
                            CardsGenerator cardsGenerator, ResultJudgement resultJudgement) {
        this.scoreCalculator = scoreCalculator;
        this.dealerHitPolicy = dealerHitPolicy;
        this.bustPolicy = bustPolicy;
        this.cardsGenerator = cardsGenerator;
        this.resultJudgement = resultJudgement;
    }

    private final ResultJudgement resultJudgement;

    public Deck createDeck() {
        return Deck.shuffled(cardsGenerator);
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

    public void hitPlayer(Participant player, Deck deck, Answer answer) {
        if (answer == Answer.NO) {
            return;
        }
        player.hit(deck.draw());
    }

    public boolean canHit(Participant player) {
        return !bustPolicy.isBust(scoreCalculator.calculate(player.getCards()));
    }

    public boolean shouldHit(Dealer dealer) {
        return dealer.shouldHit(dealerHitPolicy, scoreCalculator.calculate(dealer.getCards()));
    }

    public void hitDealer(Dealer dealer, Deck deck) {
        dealer.hit(deck.draw());
    }

    public Score calculate(Participant participant) {
        return scoreCalculator.calculate(participant.getCards());
    }

    public BlackjackResult judge(Participant player, Dealer dealer) {
        return resultJudgement.judge(
            scoreCalculator.calculate(player.getCards()),
            scoreCalculator.calculate(dealer.getCards()));
    }
}

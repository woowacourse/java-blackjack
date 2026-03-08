package blackjack.service;

import blackjack.model.Answer;
import blackjack.model.BlackjackResult;
import blackjack.model.BlackjackRule;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Score;

public class BlackjackService {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final CardsGenerator cardsGenerator;
    private final BlackjackRule rule;

    public BlackjackService(CardsGenerator cardsGenerator, BlackjackRule rule) {
        this.cardsGenerator = cardsGenerator;
        this.rule = rule;
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

    public void hitPlayer(Participant player, Deck deck, Answer answer) {
        if (answer == Answer.NO) {
            return;
        }
        player.hit(deck.draw());
    }

    public boolean canHit(Participant player) {
        return rule.canHit(player);
    }

    public boolean shouldHit(Dealer dealer) {
        return rule.shouldHit(dealer);
    }

    public void hitDealer(Dealer dealer, Deck deck) {
        dealer.hit(deck.draw());
    }

    public Score calculate(Participant participant) {
        return rule.calculate(participant);
    }

    public BlackjackResult judge(Participant player, Dealer dealer) {
        return rule.judge(player, dealer);
    }
}

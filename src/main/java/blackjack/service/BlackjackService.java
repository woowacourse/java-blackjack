package blackjack.service;

import blackjack.model.Answer;
import blackjack.model.PlayerBlackjackResult;
import blackjack.model.BlackjackGameManager;
import blackjack.model.Card;
import blackjack.model.CardsGenerator;
import blackjack.model.Dealer;
import blackjack.model.Deck;
import blackjack.model.Participant;
import blackjack.model.Participants;
import blackjack.model.Score;
import java.util.List;

public class BlackjackService {
    private static final int INITIAL_DEAL_COUNT = 2;

    private final CardsGenerator cardsGenerator;
    private final BlackjackGameManager manager;

    public BlackjackService(CardsGenerator cardsGenerator, BlackjackGameManager manager) {
        this.cardsGenerator = cardsGenerator;
        this.manager = manager;
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

    public void hitPlayer(Participant player, Deck deck) {
        player.hit(deck.draw());
    }

    public boolean canHit(Participant player) {
        return manager.canHit(player);
    }

    public boolean shouldHit(Dealer dealer) {
        return manager.shouldHit(dealer);
    }

    public void hitDealer(Dealer dealer, Deck deck) {
        dealer.hit(deck.draw());
    }

    public Score calculate(List<Card> cards) {
        return manager.calculate(cards);
    }

    public PlayerBlackjackResult judge(Participant player, Dealer dealer) {
        return manager.judge(player, dealer);
    }
}

package blackjack.service;

import static blackjack.util.constant.Constants.INITIAL_CARD_COUNT;

import blackjack.domain.GameResultStatus;
import blackjack.domain.participant.Dealer;
import blackjack.domain.deck.Deck;
import blackjack.domain.GameResult;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.User;
import blackjack.domain.participant.Users;

public class GameService {

    private final Deck deck;

    public GameService(Deck deck) {
        this.deck = deck;
    }

    public void settingCards(Users users, Dealer dealer) {
        deck.shuffle();
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            distributeCard(users, dealer);
        }
    }

    private void distributeCard(Users users, Dealer dealer) {
        users.forEach(user -> user.add(deck.bringTopCard()));
        dealer.add(deck.bringTopCard());
    }

    public void getMoreCard(Participant participant) {
        participant.add(deck.bringTopCard());
    }

    public void applyGameResult(User user, Dealer dealer, GameResult gameResult) {
        GameResultStatus resultType = determineResult(user, dealer);
        gameResult.add(user.getName(), resultType.calculateProfit(user.getBettingAmount()));
    }

    public GameResultStatus determineResult(User user, Dealer dealer) {
        if (user.isBurst()) {
            return GameResultStatus.LOSE;
        }
        if (user.isBlackjack() && dealer.isBlackjack()) {
            return GameResultStatus.DRAW;
        }
        if (user.isBlackjack()) {
            return GameResultStatus.BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return GameResultStatus.LOSE;
        }
        if (dealer.isBurst()) {
            return GameResultStatus.WIN;
        }
        return compareScore(user, dealer);
    }

    private GameResultStatus compareScore(User user, Dealer dealer) {
        if (user.calculateCardsValue() > dealer.calculateCardsValue()) {
            return GameResultStatus.WIN;
        }
        if (user.calculateCardsValue() == dealer.calculateCardsValue()) {
            return GameResultStatus.DRAW;
        }
        return GameResultStatus.LOSE;
    }
}

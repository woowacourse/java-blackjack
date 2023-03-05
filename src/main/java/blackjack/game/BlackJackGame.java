package blackjack.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.player.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int INIT_FIRST_CARD = 2;
    private static final int MIN_CARD_SIZE = 1;

    private final Players players;
    private final Queue<Card> cards;

    public BlackJackGame(List<Name> names, Dealer dealer) {
        Cards shuffledCards = Cards.initializeCards();
        cards = new LinkedList<>(shuffledCards.getCards());
        players = initializePlayers(names);
        giveFirstCards(dealer);
    }

    private Players initializePlayers(List<Name> names) {
        return new Players(names.stream().map(Player::new)
                .collect(Collectors.toUnmodifiableList()));
    }

    private void giveFirstCards(Dealer dealer) {
        for (Player player : players.getPlayers()) {
            giveFirstCard(player);
        }
        giveFirstCard(dealer);
    }

    private void giveFirstCard(User user) {
        for (int i = 0; i < INIT_FIRST_CARD; i++) {
            user.updateCardScore(giveFirstCard());
        }
    }

    public void giveOneMoreCard(User user) {
        user.updateCardScore(giveFirstCard());
    }

    public Players getPlayers() {
        return this.players;
    }

    private Card giveFirstCard() {
        if (cards.size() < MIN_CARD_SIZE) {
            throw new IndexOutOfBoundsException("뽑을 수 있는 카드가 없습니다.");
        }
        return cards.remove();
    }
}

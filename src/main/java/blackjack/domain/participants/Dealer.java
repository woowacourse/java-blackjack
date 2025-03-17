package blackjack.domain.participants;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

public class Dealer {
    private final Players players;
    private final Deck deck;
    private final Cards cards;
    private int bettingMoney;

    public Dealer(Players players, Deck deck) {
        this(players, deck, new Cards());
    }

    public Dealer(Players players, Deck deck, Cards cards) {
        this.players = players;
        this.deck = deck;
        this.cards = cards;
    }

    public void prepareBlackjack() {
        pickCards();
        handOutCard();
    }

    public void pickCards() {
        cards.take(deck.draw(), deck.draw());
    }

    private void handOutCard() {
        players.sendAll((player) -> player.take(deck.draw(), deck.draw()));
    }

    public void sendCardToPlayer(Player player) {
        if (!players.contains(player)) {
            throw new IllegalArgumentException("해당 플레이어는 존재하지 않습니다.");
        }
        if (!player.canTake()) {
            throw new IllegalArgumentException("한 플레이어가 가질 수 있는 카드 합의 최대는 21입니다.");
        }
        player.additionalTake(deck.draw());
    }

    public void pickAdditionalCard() {
        while (cards.doesNeedDealerPickAdditionalCard()) {
            cards.additionalTake(deck.draw());
        }
    }

    public GameResult createGameResult() {
        return GameResult.create(this, players);
    }

    public Cards getCards() {
        return cards;
    }

    public Card getFirstCard() {
        return cards.getCards().get(0);
    }

    public int getCardSize() {
        return cards.getSize();
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void updateBettingMoney(int money) {
        bettingMoney += money;
    }
}

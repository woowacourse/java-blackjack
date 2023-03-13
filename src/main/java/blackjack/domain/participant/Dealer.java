package blackjack.domain.participant;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import blackjack.domain.money.Bank;
import blackjack.domain.money.Money;
import blackjack.domain.result.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public final class Dealer extends Participant {

    private static final String name = "딜러";
    private static final int INIT_CARD_COUNT = 2;
    private static final int FIRST_CARD_INDEX = 0;
    private static final int CAN_DRAW_SCORE = 16;

    private final Bank bank;
    private final Deck deck;

    public Dealer() {
        super();
        this.bank = new Bank();
        this.deck = new Deck();
    }

    public void saveBettingMoney(final Player player, final Money money) {
        this.bank.betMoney(player, money);
    }

    public void settingCards(final Players players) {
        List<Hand> hands = IntStream.range(0, players.size())
                .mapToObj(x -> new Hand(deck.drawTwoCard()))
                .collect(toList());

        players.distributeHands(hands);
    }

    public void settingSelf() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            this.receiveCard(deck.drawCard());
        }
    }

    public Card showOneCard() {
        List<Card> hand = this.getHand().getHand();

        if (hand.isEmpty()) {
            throw new IllegalStateException("딜러의 손에는 카드가 존재하지 않습니다.");
        }
        return hand.get(FIRST_CARD_INDEX);
    }

    public boolean canDraw() {
        return this.totalScore() <= CAN_DRAW_SCORE;
    }

    public void drawIfLowerOrEquals16() {
        while (this.totalScore() <= CAN_DRAW_SCORE) {
            this.receiveCard(deck.drawCard());
        }
    }

    public Map<Player, Money> exchange(final Map<Player, Result> playersResult) {
        return playersResult.keySet().stream()
                .collect(toMap(
                        player -> player,
                        player -> bank.exchange(player, playersResult.get(player))
                ));
    }

    public Money totalBettingMoney() {
        return bank.totalMoney();
    }

    public Money totalExchangedMoney(final Map<Player, Money> exchanges) {
        Bank exchangedBank = new Bank(exchanges);
        return exchangedBank.totalMoney();
    }

    public void giveCard(final Player player) {
        player.receiveCard(deck.drawCard());
    }

    public int getCanDrawScore() {
        return CAN_DRAW_SCORE;
    }

    public int getInitCardCount() {
        return INIT_CARD_COUNT;
    }

    public String getName() {
        return name;
    }

    public Bank getBank() {
        return bank;
    }
}

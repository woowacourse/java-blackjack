package domain.model;

import static constant.BlackJackConstant.BURST_CRITERIA;

import constant.BlackJackConstant;
import java.util.List;

public class Player implements Person {

    private final String name;
    private Deck deck;
    private PlayerStatus playerStatus = PlayerStatus.NONE;
    private Bet bet;

    private Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
    }

    public static Player of(String name) {
        return new Player(name, null);
    }

    public int calculateFinalSum() {
        return deck.calculateFinalSum();
    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isBlackJack() {
        return deck.getDeckStatus() == DeckStatus.BLACK_JACK;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public List<Card> getCards() {
        return deck.getCards();
    }

    public int getBetAmount() {return bet.getBetAmount(); }

    // 플레이어에게 초기 생성된 카드 2개가 있는 덱을 부여
    public void assignDeck(Deck deck) {
        this.deck = deck;
    }

    public void bet(int betAmount) {
        bet = Bet.of(betAmount);
    }

    public int getFinalMoney() {
        return (int)bet.getFinalMoney(playerStatus);
    }

    // 추가 카드 부여
    public void appendCard(Card card) {
        deck.append(card);
    }

    public boolean isBurst() {
        return deck.calculateFinalSum() > BURST_CRITERIA;
    }

    public boolean isAlive() {
        return deck.getDeckStatus() == DeckStatus.ALIVE;
    }

    public void changeStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    @Override
    public int getDeckSum() {
        return deck.getSum();
    }

    @Override
    public int getDeckSize() {
        return deck.getSize();
    }
}

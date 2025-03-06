package domain;

import except.BlackJackException;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackParticipants {

    private final List<Player> players;
    private final Dealer dealer;
    private final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";

    public BlackjackParticipants(List<Player> players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void addCard(String name, TrumpCard trumpCard) {
        Player findPlayer = findPlayer(name);
        findPlayer.addDraw(trumpCard);
    }

    private Player findPlayer(String name) {
        return players.stream()
                .filter((player) -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlackJackException(INVALID_PLAYER));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::name)
                .collect(Collectors.toList());
    }

    public List<TrumpCard> playerCards(String name) {
        Player player = findPlayer(name);
        return player.trumpCards();
    }

    public List<TrumpCard> dealerCards() {
        return dealer.trumpCards();
    }

    public int calculateCardSum(String name) {
        Player player = findPlayer(name);
        return player.calculateCardSum();
    }

    public int calculateDealerSum() {
        return dealer.calculateCardSum();
    }

    public String dealerName() {
        return dealer.name();
    }

    public boolean isBust(String name) {
        Player player = findPlayer(name);
        return !player.isDrawable();
    }

    public void addDealerDraw(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public boolean dealerDrawable() {
        return dealer.isDrawable();
    }
}

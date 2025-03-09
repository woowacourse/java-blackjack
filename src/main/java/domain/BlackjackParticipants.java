package domain;

import except.BlackJackException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackParticipants<T extends BlackjackParticipant> {

    private final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";

    private final List<T> players;
    private final Dealer dealer;

    public BlackjackParticipants(List<T> players, Dealer dealer) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
    }

    private T findPlayer(String name) {
        return players.stream()
                .filter(player -> player.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlackJackException(INVALID_PLAYER));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(BlackjackParticipant::name)
                .collect(Collectors.toList());
    }

    public List<TrumpCard> playerCards(String name) {
        return findPlayer(name).trumpCards();
    }

    public List<TrumpCard> dealerCards() {
        return dealer.trumpCards();
    }

    public int calculateCardSum(String name) {
        T player = findPlayer(name);
        return player.calculateCardSum();
    }

    public int calculateDealerSum() {
        return dealer.calculateCardSum();
    }

    public String dealerName() {
        return dealer.name();
    }

    public boolean isBust(String name) {
        T player = findPlayer(name);
        return !player.isDrawable();
    }

    public void addCard(String name, TrumpCard trumpCard) {
        T player = findPlayer(name);
        player.addDraw(trumpCard);
    }

    public void addDealerCard(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public boolean dealerDrawable() {
        return dealer.isDrawable();
    }
}

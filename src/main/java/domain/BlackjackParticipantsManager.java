package domain;

import domain.except.BlackJackStateException;
import except.BlackJackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackParticipantsManager<E extends BlackjackParticipant, V extends BlackjackParticipant> {

    private static final String INVALID_PLAYER = "존재하지 않는 플레이어입니다.";
    private static final String INVALID_HANDS_STATE = "아직 카드를 받지 않은 참여자입니다.";

    private final List<E> players;
    private final V dealer;

    public BlackjackParticipantsManager(List<E> players, V dealer) {
        this.players = new ArrayList<>(players);
        this.dealer = dealer;
    }

    private E findPlayer(String name) {
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
        return Collections.unmodifiableList(findPlayer(name).trumpCards());
    }

    public List<TrumpCard> dealerCards() {
        return Collections.unmodifiableList(dealer.trumpCards());
    }

    private void validateEmptyCards(List<TrumpCard> trumpCards) {
        if (trumpCards.isEmpty()) {
            throw new BlackJackStateException(INVALID_HANDS_STATE);
        }
    }

    public TrumpCard firstDealerCards() {
        validateEmptyCards(dealerCards());
        return dealer.trumpCards().get(0);
    }

    public int calculateCardSum(String name) {
        E player = findPlayer(name);
        return player.calculateCardSum();
    }

    public int calculateDealerSum() {
        return dealer.calculateCardSum();
    }

    public String dealerName() {
        return dealer.name();
    }

    public boolean isBust(String name) {
        E player = findPlayer(name);
        return !player.isBurst(player.calculateCardSum());
    }

    public void addCard(String name, TrumpCard trumpCard) {
        E player = findPlayer(name);
        player.addDraw(trumpCard);
    }

    public void addDealerCard(TrumpCard trumpCard) {
        dealer.addDraw(trumpCard);
    }

    public boolean dealerDrawable() {
        return dealer.isDrawable();
    }
}

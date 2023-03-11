package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardResponse;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    private Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public static Participants of(final List<String> playerNames, final List<Integer> bettingMoneys) {
        return new Participants(Players.from(playerNames, bettingMoneys), new Dealer());
    }

    //이런 방식으로 검증을 하게 되면, 생성자에서 검증을 하기에, 로직상은 문제가 없지만
    //new Names().validate()와 같이 호출하는 것이 직관적일 수도 있을 것 같은데 어떤 방향이 좋으신가요?
    //애그리거트 루트로 생각하다보니, 여기에 검증하는 작업을 넣었는데, 생성하는 쪽에서 검증하는 것이 불가능하기에 static 으로 작성하게 되었습니다
    public static void validatePlayerNames(final List<String> playerNames) {
        new Names(playerNames);
    }

    public static void validateBettingMoney(final int amount) {
        new BettingMoney(amount);
    }

    public void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawInitialCard(deck);
    }

    public boolean isPlayerDrawable(final String playerName) {
        return players.isDrawable(playerName);
    }

    public void drawPlayerCard(final String playerName, final Card card) {
        players.draw(playerName, card);
    }

    public boolean isDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard(final Card card) {
        dealer.drawCard(card);
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public List<CardResponse> getPlayerCards(final String playerName) {
        return players.getPlayerCards(playerName);
    }

    public Map<String, List<CardResponse>> getPlayersCards() {
        return players.getPlayersCards();
    }

    public int getDealerScore() {
        return dealer.currentScore();
    }

    public List<CardResponse> getDealerCards() {
        return dealer.getCards();
    }

    public Map<String, Integer> getPlayersScores() {
        return players.calculatePlayersScore();
    }
}

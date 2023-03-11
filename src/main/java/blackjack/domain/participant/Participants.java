package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.dto.CardResponse;
import java.util.List;
import java.util.Map;

public class Participants {

    private final Players players;
    private final Dealer dealer;

    public Participants(final Players players, final Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void distributeInitialCards(final Deck deck) {
        players.distributeInitialCards(deck);
        dealer.drawInitialCard(deck.popCard(), deck.popCard());
    }

    public Player findPlayerByName(final String playerName) {
        return players.findPlayerByName(playerName);
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

    public Players getPlayers() {
        return players;
    }

    //이런 방식으로 검증을 하게 되면, 생성자에서 검증을 하기에, 로직상은 문제가 없지만
    //new Names().validate()와 같이 호출하는 것이 직관적일 수도 있을 것 같은데 어떤 방향이 좋으신가요?
    public void validatePlayerNames(final List<String> playerNames) {
        new Names(playerNames);
    }

    public void validateBettingMoney(final int amount) {
        new BettingMoney(amount);
    }

    public List<String> getPlayerNames() {
        return players.getPlayerNames();
    }

    public Map<String, Integer> calculatePlayersScore() {
        return players.calculatePlayersScore();
    }

    public Map<String, List<CardResponse>> getPlayersCards() {
        return players.getPlayersCards();
    }
}

package domain.member;
import static constant.Word.*;

import domain.RoundResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Members {

    private final List<Player> players;
    private final Dealer dealer;

    public Members() {
        players = new ArrayList<>();
        dealer = new Dealer(DEALER.format());
    }

    public void appendPlayer(List<String> playerNames) {
        playerNames.forEach(playerName -> players.add(new Player(playerName)));
    }

    public void provideCardToPlayer(String memberName, Card card) {
        Player member = findByName(memberName);
        member.receiveCard(card);
    }

    public void provideCardToDealer(Card card) {
        dealer.receiveCard(card);
    }

    public List<Card> findCardByName(String memberName) {
        Member member = findByName(memberName);
        return member.currentCards();
    }

    public boolean isDealer(String memberName) {
        return memberName.equals(dealer.name());
    }

    public List<Card> dealerFirstCard() {
        return List.of(dealer.firstCard());
    }

    public List<Card> dealerCards() {
        return dealer.currentCards();
    }

    public int checkDealerValue() {
        return dealer.currentValue();
    }

    public int checkValue(String memberName) {
        Member member = findByName(memberName);
        return member.currentValue();
    }

    private Player findByName(String memberName) {
        return players.stream()
                .filter(member -> member.name().equals(memberName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<String> getAllPlayerNames() {
        return players.stream()
                .map(Player::name)
                .toList();
    }

    public Map<String, RoundResult> judgeGameResults() {
        Map<String, RoundResult> playerResults = new LinkedHashMap<>();
        players.forEach(player ->
            playerResults.put(player.name(), player.judgeAgainst(dealer))
        );
        return playerResults;
    }
}

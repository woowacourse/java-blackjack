package domain;

import domain.member.Member;
import domain.member.Members;
import domain.card.Deck;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final Deck deck;
    private final Members members;

    public BlackjackGame(Members members) {
        this.members = members;
        this.deck = new Deck();
        this.deck.init();
    }

    public void initGame() {
        members.initDraw(deck);
    }

    public void drawPlayer(Member player) {
        members.draw(player, deck.draw());
    }

    public void drawDealer() {
        members.draw(members.getDealer(), deck.draw());
    }

    public boolean canDealerDraw() {
        return members.canDealerDraw();
    }

    public boolean isContinuable(Member member) {
        return !members.hasBust(member);
    }

    public void applyBlackjackBonus() {
        getPlayers().stream()
                .filter(members::hasBlackjack)
                .forEach(members::applyBlackjackBonus);
    }

    public boolean checkBust(Member player) {
        return members.hasBust(player);
    }

    public boolean checkBlackjack(Member member) {
        return members.hasBlackjack(member);
    }

    public List<Member> getPlayers() {
        return Collections.unmodifiableList(members.getPlayers());
    }

    public Member getDealer() {
        return members.getDealer();
    }

    public Map<Member, Integer> getPlayerProfits() {
        return members.judgeGameResults().entrySet().stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> entry.getKey()
                                .calculateProfit(entry.getValue()),
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    public int getDealerProfit() {
        return getPlayerProfits().values().stream()
                .mapToInt(result -> -result)
                .sum();
    }
}

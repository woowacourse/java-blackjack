package domain.member;

import domain.vo.RoundResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Members {

    private final List<Member> members;

    public Members(Map<String, Integer> players) {
        members = new ArrayList<>();
        members.add(new Dealer());
        for (Entry<String, Integer> player : players.entrySet()) {
            members.add(new Player(player.getKey(), player.getValue()));
        }
    }

    public Member findByName(String memberName) {
        return members.stream()
                .filter(member -> member.hasName(memberName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public Member findDealer() {
        return members.stream()
                .filter(Member::isDealer)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Card> findCard(Member member) {
        return member.handCards();
    }

    public void draw(Member member, Card card) {
        member.receiveCard(card);
    }

    public boolean isDealer(Member member) {
        return member.isDealer();
    }

    public Map<String, RoundResult> judgeGameResults() {
        Member dealer = findDealer();
        return members.stream()
                .filter(member -> !member.isDealer())
                .collect(Collectors.toMap(
                                Member::getName,
                                player -> RoundResult.judgeAgainst(
                                        dealer.handValue(),
                                        player.handValue()
                                ),
                                (existing, replacement) -> existing,
                                LinkedHashMap::new
                        )
                );
    }

    public void applyBlackjackBonus(Member member) {
        if (!member.isDealer()) {
            Player player = (Player) member;
            player.applyBlackjackBonus();
        }
    }

    public int getValue(Member member) {
        return member.handValue();
    }

    public List<String> getMemberNames() {
        return members.stream()
                .map(Member::getName)
                .toList();
    }

    public String getDealerName() {
        return findDealer().getName();
    }

    public int getBettingAmount(String playerName) {
        Member member = findByName(playerName);
        if (member.isDealer()) {
            throw new UnsupportedOperationException("딜러는 배팅 금액을 가질 수 없습니다.");
        }
        Player player = (Player) member;
        return player.getBettingAmount();
    }

    public List<Card> getFirstCards(Member member) {
        return member.showFirstCards();
    }
}

package domain.member;

import domain.vo.RoundResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Members {

    private final List<Member> members;

    public Members(List<String> playerNames) {
        members = new ArrayList<>();
        members.add(new Dealer());
        playerNames.forEach(playerName -> members.add(new Player(playerName)));
    }

    public List<Card> getFirstCards(String memberName) {
        Member member = findByName(memberName);
        return member.showFirstCards();
    }

    public List<Card> findCardByName(String memberName) {
        Member member = findByName(memberName);
        return member.handCards();
    }

    public Member findByName(String memberName) {
        return members.stream()
                .filter(member -> member.getName().equals(memberName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public int getValue(String memberName) {
        Member member = findByName(memberName);
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

    public Map<String, RoundResult> judgeGameResults() {
        Member dealer = findDealer();
        return members.stream()
                .filter(member -> member instanceof Player)
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

    private Member findDealer() {
        return members.stream()
                .filter(member -> member instanceof Dealer)
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }
}

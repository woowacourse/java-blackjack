package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Members {

    private final List<Member> members;

    public Members() {
        this.members = new ArrayList<>();
    }

    public void join(Member member) {
        members.add(member);
    }

    public void provideCard(String memberName, Card card) {
        Member member = findByName(memberName);
        member.receiveCard(card);
    }

    public List<Card> findCardByName(String memberName) {
        Member member = findByName(memberName);
        return member.currentCards();
    }

    public int checkValue(String memberName) {
        Member member = findByName(memberName);
        return member.currentValue();
    }

    private Member findByName(String memberName) {
        return members.stream()
                .filter(member -> member.name().equals(memberName))
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<String> getAllPlayerName() {
        return members.stream()
                .map(Member::name)
                .toList();
    }
}

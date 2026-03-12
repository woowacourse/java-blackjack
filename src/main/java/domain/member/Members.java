package domain.member;

import constant.Word;
import domain.MatchResult;
import domain.card.Card;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Members {

    private final List<Member> members;

    public Members(List<String> playerNames) {
        this.members = new ArrayList<>();
        join(new Dealer());
        for (String name : playerNames) {
            join(new Player(name));
        }
    }

    private void join(Member member) {
        members.add(member);
    }

    public void provideCardToMember(String memberName, Card card) {
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

    public List<String> getAllMemberName() {
        return members.stream()
                .map(Member::name)
                .toList();
    }

    public List<MatchResult> determineDealerGameResult() {
        Member dealer = findByName(Word.DEALER.getWord());
        List<Member> players = members.stream()
                .filter(member -> !member.name().equals(Word.DEALER.getWord()))
                .toList();

        List<MatchResult> gameResult = new ArrayList<>();
        for (Member player : players) {
            gameResult.add(dealer.compareScoreWith(player));
        }
        return gameResult;
    }

    public MatchResult determinePlayerGameResult(String name) {
        Member dealer = findByName(Word.DEALER.getWord());
        Member player = findByName(name);
        return player.compareScoreWith(dealer);
    }
}

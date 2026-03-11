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

    public List<MatchResult> judgeDealerGameResult() {
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

    public Map<String, MatchResult> judgePlayerGameResult() {
        Member dealer = findByName(Word.DEALER.getWord());
        List<Member> players = members.stream()
                .filter(member -> !member.name().equals(Word.DEALER.getWord()))
                .toList();

        Map<String, MatchResult> gameResult = new HashMap<>();
        for (Member player : players) {
            String playerName = player.name();
            gameResult.put(playerName,
                    player.compareScoreWith(dealer));
        }

        return gameResult;
    }
}

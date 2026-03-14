package presentation.dto;

import domain.card.Card;
import domain.member.Member;
import java.util.List;

public record HitInfo(
        String memberName,
        List<String> cards
) {
    public static HitInfo firstCardFrom(Member member) {
        return new HitInfo(member.getName(), getCardNames(member.showFirstCards()));
    }

    public static List<HitInfo> firstCardFrom(List<Member> members) {
        return members.stream()
                .map(member ->
                        new HitInfo(
                                member.getName(),
                                getCardNames(member.showFirstCards())
                        )
                ).toList();
    }

    public static HitInfo from(Member member) {
        return new HitInfo(member.getName(), getCardNames(member.handCards()));
    }

    public static List<String> getCardNames(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getCourt() + card.getPattern())
                .toList();
    }
}

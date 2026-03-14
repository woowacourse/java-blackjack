package domain.member;

import domain.card.Deck;
import domain.vo.RoundResult;
import domain.card.Card;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Members {

    private static final String DEALER_DEFAULT_NAME = "딜러";
    private static final int DEALER_DRAW_CONDITION = 16;

    private final List<Member> players;
    private final Member dealer;

    public Members(List<Member> players) {
        this.players = players;
        dealer = new Member(new Name(DEALER_DEFAULT_NAME), new DealerRole());
    }
    public void initDraw(Deck deck) {
        dealer.initDraw(deck);
        players.forEach(member -> member.initDraw(deck));
    }

    public void draw(Member member, Card card) {
        member.receiveCard(card);
    }

    public boolean hasBust(Member member) {
        return member.hasBust();
    }

    public boolean hasBlackjack(Member member) {
        return member.hasBlackjack();
    }

    public boolean canDealerDraw() {
        return dealer.handValue() <= DEALER_DRAW_CONDITION;
    }

    public Map<Member, RoundResult> judgeGameResults() {
        return players.stream()
                .collect(Collectors.toMap(
                                player -> player,
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
        member.applyBlackjackBonus();
    }

    public List<Member> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Member getDealer() {
        return dealer;
    }
}

package dto;

import domain.Dealer;
import domain.Hand;
import domain.Player;
import domain.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record ScoreResultDto(List<String> scoreResults) {

    public ScoreResultDto(Dealer dealer, List<Player> players) {
        this(new ArrayList<>());
        scoreResults.add(getScoreResultString("딜러", dealer.getHand()));
        for (Player player : players) {
            scoreResults.add(getScoreResultString(player.getName(), player.getHand()));
        }
    }

    private String getScoreResultString(String name, Hand hand) {
        return name + "카드: " + getHandString(hand) + " - 결과: " + hand.getSum();
    }

    private String getHandString(Hand hand) {
        return hand.getCards().stream().
                map(Card::getCardName)
                .collect(Collectors.joining(", "));
    }

//    딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
//    pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
//    jason카드: 7클로버, K스페이드 - 결과: 17
}

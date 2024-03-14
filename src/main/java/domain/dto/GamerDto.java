package domain.dto;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.constant.GamerIdentifier;
import java.util.List;

public class GamerDto {
    private final String name;
    private final List<Card> cards;
    private final int totalScore;

    private GamerDto(String name, List<Card> cards, int totalScore) {
        this.name = name;
        this.cards = cards;
        this.totalScore = totalScore;
    }

    public static GamerDto fromPlayer(Player player) {
        return new GamerDto(
                player.getName(),
                player.getCards(),
                player.getTotalScore()
        );
    }


    public static GamerDto fromDealer(Dealer dealer) {
        return new GamerDto(
                GamerIdentifier.DEALER_IDENTIFIER,
                dealer.getCards(),
                dealer.getTotalScore()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTotalScore() {
        return totalScore;
    }
}

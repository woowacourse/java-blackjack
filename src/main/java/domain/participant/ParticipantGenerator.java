package domain.participant;

import domain.BettingMoney;
import domain.card.Card;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;

public class ParticipantGenerator {

    private ParticipantGenerator() {
        throw new IllegalStateException("생성할 수 없는 객체입니다.");
    }


    public static Player createEmptyCardPlayer(final Name name, final BettingMoney bettingMoney) {

        List<Card> emptyCards = new ArrayList<>();
        return new Player(name, new DrawnCards(emptyCards), bettingMoney);
    }

    public static Dealer createEmptyCardDealer() {
        List<Card> emptyCards = new ArrayList<>();
        return new Dealer(new DrawnCards(emptyCards));
    }
}

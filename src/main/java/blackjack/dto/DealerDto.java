package blackjack.dto;

import blackjack.domain.participant.Dealer;
import java.util.List;

public class DealerDto extends ParticipantDto {

    public DealerDto(String name, List<String> cards, int score) {
        super(name, cards, score);
    }

    public static DealerDto from(Dealer dealer) {
        String name = dealer.getName();
        List<String> cards = dealer.getHand().generateCardNames();
        int score = dealer.getCurrentScore().getValue();

        return new DealerDto(name, cards, score);
    }

    public String getFirstCard() {
        return super.getCards().get(0);
    }
}

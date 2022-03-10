package blackjack.vo;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.participant.Participant;
import java.util.Map;
import java.util.stream.Collectors;

public class ParticipantVo {

    private final Participant participant;

    public ParticipantVo(Participant participant) {
        this.participant = participant;
    }

    public String getName() {
        return participant.getName();
    }

    public Map<CardNumber, CardSymbol> getCards() {
        return participant.getCards().getValue().stream()
                .collect(Collectors.toMap(Card::getNumber, Card::getSymbol, (a, b) -> b));
    }

    public int getScore() {
        return participant.getScore();
    }
}

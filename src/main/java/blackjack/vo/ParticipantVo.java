package blackjack.vo;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ParticipantVo {

    private final Participant participant;

    public ParticipantVo(Participant participant) {
        this.participant = participant;
    }

    public String getName() {
        return participant.getName();
    }

    public Set<Card> getCards() {
        return participant.getCards().getValue();
    }

    public int getScore() {
        return participant.getScore();
    }
}

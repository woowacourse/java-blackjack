package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Cards;
import blackjack.domain.user.ParticipantName;

public interface Resultable {
    Cards getCards();
    ParticipantName getName();
    int getScore();
}

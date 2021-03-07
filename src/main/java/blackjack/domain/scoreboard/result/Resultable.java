package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Name;

public interface Resultable {
    Cards getCards();
    Name getName();
    int getScore();
}

package view;

import domain.betting.Money;
import domain.card.Card;
import domain.card.Score;
import domain.card.Symbol;
import domain.participant.Dealer;
import domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class MessageResolver {

    private static final String DEALER_NAME = "딜러";
    private static final String DELIMITER = ", ";
    private static final String WIN_TEXT = "승";
    private static final String TIE_TEXT = "무";
    private static final String LOSE_TEXT = "패";

    public String playerNamesText(List<Player> players) {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(DELIMITER));
    }

    public String dealerNameAndStartingCardsText(Dealer dealer) {
        return String.format("%s 카드: %s", DEALER_NAME, cardText(dealer.firstCard()));
    }

    public String cardsText(List<Card> cards) {
        return cards.stream()
            .map(this::cardText)
            .collect(Collectors.joining(DELIMITER));
    }

    public String cardText(Card card) {
        return card.rank().getDescription() + symbolText(card.symbol());
    }

    public String symbolText(Symbol symbol) {
        if (symbol == Symbol.CLUB) {
            return "클로버";
        }
        if (symbol == Symbol.DIAMOND) {
            return "다이아몬드";
        }
        if (symbol == Symbol.HEART) {
            return "하트";
        }
        if (symbol == Symbol.SPADE) {
            return "스페이드";
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 Symbol입니다.");
    }

    public String participantNameAndCardsText(String name, List<Card> cards) {
        return String.format("%s 카드: %s", name, cardsText(cards));
    }

    public String scoreText(Score score) {
        return String.valueOf(score.toInt());
    }

    public String moneyText(Money money) {
        return String.valueOf(money.toInt());
    }
}

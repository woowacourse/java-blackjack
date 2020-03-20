package blackjack.domain.gambler;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.PlayerOutcome;
import java.util.List;
import java.util.Objects;

public final class Player {

    private static final int BASES_SCORE_CAN_DRAW = 21;
    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";

    private final Name name;
    private final BettingMoney bettingMoney;
    private UserCards userCards = new UserCards();

    public Player(Name name, BettingMoney bettingMoney) {
        validateNotNull(name, bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validateNotNull(Name name, BettingMoney bettingMoney) {
        if (Objects.isNull(name) || Objects.isNull(bettingMoney)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, DEFAULT_DRAW_COUNT);
    }

    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            userCards.add(cardDeck.draw());
        }
    }

    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    public Name getName() {
        return name;
    }

    public CardsResult getScore() {
        return userCards.getResult();
    }

    public List<String> getCardsInfos() {
        return userCards.getInfos();
    }

    public Integer getProfitByComparing(Dealer dealer) {
        return bettingMoney.multiply(calculateOutcome(dealer).getProfitRatio());
    }

    public PlayerOutcome calculateOutcome(Dealer dealer) {
        return PlayerOutcome.of(getScore(), dealer.getScore());
    }
}

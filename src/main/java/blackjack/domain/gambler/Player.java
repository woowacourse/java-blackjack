package blackjack.domain.gambler;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserCards;
import blackjack.domain.result.CardsResult;
import blackjack.domain.result.PlayerOutcome;
import java.util.List;
import java.util.Objects;

public final class Player implements Gambler {

    private static final int BASES_SCORE_CAN_DRAW = 21;
    private static final int DEFAULT_DRAW_COUNT = 1;
    private static final String PLAYER_NAME_IS_NULL_EXCEPTION_MESSAGE =
        "참여인원의 이름은 null이어선 안됩니다.";
    private static final String MONEY_IS_NULL_EXCEPTION_MESSAGE = "배팅금액은 null이어선 안됩니다.";

    private final Name name;
    private final Money money;
    private UserCards userCards = new UserCards();

    public Player(Name name, Money money) {
        validateName(name);
        validateMoney(money);
        this.name = name;
        this.money = money;
    }

    private void validateName(Name name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(PLAYER_NAME_IS_NULL_EXCEPTION_MESSAGE);
        }
    }

    private void validateMoney(Money money) {
        if (Objects.isNull(money)) {
            throw new IllegalArgumentException(MONEY_IS_NULL_EXCEPTION_MESSAGE);
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

    @Override
    public boolean canDrawCard() {
        return getScore().isEqualOrUnderScore(BASES_SCORE_CAN_DRAW);
    }

    public Name getName() {
        return name;
    }

    public CardsResult getScore() {
        return userCards.getScore();
    }

    public List<String> getCardsInfos() {
        return userCards.getInfos();
    }

    public Money getProfitByComparing(Dealer dealer) {
        return money.multiply(calculateOutcome(dealer).getProfitRatio());
    }

    public PlayerOutcome calculateOutcome(Dealer dealer) {
        return PlayerOutcome.of(getScore(), dealer.getScore());
    }
}

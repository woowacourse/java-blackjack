package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardHandState;
import blackjack.strategy.CardHandStateStrategy;
import java.util.List;
import java.util.stream.Collectors;

public class Player extends Participant {

    private static final int INITIAL_PLAYER_OPEN_CARDS_COUNT = 2;
    private static final String BLACK_NAME_INPUT_EXCEPTION_MESSAGE = "플레이어는 이름을 지녀야 합니다.";
    private static final String INVALID_PLAYER_NAME_EXCEPTION_MESSAGE = "플레이어의 이름은 딜러가 될 수 없습니다.";

    private static final CardHandStateStrategy STATE_UPDATE_STRATEGY = CardHandState::ofPlayer;

    private final String name;

    private Player(final String name, final CardBundle cardBundle) {
        super(cardBundle, STATE_UPDATE_STRATEGY);
        this.name = name;
    }

    public static Player of(final String name, final CardBundle cardBundle) {
        validateName(name);
        return new Player(name, cardBundle);
    }

    private static void validateName(String name) {
        validateNameExistence(name);
        validateNameNotDealer(name);
    }

    private static void validateNameExistence(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(BLACK_NAME_INPUT_EXCEPTION_MESSAGE);
        }
    }

    private static void validateNameNotDealer(String name) {
        if (name.equals(Dealer.UNIQUE_NAME)) {
            throw new IllegalArgumentException(INVALID_PLAYER_NAME_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void receiveCard(Card card) {
        cardHand.hit(card, STATE_UPDATE_STRATEGY);
    }

    public void stay() {
        cardHand.stay();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getInitialOpenCards() {
       return cardHand.getCards()
               .stream()
               .limit(INITIAL_PLAYER_OPEN_CARDS_COUNT)
               .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String toString() {
        return "Player{" +
                "cardHand=" + cardHand +
                ", name='" + name + '\'' +
                '}';
    }
}

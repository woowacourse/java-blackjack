package blackjack.controller;

import blackjack.domain.BlackJack;
import blackjack.domain.Card;
import blackjack.domain.CardGroup;
import blackjack.domain.Gamer;
import blackjack.domain.Player;
import blackjack.domain.PlayerGroup;
import blackjack.dto.CardDto;
import blackjack.dto.GamerCardsDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {

    public void run() {
        PlayerGroup playerGroup = initializePlayerGroup();
        BlackJack blackJack = new BlackJack(playerGroup);
        blackJack.divideCards();

        List<GamerCardsDto> gamersCardsDto = getGamersCardsDto(blackJack.getGamersCards());
        OutputView.printGamersCards(gamersCardsDto);
    }

    private PlayerGroup initializePlayerGroup() {
        try {
            return new PlayerGroup(Player.of(InputView.requestPlayerNames()));
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorMessage(exception.getMessage());
            return initializePlayerGroup();
        }
    }

    private List<GamerCardsDto> getGamersCardsDto(List<Gamer> gamersCards) {
        List<GamerCardsDto> gamersCardsDto = new ArrayList<>();
        for (Gamer gamer : gamersCards) {
            List<CardDto> cardsDto = getCardsDto(gamer.getCardGroup());
            gamersCardsDto.add(new GamerCardsDto(gamer.getName(), cardsDto));
        }
        return gamersCardsDto;
    }

    private List<CardDto> getCardsDto(CardGroup cardGroup) {
        List<CardDto> cardsDto = new ArrayList<>();
        for (Card card : cardGroup.getCards()) {
            addOpenCard(cardsDto, card);
        }

        return cardsDto;
    }

    private void addOpenCard(List<CardDto> cardsDto, Card card) {
        if (card.isOpen()) {
            cardsDto.add(new CardDto(card.getCardShape().getName(), card.getCardNumber().getName()));
        }
    }
}

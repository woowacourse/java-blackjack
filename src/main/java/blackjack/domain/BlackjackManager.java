package blackjack.domain;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.ParticipantDto;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackManager {

    private static final int INIT_CARD_COUNT = 2;

    private final CardDeck cardDeck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackManager(final Dealer dealer, final Players players) {
        this(CardDeck.newShuffledDeck(), dealer, players);
    }

    private BlackjackManager(final CardDeck cardDeck, final Dealer dealer, final Players players) {
        this.cardDeck = cardDeck;
        this.dealer = dealer;
        this.players = players;
    }

    public void initDrawCards() {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            this.dealer.addCard(cardDeck.draw());
            this.players.drawCards(cardDeck);
        }
    }

    public ParticipantDto createDealerDto() {
        return new ParticipantDto(createCardDtos(dealer.getCards()), dealer.getScore());
    }

    public List<ParticipantDto> createPlayerDtos() {
        return players.toList()
            .stream()
            .map(player -> new ParticipantDto(
                player.getName(),
                createCardDtos(player.getCards()),
                player.getScore()
            )).collect(Collectors.toList());
    }

    private List<CardDto> createCardDtos(final List<Card> cards) {
        return cards.stream()
            .map(card -> new CardDto(card.getNumberName() + card.getPatternName()))
            .collect(Collectors.toList());
    }
}

package blackjack.service;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.dto.CardDto;
import blackjack.dto.DealerTurnResultDto;
import blackjack.dto.ParticipantResultDto;

public class DealerService {

    private final Deck deck;
    private final Dealer dealer;

    public DealerService(final Deck deck) {
        this.deck = deck;
        this.dealer = new Dealer();
    }

    public void prepare() {
        dealer.prepareGame(deck);
    }

    public CardDto findFirstCard() {
        return CardDto.of(dealer.openFirstCard());
    }

    public DealerTurnResultDto progressTurn() {
        if (!dealer.canDrawCard()) {
            return new DealerTurnResultDto(0);
        }

        int count = 0;
        do {
            dealer.hit(deck);
            count++;
        } while (dealer.canDrawCard());
        return new DealerTurnResultDto(count);
    }

    public ParticipantResultDto getResult() {
        return ParticipantResultDto.of(dealer);
    }

    public int getScore() {
        return dealer.getScore();
    }
}

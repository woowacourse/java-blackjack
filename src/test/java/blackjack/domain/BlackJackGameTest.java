package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.betting.BettingManager;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.DeckFactory;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BlackJackGameTest {

    private BettingManager bettingManager;
    private Participants participants;
    private Deck deck;

    @BeforeEach
    void init() {
        participants = new Participants(List.of(
                new Dealer(),
                new Player("kokodak"),
                new Player("dani")
        ));
        bettingManager = new BettingManager();
        bettingManager.registerBetting("kokodak", 10_000);
        bettingManager.registerBetting("dani", 50_000);
        deck = DeckFactory.createWithCount(1);
    }

    @Test
    void 카드를_더_받고_싶다면_카드를_추가로_받는다() {
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, bettingManager, 0);
        final Dealer dealer = blackJackGame.dealer();

        blackJackGame.drawOrNot(true, dealer);
        final Cards dealerCards = dealer.getCards();

        assertThat(dealerCards.count()).isEqualTo(1);
    }

    @Test
    void 카드를_더_받고_싶지_않다면_자신의_상태를_바꾼다() {
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, bettingManager, 0);
        final Dealer dealer = blackJackGame.dealer();

        blackJackGame.drawOrNot(false, dealer);

        assertThat(dealer.isDrawable()).isFalse();
    }

    @Test
    void 카드를_더_받을_수_있는_플레이어를_찾는다() {
        final Player kokodak = new Player("kokodak");
        final Player dani = new Player("dani");
        participants = new Participants(List.of(new Dealer(), kokodak, dani));
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, bettingManager, 0);

        blackJackGame.drawOrNot(false, kokodak);
        final Player drawablePlayer = blackJackGame.findDrawablePlayer();

        assertThat(drawablePlayer).isEqualTo(dani);
    }

    @Test
    void 카드를_더_받을_수_있는_플레이어가_없다면_예외를_던진다() {
        final Player kokodak = new Player("kokodak");
        final Player dani = new Player("dani");
        participants = new Participants(List.of(new Dealer(), kokodak, dani));
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, bettingManager, 0);

        blackJackGame.drawOrNot(false, kokodak);
        blackJackGame.drawOrNot(false, dani);

        assertThatThrownBy(blackJackGame::findDrawablePlayer)
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("카드를 받을 수 있는 플레이어가 존재하지 않습니다.");
    }

    @Test
    void 카드를_더_받을_수_있는_플레이어가_있는지_확인한다() {
        final BlackJackGame blackJackGame = new BlackJackGame(participants, deck, bettingManager, 0);

        assertThat(blackJackGame.existDrawablePlayer()).isTrue();
    }
}

package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participants;
import blackjack.domain.Person;
import blackjack.domain.Player;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class BlackJackController {
    private Participants participants;
    private final Cards uniqueCards;

    public BlackJackController() {
        this.uniqueCards = createUniqueCards();
    }

    private Cards createUniqueCards() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Stream.of(new Card(suit, rank)))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }

    public void run() {
        participants = new Participants(new Dealer(), repeat(this::getPlayers));
        initDrawCard();
        printInitStatus();
        drawMoreCardForPlayers();
        drawDealerMoreCard();
        printAllStatus();
        printGameResult();
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    private List<Player> getPlayers() {
        String[] names = InputView.readPlayerNames();
        validateDuplicate(names);
        return Arrays.stream(names)
                .map(Player::new)
                .collect(toList());
    }

    private void validateDuplicate(String[] names) {
        long uniqueNamesCount = Arrays.stream(names)
                .distinct()
                .count();
        if (uniqueNamesCount != names.length) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 있습니다.");
        }
    }

    private void initDrawCard() {
        List<Person> persons = participants.getPeople();
        for (Person person : persons) {
            drawTwoCards(person, uniqueCards);
        }
    }

    private void drawTwoCards(Person person, Cards cards) {
        person.addCard(cards.drawCard());
        person.addCard(cards.drawCard());
    }

    private void printInitStatus() {
        List<String> playerNames = participants.getPlayers().stream()
                .map(Person::getName)
                .collect(toList());
        OutputView.printDefaultDrawCardMessage(playerNames);
        for (Person person : participants.getPeople()) {
            OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getInitCards()));
        }
    }

    private void drawMoreCardForPlayers() {
        for (Person person : participants.getPlayers()) {
            repeat(() -> drawMoreCard(person, uniqueCards));
        }
    }

    private void repeat(Runnable runnable) {
        try {
            runnable.run();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            repeat(runnable);
        }
    }

    private void drawMoreCard(Person person, Cards cards) {
        while (decideDraw(person)) {
            validateOverScore(person);
            person.addCard(cards.drawCard());
            OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getCards()));
        }
        OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getCards()));
    }

    private boolean decideDraw(Person person) {
        String decision = InputView.readDrawCardDecision(person.getName());
        if (decision.equals("y") || decision.equals("n")) {
            return decision.equals("y");
        }
        throw new IllegalArgumentException("[ERROR] y 또는 n만 입력 가능합니다.");
    }

    private void validateOverScore(Person person) {
        if (!person.canDrawCard()) {
            throw new IllegalArgumentException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
        }
    }

    private List<String> getCardsStatus(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
    }

    private void drawDealerMoreCard() {
        Person dealer = participants.getDealer();
        OutputView.printDealerDrawCardMessage(dealer.getScore());
        if (dealer.canDrawCard()) {
            dealer.addCard(uniqueCards.drawCard());
        }
    }

    private void printAllStatus() {
        for (Person person : participants.getPeople()) {
            printPersonStatus(person);
        }
    }

    private void printPersonStatus(Person person) {
        OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getCards()), person.getScore());
    }

    private void printGameResult() {
        OutputView.printGameEndMessage();
        Person dealer = participants.getDealer();
        List<GameResult> dealerGameResults = participants.getPlayers()
                .stream()
                .map(dealer::matchGame)
                .collect(toList());
        OutputView.printDealerResult(dealerGameResults);
        for (Person person : participants.getPlayers()) {
            OutputView.printPlayerResult(person.getName(), person.matchGame(dealer));
        }
    }
}

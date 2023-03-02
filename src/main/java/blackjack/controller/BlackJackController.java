package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Person;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class BlackJackController {

    public void run() {
        List<Person> persons = repeat(this::getPersons);
        Dealer dealer = new Dealer();
        persons.add(0, dealer);
        Cards uniqueCards = dealer.createUniqueCards();
        initDrawCard(persons, uniqueCards);
        printInitStatus(persons);
        drawMoreCardForAll(persons, uniqueCards);
        drawDealerMoreCard(dealer, uniqueCards);
        printAllStatus(persons);
        printGameResult(dealer, persons);
    }

    private <T> T repeat(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return repeat(supplier);
        }
    }

    private List<Person> getPersons() {
        String[] names = InputView.readPersonNames();
        validateDuplicate(names);
        return Arrays.stream(names)
                .map(Person::new)
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

    private void initDrawCard(List<Person> persons, Cards uniqueCards) {
        for (Person person : persons) {
            drawTwoCards(person, uniqueCards);
        }
    }

    private void drawTwoCards(Person person, Cards cards) {
        person.addCard(cards.drawCard());
        person.addCard(cards.drawCard());
    }

    private void printInitStatus(List<Person> persons) {
        List<String> personNames = getWithoutDealer(persons)
                .stream()
                .map(Person::getName)
                .collect(toList());
        OutputView.printDefaultDrawCardMessage(personNames);
        for (Person person : persons) {
            OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getInitCards()));
        }
    }

    private List<Person> getWithoutDealer(List<Person> persons) {
        return persons.stream()
                .filter(Person::isPlayer)
                .collect(toList());
    }

    private void drawMoreCardForAll(List<Person> persons, Cards uniqueCards) {
        for (Person person : getWithoutDealer(persons)) {
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

    private List<String> getCardsStatus(List<Card> cards) {
        return cards.stream()
                .map(card -> card.getRank() + card.getSuit())
                .collect(toList());
    }

    private void drawDealerMoreCard(Dealer dealer, Cards uniqueCards) {
        OutputView.printDealerDrawCardMessage(dealer.getScore());
        if (dealer.canDrawCard()) {
            dealer.addCard(uniqueCards.drawCard());
        }
    }

    private void printAllStatus(List<Person> persons) {
        for (Person person : persons) {
            printPersonStatus(person);
        }
    }

    private void printPersonStatus(Person person) {
        OutputView.printCardsStatus(person.getName(), getCardsStatus(person.getCards()), person.getScore());
    }

    private void printGameResult(Dealer dealer, List<Person> persons) {
        OutputView.printGameEndMessage();
        List<GameResult> dealerGameResults = getWithoutDealer(persons)
                .stream()
                .map(dealer::matchGame)
                .collect(toList());
        OutputView.printDealerResult(dealerGameResults);
        for (Person person : getWithoutDealer(persons)) {
            OutputView.printPersonResult(person.getName(), person.matchGame(dealer));
        }
    }
}

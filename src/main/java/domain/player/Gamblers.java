package domain.player;

import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gamblers {
    private final List<Gambler> gamblers;

    public Gamblers(List<String> names) {
        validateNonDuplicate(names);

        gamblers = new ArrayList<>();
        init(names);
    }

    private void validateNonDuplicate(List<String> names) {
        Set<String> namesSet = new HashSet<>(names);
        if (namesSet.size() != names.size()) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }

    private void init(List<String> names) {
        names.stream().map(Gambler::new).forEach(gamblers::add);

        //for(String name : names){
        //    gamblers.add(new Gambler(name));
        //} 학습을 위해 남겨두었습니다.
    }

    public List<Gambler> getGamblers() {
        return gamblers;
    }

}

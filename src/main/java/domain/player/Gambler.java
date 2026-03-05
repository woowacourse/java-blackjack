package domain.player;

import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.List;

public class Gambler extends Player {
    private final String name;

    private static final int GAMBLER_NAME_MAX_LENGTH = 10;
    private static final int GAMBLER_NAME_MIN_LENGTH = 2;

    public Gambler(String name){
        super();
        validate(name);
        this.name = name;
    }


    private void validate(String name){
        validateContainsNumber(name);
        validateLength(name);
    }

    private void validateContainsNumber(String name){
        if(name.matches(".*\\d.*")) throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
    }

    private void validateLength(String name){
        if(name.length() > GAMBLER_NAME_MAX_LENGTH || name.length() < GAMBLER_NAME_MIN_LENGTH)
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
    }

}

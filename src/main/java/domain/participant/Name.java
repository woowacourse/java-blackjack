package domain.participant;

import domain.ExceptionMessage;

public class Name {
    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    public void validate(String name){
        if(name.isBlank()){
            throw new IllegalArgumentException(ExceptionMessage.BLANK_NAME_NOT_ALLOWED.getMessage());
        }
        if(name.length() < 1 || name.length() > 10){
            throw new IllegalArgumentException(ExceptionMessage.NAME_OUT_OF_RANGE.getMessage());
        }
    }
    @Override
    public String toString() {
        return name;
    }
}

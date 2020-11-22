package it.unibo.oop.lab.mvc;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {

    private String nextString;
    private List<String> listPrintedStrings;

    public ControllerImpl() {
        listPrintedStrings = new ArrayList<String>();
    }

    public void setNextString(final String nextString) throws NullPointerException {
        if (nextString == null) {
            throw new NullPointerException();
        } else {
            this.nextString = nextString;
        }
    }

    public String getNextString() {
        return this.nextString;
    }

    public List<String> getHistoryList() {
        return listPrintedStrings;
    }

    public void printCurrentString() throws IllegalStateException {
        if (nextString == null) {
            throw new IllegalStateException();
        } else {
            System.out.println(nextString);
            listPrintedStrings.add(nextString);
        }

    }

}

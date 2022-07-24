package com.sudoku.elements;

import java.util.ArrayList;
import java.util.List;

public class SudokuElement {
    int value;

    public SudokuElement(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

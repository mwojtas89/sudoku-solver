package com.sudoku.elements;

import java.util.ArrayList;
import java.util.List;

public class SudokuRow {
    List<SudokuElement> row;

    public SudokuRow(List<SudokuElement> row) {
        this.row = row;
    }

    public List<SudokuElement> getRow() {
        return row;
    }

    public void setRow(List<SudokuElement> row) {
        this.row = row;
    }
}

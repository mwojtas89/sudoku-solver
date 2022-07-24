package com.sudoku.elements;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
    List<SudokuRow> board;

    public SudokuBoard(List<SudokuRow> board) {
        this.board = board;
    }

    public void print() {
        for(int i = 0; i<board.size(); i++) {
            if(i % 3 ==0 && i != 0) {
                System.out.println("-----------");
            }
            SudokuRow sudokuRow = board.get(i);
            List<SudokuElement> sudokuElements = sudokuRow.getRow();
            for(int n=0; n<sudokuElements.size(); n++) {
                if(n % 3 == 0 && n != 0) {
                    System.out.print("|");
                }
                SudokuElement sudokuElement = sudokuElements.get(n);
                System.out.print(sudokuElement.getValue());
                if(n==8) {
                    System.out.print("\n");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "SudokuBoard{" +
                "board=" + board +
                '}';
    }

    public List<SudokuRow> getBoard() {
        return board;
    }

    public void setBoard(List<SudokuRow> board) {
        this.board = board;
    }
}

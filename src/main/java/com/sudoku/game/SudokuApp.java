package com.sudoku.game;

import com.sudoku.elements.SudokuBoard;

import java.util.List;

public class SudokuApp {
    public static void main(String[] args) {
        boolean gameFinished = false;
        while(!gameFinished) {
            SudokuGame theGame = new SudokuGame();
            List <String> readedBoard = theGame.readBoardFromFile();
            List<List<Integer>> transferedBoard = theGame.transferToIntegerList(readedBoard);
            SudokuBoard sudokuBoard = theGame.createBoard(transferedBoard);
            System.out.println("Loaded board from file : \n");
            sudokuBoard.print();
            System.out.println("Starting to process sudoku...");
            if(theGame.solveSudoku(sudokuBoard)) {
                System.out.println("Sudoku solved");
                sudokuBoard.print();
            } else {
                System.out.println("Unsolvable");
            }
            gameFinished = theGame.resolveSudoku();
        }
        System.out.println("Thank you for using Sudoku Solver v. 1.1");
    }
}

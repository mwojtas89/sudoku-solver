package com.sudoku.game;

import com.sudoku.elements.SudokuBoard;
import com.sudoku.elements.SudokuElement;
import com.sudoku.elements.SudokuRow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class SudokuGame {

    private static final int SIZE = 9;

    public boolean resolveSudoku() {
        boolean play = false;
        String answer;
        System.out.println("Do you want to resolve sudoku ? Please type YES or NO");
        Scanner scan = new Scanner(System.in);
        answer = scan.nextLine().toUpperCase();
        while (!answer.equals("YES") && !answer.equals("NO")) {
            System.out.println("You have typed wrong answer please try again...\n" + "Type YES or NO");
            answer = scan.nextLine().toUpperCase();
        }
        switch (answer) {
            case "YES" : play = false; break;
            case "NO" : play = true; break;
        }
        return play;
    }

    public List <String> readBoardFromFile () {
        List<String> board = new ArrayList<>();
        String fileName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please give file name to read:");
        fileName = scanner.nextLine();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("/Users/humptydumpty/IdeaProjects/sudoku-solver/src/main/resources/"+fileName))) {
            for(int i =0; i<SIZE; i++) {
                board.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
    }

    public List<List<Integer>> transferToIntegerList (List<String> board) {
        List<List<Integer>> boardList = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            String row = board.get(i);
            List<Integer> rowElements = new ArrayList<>();
            String[] elements = row.split(" ");
            for (int n = 0; n < SIZE; n++) {
                int element;
                element = Integer.parseInt(elements[n]);
                rowElements.add(element);
            }
            boardList.add(rowElements);
        }
        return boardList;
    }

    public SudokuBoard createBoard (List<List<Integer>> board) {
        List<SudokuRow> sudokuRows = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            List<Integer> elements;
            List<SudokuElement> sudokuElements = new ArrayList<>();
            elements = board.get(i);
            for (int n = 0; n < elements.size(); n++) {
                SudokuElement sudokuElement = new SudokuElement(elements.get(n));
                sudokuElements.add(sudokuElement);
            }
            SudokuRow sudokuRow = new SudokuRow(sudokuElements);
            sudokuRows.add(sudokuRow);
        }
        return new SudokuBoard(sudokuRows);
    }

    public void printBoardList (List<List<Integer>> board) {
        for(int i=0; i<board.size(); i++) {
            if(i % 3 == 0 && i != 0) {
                System.out.println("-----------");
            }
            List<Integer> elements;
            elements = board.get(i);
            for(int n=0 ; n<elements.size(); n++) {
                if(n%3==0 && n!=0) {
                    System.out.print("|");
                }
                System.out.print(elements.get(n));
                if(n==8) {
                    System.out.print("\n");
                }
            }
        }
    }

    public static boolean isInTheRow (SudokuBoard board, int number, int row) {
        SudokuRow sudokuRow = board.getBoard().get(row);
        for(int i =0; i<sudokuRow.getRow().size(); i++) {
            if(sudokuRow.getRow().get(i).getValue() == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInTheCol (SudokuBoard board, int number, int col) {
        for(int i = 0; i< SIZE; i++) {
            SudokuRow sudokuRow = board.getBoard().get(i);
            if(sudokuRow.getRow().get(col).getValue() == number) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInTheBox (SudokuBoard board, int number, int col, int row) {
        int localBoxRow = row - row % 3;
        int localBoxCol = col - col % 3;

        for(int i = localBoxRow; i < localBoxRow + 3; i++) {
            SudokuRow sudokuRow = board.getBoard().get(localBoxRow);
            for(int n = localBoxCol; n < localBoxCol + 3; n++) {
                if(sudokuRow.getRow().get(n).getValue() == number) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isValidPlacement (SudokuBoard board, int number, int col, int row) {
        return !isInTheRow(board, number, row) &&
                !isInTheCol(board, number, col) &&
                !isInTheBox(board, number, col, row);
    }

    public boolean solveSudoku (SudokuBoard board) {
        for(int i = 0; i<board.getBoard().size(); i++) {
            SudokuRow sudokuRow = board.getBoard().get(i);
            List<SudokuElement> elementsInRow;
            elementsInRow=sudokuRow.getRow();
            for(int n=0; n< elementsInRow.size(); n++) {
                if(elementsInRow.get(n).getValue()==0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValidPlacement(board, num, n, i)) {
                            elementsInRow.get(n).setValue(num);

                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                elementsInRow.get(n).setValue(0);
                            }
                        }
                    }
                }
            }
        }return false;
    }
}


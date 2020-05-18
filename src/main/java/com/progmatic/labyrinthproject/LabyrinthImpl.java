package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    private CellType[][] labyrint = new CellType[getHeight()][getWidth()];
    private int width = -1;
    private int height = -1;
    private Coordinate playerPosition;

    public LabyrinthImpl() {
        playerPosition = new Coordinate(0, 0);
    }


    @Override
    public int getWidth() {

        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            int width = Integer.parseInt(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
/*
                        for (int row = 0; row < labyrint.length; row++) {
                            for (int column = 0; column < labyrint[row].length; column++) {
                                labyrint[row][column] = line.charAt(ww);
                            }
                        }
*/
                        case 'W':
                            labyrint[hh][ww] = CellType.WALL;
                            break;
                        case 'E':
                            labyrint[hh][ww] = CellType.END;
                            break;
                        case 'S':
                            labyrint[hh][ww] = CellType.START;
                            break;
                        default:
                            labyrint[ww][hh] = CellType.EMPTY;
                            break;
                    }

                }
            }

        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getCol() < 0 || c.getRow() < 0 || c.getCol() >= width || c.getRow() >= height) {

            throw new CellException(c, "Nem létező koordináta");
        }
        return labyrint[c.getCol()][c.getRow()];

    }

    @Override
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;

        labyrint = new CellType[width][height];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                labyrint[col][row] = CellType.EMPTY;
            }
        }

    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        if (c.getRow() < 0 || c.getRow() >= height || c.getCol() >= width || c.getCol() < 0) {
            throw new CellException(c, "Hibás koordináták.");
        } else {
            labyrint[c.getCol()][c.getRow()] = type;
            if (type == CellType.START) {
                playerPosition = c;
            }
        }

    }

    @Override
    public Coordinate getPlayerPosition() {

        return playerPosition;
    }

    @Override
    public boolean hasPlayerFinished() {


        for (int row = 0; row < labyrint.length; row++) {
            for (int col = 0; col < labyrint[row].length; col++) {
                if (labyrint[row][col] == CellType.END) {
                    return true;
                }
            }
        }
        return false;


    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> step = new ArrayList<>();
        int playerCol = playerPosition.getCol();
        int playerRow = playerPosition.getRow();

        if (playerCol < width && labyrint[playerCol + 1][playerRow] != CellType.WALL) {
            step.add(Direction.EAST);
        }
        if (playerCol > 0 && labyrint[playerCol - 1][playerRow] != CellType.WALL) {
            step.add(Direction.WEST);
        }
        if (playerRow < height && labyrint[playerCol][playerRow + 1] != CellType.WALL) {
            step.add(Direction.SOUTH);
        }
        if (playerPosition.getRow() > 0 && labyrint[playerCol][playerRow - 1] != CellType.WALL) {
            step.add(Direction.NORTH);
        }

        return step;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        if (possibleMoves().contains(direction)) {

        }

    }

}
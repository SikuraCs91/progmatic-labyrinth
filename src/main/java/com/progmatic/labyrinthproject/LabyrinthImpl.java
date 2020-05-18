package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {
    public int width;
    public int height;
    public CellType[][] labyrint = new CellType[height][width];



    public LabyrinthImpl() {
        this.width = getWidth();
        this.height = getHeight();
    }



    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
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
        return null;
    }

    @Override
    public void setSize(int width, int height) {
    setHeight(height);
    setWidth(width);



    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {


    }

    @Override
    public Coordinate getPlayerPosition() {
        return null;
    }

    @Override
    public boolean hasPlayerFinished() {
        return false;
    }

    @Override
    public List<Direction> possibleMoves() {
        return null;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {

    }

}

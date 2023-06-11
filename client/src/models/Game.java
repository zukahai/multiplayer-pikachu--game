package models;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import utils.Util;

public class Game implements Serializable{
    private int board[][];
    private int roomID;
    HashMap <User, Socket> players = new HashMap<>();

    public Game() {
        
    }

    public Game(int roomID) {
        this.roomID = roomID;
        this.initBoard();
    }

    public Game(int board[][]) {
        this.board = board;
    }

    public Game(int board[][], int roomID) {
        this.board = board;
        this.roomID = roomID;
    }

    void initBoard() {
        int list[] = new int[9 * 16];
        for (int i = 0; i < 9 * 16; i ++) {
            int value = (int) (Math.round(Math.random() * 100000)) % 16 + 1;
            list[i++] = value;
            list[i] = value;
        }

        for (int i = 0; i < 100; i++) {
            int randomIndex1 = (int) (Math.round(Math.random() * 100000)) % list.length;
            int randomIndex2 = (int) (Math.round(Math.random() * 100000)) % list.length;
            //swap
            int temp = list[randomIndex1];
            list[randomIndex1] = list[randomIndex2];
            list[randomIndex2] = temp;
        }
        
        int indexList = 0;
        int board[][] = new int[9][16];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = list[indexList++];
            }
        }
        
        this.board = board;
    }

    public ArrayList<Socket> getSockets() {
        ArrayList<Socket> sockets = new ArrayList<>();
        for (User user : players.keySet()) {
            sockets.add(players.get(user));
        }
        return sockets;
    }

    public void addPlayer(User user, Socket socket) {
        this.players.put(user, socket);
    }

    public void removePlayer(User user) {
        this.players.remove(user);
    }

    int getNumberOfPlayers() {
        return this.players.size();
    }

    public HashMap<User, Socket> getPlayers() {
        return this.players;
    }

    public Socket getPlayer(String username) {
        return this.players.get(username);
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int getRoomID() {
        return roomID;
    }

    
}

package chess;


import chess.pieces.*;

import java.util.*;
import java.util.stream.Collectors;

import static chess.Player.Black;
import static chess.Player.White;
import static chess.Player.oppositePlayer;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

    /**
     * The current player
     */
    private Player currentPlayer = White;

    /**
     * A map of board positions to pieces at that position
     */
    private Map<Position, Piece> positionToPieceMap;

    /**
     * Create the game state.
     */
    public GameState() {
        positionToPieceMap = new HashMap<>();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Call to initialize the game state into the starting positions
     */
    public void reset() {
        // White Pieces
        placePiece(new Rook(White), new Position("a1"));
        placePiece(new Knight(White), new Position("b1"));
        placePiece(new Bishop(White), new Position("c1"));
        placePiece(new Queen(White), new Position("d1"));
        placePiece(new King(White), new Position("e1"));
        placePiece(new Bishop(White), new Position("f1"));
        placePiece(new Knight(White), new Position("g1"));
        placePiece(new Rook(White), new Position("h1"));
        placePiece(new Pawn(White), new Position("a2"));
        placePiece(new Pawn(White), new Position("b2"));
        placePiece(new Pawn(White), new Position("c2"));
        placePiece(new Pawn(White), new Position("d2"));
        placePiece(new Pawn(White), new Position("e2"));
        placePiece(new Pawn(White), new Position("f2"));
        placePiece(new Pawn(White), new Position("g2"));
        placePiece(new Pawn(White), new Position("h2"));

        // Black Pieces
        placePiece(new Rook(Black), new Position("a8"));
        placePiece(new Knight(Black), new Position("b8"));
        placePiece(new Bishop(Black), new Position("c8"));
        placePiece(new Queen(Black), new Position("d8"));
        placePiece(new King(Black), new Position("e8"));
        placePiece(new Bishop(Black), new Position("f8"));
        placePiece(new Knight(Black), new Position("g8"));
        placePiece(new Rook(Black), new Position("h8"));
        placePiece(new Pawn(Black), new Position("a7"));
        placePiece(new Pawn(Black), new Position("b7"));
        placePiece(new Pawn(Black), new Position("c7"));
        placePiece(new Pawn(Black), new Position("d7"));
        placePiece(new Pawn(Black), new Position("e7"));
        placePiece(new Pawn(Black), new Position("f7"));
        placePiece(new Pawn(Black), new Position("g7"));
        placePiece(new Pawn(Black), new Position("h7"));
    }

    /**
     * Get the piece at the position specified by the String
     *
     * @param colrow The string indication of position; i.e. "d5"
     * @return The piece at that position, or null if it does not exist.
     */
    Piece getPieceAt(String colrow) {
        Position position = new Position(colrow);
        return getPieceAt(position);
    }

    /**
     * Get the piece at a given position on the board
     *
     * @param position The position to inquire about.
     * @return The piece at that position, or null if it does not exist.
     */
    public Piece getPieceAt(Position position) {
        return positionToPieceMap.get(position);
    }

    /**
     * Method to place a piece at a given position
     *
     * @param piece    The piece to place
     * @param position The position
     */
    public void placePiece(Piece piece, Position position) {
        positionToPieceMap.put(position, piece);
    }

    /**
     * Method to place a piece at a given position pointed as string
     *
     * @param piece  The piece to place
     * @param colrow position as string
     */
    void placePiece(Piece piece, String colrow) {
        positionToPieceMap.put(new Position(colrow), piece);
    }

    /**
     * Moves piece from 'from' position to 'to' position. Throws IllegalArgumentException, if no piece placed on from position or move impossible
     *
     * @param from start position
     * @param to   target position
     * @throws IllegalArgumentException
     */
    void move(String from, String to) throws IllegalArgumentException {
        move(new Position(from), new Position(to), currentPlayer);
    }

    /**
     * Checks if position can be occupied
     *
     * @param currentPosition position to check
     * @return true if position in board and no another piece occupies it
     */
    public boolean isReachable(Position currentPosition) {
        return currentPosition.isNotOutOfTheBoard() && noObstacle(currentPosition);
    }

    public boolean hasPieceOfAnotherPlayer(Player player, Position position) {
        Piece piece = getPieceAt(position);
        return Objects.nonNull(piece) && piece.getOwner() != player;
    }

    public boolean noObstacle(Position position) {
        return getPieceAt(position) == null;
    }

    void switchPlayers() {
        currentPlayer = Player.oppositePlayer(currentPlayer);
    }

    private void move(Position from, Position to, Player player) throws IllegalArgumentException {
        Piece piece = getPieceAt(from);
        if (Objects.isNull(piece)) {
            throw new IllegalArgumentException("No piece on from position: " + from);
        }
        if (piece.getOwner() != player) {
            throw new IllegalArgumentException("Piece belongs to another player");
        }
        if (!findAllPossibleMovesForPosition(from, piece).contains(new Move(piece, from, to))) {
            throw new IllegalArgumentException("To position isn't reachable: " + to);
        }
        positionToPieceMap.remove(from);
        placePiece(piece, to);
    }

    Collection<Move> findAllPossibleMovesForWhitePlayer() {
        return findAllPossibleMoves(White);
    }

    Collection<Move> findAllPossibleMovesForBlackPlayer() {
        return findAllPossibleMoves(Black);
    }

    Collection<Take> findAllPossibleTakesForWhitePlayer() {
        return findAllPossibleTakes(White);
    }

    Collection<Take> findAllPossibleTakesForBlackPlayer() {
        return findAllPossibleTakes(Black);
    }

    Collection<Take> findAttacksOnPosition(Position p) {
        Piece piece = getPieceAt(p);
        if (Objects.isNull(piece)) return Collections.emptyList();
        Player offer = oppositePlayer(piece.getOwner());
        Collection<Take> allEnemiesTakes = findAllPossibleTakes(offer);
        return allEnemiesTakes.stream()
                .filter(take -> take.getVictim() == piece && take.getVictimPosition().equals(p))
                .collect(Collectors.toList());

    }

    Collection<Take> findAttacksOnKing(Player player) {
        Collection<Position> kingPosition = findPiecePositions(new King(player));
        return kingPosition.stream()
                .flatMap(position -> findAttacksOnPosition(position).stream())
                .collect(Collectors.toList());
    }

    private List<Position> findPiecePositions(Piece p) {
        return positionToPieceMap.entrySet().stream()
                .filter(ppe -> ppe.getValue().equals(p))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean hasKingSafeMoves(Player player) {
        King king = new King(player);
        List<Position> kingPositions =  findPiecePositions(king);
        assert kingPositions.size() == 1;
        Position kingPosition = kingPositions.get(0);
        Collection<Move> possibleKingMoves = findAllPossibleMovesForPosition(kingPosition, king);
        boolean hasKingSafeMoves = false;
        for(Move move : possibleKingMoves) {
            assert move.getFrom() == kingPosition;
            Position to = move.getTo();
            move(move.getFrom(), to, player);
            Collection<Take> attacksOnNewPosition =  findAttacksOnPosition(to);
            move(to, move.getFrom(), player);
            if(attacksOnNewPosition.isEmpty()) {
                hasKingSafeMoves = true;
                break;
            }
        }
        return hasKingSafeMoves;
    }

    boolean canKingBeCovered(Player p) {
        return true;
    }

    boolean canEnemyBeTakenByPlayer(Position pos, Player p) {
        return findAllPossibleTakes(p).stream().filter(take -> take.getVictimPosition().equals(pos)).findAny().isPresent();
    }

    boolean isOppositeKingCheckMated(Player p) {
        boolean isEnemyCheckMated = false;
        Player oppositePlayer = oppositePlayer(p);
        Collection<Take> attacksOnEnemyKing = findAttacksOnKing(oppositePlayer);
        for (Take attack : attacksOnEnemyKing) {
            Position attackerPosition = attack.getAttackerPosition();
            boolean attackOnKingCanBeAvoid = canEnemyBeTakenByPlayer(attackerPosition, oppositePlayer)
                    || hasKingSafeMoves(oppositePlayer)
                    || canKingBeCovered(oppositePlayer);
            if (!attackOnKingCanBeAvoid) {
                isEnemyCheckMated = true;
                break;
            }
        }
        return isEnemyCheckMated;
    }

    private Collection<Move> findAllPossibleMoves(Player player) {
        return positionToPieceMap.entrySet().stream()
                .filter(p -> p.getValue().getOwner() == player)
                .flatMap(p -> findAllPossibleMovesForPosition(p.getKey(), p.getValue()).stream())
                .sorted((o1, o2) -> o1.toString().compareTo(o2.toString()))
                .collect(Collectors.toList());

    }

    private Collection<Take> findAllPossibleTakes(Player player) {
        return positionToPieceMap.entrySet().stream()
                .filter(p -> p.getValue().getOwner() == player)
                .flatMap(p -> findAllPossibleTakesForPosition(p.getKey(), p.getValue()).stream())
                .collect(Collectors.toList());

    }

    private Collection<Move> findAllPossibleMovesForPosition(Position startPosition, Piece piece) {
        return piece.getMovementStrategies().stream()
                .flatMap(s -> s.findPossibleMovesFromPositionForGameState(startPosition, this).stream())
                .map(endPosition -> new Move(piece, startPosition, endPosition))
                .collect(Collectors.toList());
    }

    private Collection<Take> findAllPossibleTakesForPosition(Position startPosition, Piece piece) {
        return piece.getMovementStrategies().stream()
                .flatMap(s -> s.findPossibleTakingFromPositionForGameState(startPosition, this, currentPlayer).stream())
                .collect(Collectors.toList());
    }


}

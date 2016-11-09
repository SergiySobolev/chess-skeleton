package chess;


import chess.pieces.Piece;

import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Describes beating victim by attacker via path
 */
public class Take {
    private Piece attacker;
    private Position attackerPosition;
    private Piece victim;
    private Position victimPosition;
    private Collection<Position> pathFromAttackerToVictim;

    public Take(Piece attacker, Position attackerPosition,  Piece victim, Position victimPosition, Collection<Position> pathFromAttackerToVictim) {
        this.attacker = attacker;
        this.attackerPosition = attackerPosition;
        this.victim = victim;
        this.victimPosition = victimPosition;
        this.pathFromAttackerToVictim = pathFromAttackerToVictim;
    }

    public Piece getAttacker() {
        return attacker;
    }

    public Position getAttackerPosition() {
        return attackerPosition;
    }

    public Piece getVictim() {
        return victim;
    }

    public Position getVictimPosition() {
        return victimPosition;
    }

    public Collection<Position> getPathFromAttackerToVictim() {
        return pathFromAttackerToVictim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Take take = (Take) o;

        if (!take.getAttacker().equals(attacker)) return false;
        if (!take.getVictim().equals(victim)) return false;
        if (!take.getPathFromAttackerToVictim().equals(pathFromAttackerToVictim)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attacker,victim,pathFromAttackerToVictim);
    }

    @Override
    public String toString() {
        return attacker.getIdentifier() +
                " on " +
                attackerPosition.toString()  +
                " take " +
                victim.getIdentifier() +
                " on " +
                victimPosition.toString() +
                " via path " +
                pathFromAttackerToVictim.toString();
    }

}

package com.lukaspradel.ssp;

import javax.persistence.*;

@Entity
public class Player {

    private static final int STARTING_SCORE = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "score", nullable = false)
    private int score;

    @Column(name = "won", nullable = false)
    private int won;

    @Column(name = "lost", nullable = false)
    private int lost;

    private Player() {
    }

    public Player(String name, String url) {
        this.name = name;
        this.url = url;
        this.score = STARTING_SCORE;
        this.won = 0;
        this.lost = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getScore() {
        return score;
    }

    public int getWon() {
        return won;
    }

    public int getLost() {
        return lost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (score != player.score) return false;
        if (won != player.won) return false;
        if (lost != player.lost) return false;
        if (!name.equals(player.name)) return false;
        return url.equals(player.url);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + url.hashCode();
        result = 31 * result + score;
        result = 31 * result + won;
        result = 31 * result + lost;
        return result;
    }
}

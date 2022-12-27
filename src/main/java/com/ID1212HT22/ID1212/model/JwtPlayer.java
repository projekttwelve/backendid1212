package com.ID1212HT22.ID1212.model;

public class JwtPlayer {
    private final String jwtToken;
    private final Player player;

    public JwtPlayer(String jwtToken, Player p){
        this.jwtToken = jwtToken;
        this.player = p;
    }

    public String getjwtToken(){
        return jwtToken;
    }
    public Player getPlayer(){
        return player;
    }
}

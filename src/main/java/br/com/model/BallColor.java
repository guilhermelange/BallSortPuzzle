package br.com.model;

import java.awt.Color;

public class BallColor {
    private String nome;
    private String hexCode;
    private Color color;

    public BallColor(String nome, String hexCode) {
        this.nome = nome;
        this.hexCode = hexCode;
        this.color = Color.decode(hexCode);
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHexCode() {
        return hexCode;
    }

    public String getNome() {
        return nome;
    }       

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Cor: " + getNome();
    }
}

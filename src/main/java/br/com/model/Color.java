package br.com.model;

import javax.swing.JPanel;

public class Color {
    private String nome;
    private String hexCode;

    public Color(String nome, String hexCode) {
        this.nome = nome;
        this.hexCode = hexCode;
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

    @Override
    public String toString() {
        return "Cor: " + getNome();
    }
}

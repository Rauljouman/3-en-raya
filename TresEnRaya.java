package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TresEnRaya extends JFrame{
    private int ronda = 0;
    private boolean jugadorVerde = true;
    private JButton[][] tablero = new JButton[3][3];

    public TresEnRaya() {

        //Tamaño y layout
        this.setTitle("Tres en raya");
        this.setSize(400,400);
        this.setLayout(new GridLayout(3,3));

        // Crear y agregar botones al tablero
        for (int i = 0; i < 3; i++) {
            for ( int j = 0; j < 3; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setBackground(Color.BLACK);
                tablero[i][j].addActionListener(e -> jugar(e));
                this.add(tablero[i][j]);
            }
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void jugar(ActionEvent e) {

        //Obtenemos el input del boton que clicko
        JButton botonPulsado = (JButton) e.getSource();

        //Turno del greenPlayer

        if (jugadorVerde) {
            botonPulsado.setBackground(Color.GREEN);
            botonPulsado.setEnabled(false);
            jugadorVerde = false;
        }
        // Turno del redPlayer
        else {
            botonPulsado.setBackground(Color.RED);
            botonPulsado.setEnabled(false);
            jugadorVerde = true;
        }
        ronda++;
        verificarFinalPartida();
    }

    private boolean verificar3Raya(JButton p1, JButton p2, JButton p3){
        return p1.getBackground() == p2.getBackground() && p2.getBackground() == p3.getBackground()
                && (p1.getBackground() == Color.RED || p1.getBackground() == Color.GREEN);
    }

    private void verificarFinalPartida() {

        //3 en raya en horizontal o vertical
        for (int i = 0; i < 3; i++) {
            if (verificar3Raya(tablero[i][0], tablero[i][1], tablero[i][2])
                    || verificar3Raya(tablero[0][i], tablero[1][i], tablero[2][i])) {
                victoria();
                return;
            }
        }
            //3 en raya en diagonal
        if (verificar3Raya(tablero[0][0], tablero[1][1], tablero[2][2])
                || verificar3Raya(tablero[0][2], tablero[1][1], tablero[2][0])) {
            victoria();
            return;
        }

        //Empate
        if (ronda == 9) {
            empate();
        }
    }

        private void victoria() {
            String ganador;

            if(jugadorVerde) {
                ganador = "Rojo";
            }
            else {
                ganador = "Verde";
            }
            JOptionPane.showMessageDialog(this,"El ganador es el jugador " + ganador);
            reiniciarJuego();
        }

        private void empate() {
            JOptionPane.showMessageDialog(this, "Empate");
            reiniciarJuego();
        }

        private void reiniciarJuego() {
        //Reiniciar botones

            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[i].length; j++) {
                    tablero[i][j].setEnabled(true);
                    tablero[i][j].setBackground(Color.black);
                }
            }

            jugadorVerde = true;
            ronda = 0;
        }









}

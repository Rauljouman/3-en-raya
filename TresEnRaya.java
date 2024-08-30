package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TresEnRaya extends JFrame{
    private int ronda = 0;
    private boolean jugadorVerde = true; // True para verde (X), False para rojo (O)
    private JButton[][] tablero = new JButton[3][3];

    public TresEnRaya() {

        // Tamaño y layout
        this.setTitle("Tres en raya");
        this.setSize(400, 400);
        this.setLayout(new GridLayout(3, 3));
        this.getContentPane().setBackground(Color.BLACK); // Fondo de la ventana

        // Crear y agregar botones al tablero
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = new JButton();
                tablero[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // Tamaño grande
                tablero[i][j].setForeground(Color.WHITE); // Color del texto
                tablero[i][j].setFocusPainted(false); // Elimina el borde al hacer clic
                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borde del botón
                tablero[i][j].setBackground(Color.BLACK); // Fondo del botón
                tablero[i][j].setOpaque(true);
                tablero[i][j].addActionListener(e -> jugar(e));
                this.add(tablero[i][j]);
            }
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void jugar(ActionEvent e) {

        // Obtenemos el input del botón que clicó
        JButton botonPulsado = (JButton) e.getSource();

        // Evita que se haga clic en botones ya ocupados
        if (!botonPulsado.getText().equals("")) {
            return;
        }

        // Turno del jugador verde
        if (jugadorVerde) {
            botonPulsado.setText("X");
            botonPulsado.setForeground(Color.GREEN);
            jugadorVerde = false;
        }
        // Turno del jugador rojo
        else {
            botonPulsado.setText("O");
            botonPulsado.setForeground(Color.RED);
            jugadorVerde = true;
        }

        ronda++;
        verificarFinalPartida();
    }

    private boolean verificar3Raya(JButton p1, JButton p2, JButton p3){
        return p1.getText().equals(p2.getText()) && p2.getText().equals(p3.getText())
                && (!p1.getText().equals(""));
    }

    private void verificarFinalPartida() {

        // 3 en raya en horizontal o vertical
        for (int i = 0; i < 3; i++) {
            if (verificar3Raya(tablero[i][0], tablero[i][1], tablero[i][2])
                    || verificar3Raya(tablero[0][i], tablero[1][i], tablero[2][i])) {
                victoria();
                return;
            }
        }
        // 3 en raya en diagonal
        if (verificar3Raya(tablero[0][0], tablero[1][1], tablero[2][2])
                || verificar3Raya(tablero[0][2], tablero[1][1], tablero[2][0])) {
            victoria();
            return;
        }

        // Empate
        if (ronda == 9) {
            empate();
        }
    }

    private void victoria() {
        String ganador;

        if (jugadorVerde) {
            ganador = "Rojo";
        } else {
            ganador = "Verde";
        }
        JOptionPane.showMessageDialog(this, "El ganador es el jugador " + ganador);
        reiniciarJuego();
    }

    private void empate() {
        JOptionPane.showMessageDialog(this, "Empate");
        reiniciarJuego();
    }

    private void reiniciarJuego() {
        // Reiniciar botones
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j].setText(""); // Eliminar texto de los botones
                tablero[i][j].setEnabled(true);
            }
        }

        jugadorVerde = true; // Volver al turno del jugador verde
        ronda = 0;
    }
}
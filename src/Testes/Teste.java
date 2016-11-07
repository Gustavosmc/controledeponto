package Testes;

import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Teste {

	public static void main(String[] args) {

		System.err.println("começou");
		Scanner s = null;
		Process p = null;
		try {
			//Process pb = new ProcessBuilder("set PGPASSWORD=115798").start();
			p = Runtime.getRuntime().exec("export PGPASSWORD=115798 gksudo");
			s = new Scanner(p.getInputStream());
			System.out.println(s);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		while (s.hasNext()) {
			System.err.println(s);
		}

	}

}

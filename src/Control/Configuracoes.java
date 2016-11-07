package Control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Configuracoes {

	public static String getPathDataBase() {
		String nome = "./Configuracoes/Config.ini";
		String linha = null;
		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			linha = lerArq.readLine(); // l� a primeira linha
			// a vari�vel "linha" recebe o valor "null" quando o processo
			// de repeti��o atingir o final do arquivo texto
			// while (linha != null) {
			// //System.out.printf("%s\n", linha);
			// linha = lerArq.readLine(); // l� da segunda at� a �ltima linha
			// }
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}
		return linha;
	}

	
	public String getApplicationPath() {
		String url = getClass().getResource("").getPath();
		File dir = new File(url).getParentFile();
		String path = null;

		if (dir.getPath().contains(".jar")) {
			path = findJarParentPath(dir);
		} else {
			path = dir.getPath();
		}

		try {
			return URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return path.replace("%20", " ");
		}
	}

	private static String findJarParentPath(File jarFile) {
		while (jarFile.getPath().contains(".jar")) {
			jarFile = jarFile.getParentFile();
		}

		return jarFile.getPath().substring(6);
	}
}

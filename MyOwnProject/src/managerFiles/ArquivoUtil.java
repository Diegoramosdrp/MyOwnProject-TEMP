package managerFiles;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class ArquivoUtil {

	
	public static List<File> listar() {
        File dir = diretorioRaizParaArquivos();

        return Arrays.asList(dir.listFiles());
	}
	
	public static File escrever(String name, byte[] contents) throws IOException {
        File file = new File(diretorioRaizParaArquivos(), name);

        OutputStream out = new FileOutputStream(file);
        out.write(contents);
        out.close();

        return file;
    }
	
	public static File diretorioRaizParaArquivos() {
        File dir = new File(diretorioRaiz(), "arquivos");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        return dir;
	}
	
	public static File diretorioRaiz() {
        File dir = new File("C:\\Users\\Usuario\\Desktop\\upload\\");

        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
	}
}

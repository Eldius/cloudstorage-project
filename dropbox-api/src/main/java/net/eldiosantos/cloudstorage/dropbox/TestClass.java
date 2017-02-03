package net.eldiosantos.cloudstorage.dropbox;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by esjunior on 02/02/2017.
 */
public class TestClass {
    public static void main(String params[]) {
        try {
            ProcessBuilder pb;
            Process p;
            pb = new ProcessBuilder("cmd", "/C", "c:\\Windows\\System32\\PING.EXE", "-n", "10", "8.8.8.8");
            pb.redirectErrorStream(true);
            File file = Paths.get("output.txt").toFile();
            File error = Paths.get("error_output.txt").toFile();
            if(file.exists()) {
                file.delete();
            }
            if(error.exists()) {
                error.delete();
            }
            error.createNewFile();
            pb.redirectOutput(file);
            pb.redirectError(error);
            pb.environment().put("PGPASSWORD", "SENHA DO BANCO");
            p = pb.start();

            // VERIFICAR SE PROCESSO AINDA ESTA ATIVO
            do {
                Thread.sleep(100L);
            } while(p.isAlive());
            JOptionPane.showMessageDialog(null, "Backup Gerado com sucesso!\n A Aplicação  será finalizada para efetivação do Backup. ", "BACKUP!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro ao GERAR O BACKUP: " + ex.getMessage(), "ERRO BAKCUP!", JOptionPane.ERROR_MESSAGE);
        }
    }
}

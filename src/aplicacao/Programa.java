package aplicacao;

import entidade.Produto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {
        Scanner dados = new Scanner (System.in);
        List <Produto> lista = new ArrayList<>();
             
        System.out.println("==============================");
        System.out.println("       ITENS DE COMPRAS       ");
        System.out.print("Quantos produtos a serem cadastrados: ");
        int num = dados.nextInt();
        System.out.println("==============================");
        
        for (int i = 0; i < num; i++){
            System.out.println((i+1) + "° CADASTRO");
            System.out.print("Produto: ");
            dados.nextLine();
            String nome = dados.nextLine();
            System.out.print("Valor: R$ ");
            double valor = dados.nextDouble();
            System.out.print("Quantidade: ");
            int qde = dados.nextInt();
            lista.add(new Produto(nome, valor, qde));
            System.out.println("==============================");
        }
        

        System.out.println("Caminho para salvar lista de compras: ");
        dados.nextLine();
        String caminho = dados.nextLine();
        
        //INFORMA O NOME DO ARQUIVO NA EXTENÇÃO DE EXCEL
        File arquivo = new File(caminho + "\\summary.csv");  
        
        //CRIA O ARQUIVO (FILEWRITER)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))){
            for(Produto linha: lista){
                //ESCREVE CADA ITEM EM UMA LINHA
                bw.write(linha.getNome());
                bw.write(linha.getQde());
                //QUEBRA DE LINHA NO ARQUIVO
                bw.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        System.out.println("==============================");
        System.out.println("       LISTA DE COMPRAS       ");
        System.out.println("==============================");
        for(Produto comp: lista){
            System.out.println(comp);
        }
        
        dados.close();
    }
    
}

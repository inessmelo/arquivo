package aplicacao;

import entidade.Produto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
            System.out.println();
        }
        
        System.out.println("====================================");
        System.out.println("        CAMINHO PARA SALVAR         ");
        System.out.println("====================================");

        System.out.println("Caminho: ");
        dados.nextLine();
        String c = dados.nextLine();        
        //INFORMA O NOME DO ARQUIVO NA EXTENÇÃO DE EXCEL
        File caminho = new File(c + "\\itens.csv");  
        
        String novoCaminho = caminho.getParent();
        //VERIFICA SE RETORNA V O DIRETORIO(MKDIR)
        boolean novo = new File(caminho + "\\ItensVendido").mkdir();
        //CRIA UM NOVO CAMINHO PARA SALVAR OUTRO ARQUIVO
        String arquivo = novoCaminho + "\\arquivoNovo.csv";
        //CRIEI UM ARQUIVO *CSV E O USUSARIO SALVOU OS DADOS DENTRO E ACRESCENTA COM O TRUE
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))){  
            
            for (Produto item: lista){                
                //SALVA EM CADA LINHA DO EXCEL
                bw.write(item.getQde() + "," + item.getNome() + "," + String.format("%.2f", item.getPreco()));
                //ESCREVE UMA NOVA LINHA
                bw.newLine();                  
            }
        }
        catch (IOException e){
            System.out.println("Erro: " + e.getMessage());
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            //LENDO UMA LINHA DO ARQUIVO
            String itemNovo = br.readLine();
            while (itemNovo != null){
                String[] lendo = itemNovo.split(",");
                int qde = Integer.parseInt(lendo[0]);
                String nome = lendo[1];
                double valor = Double.parseDouble(lendo[2]);
                
                //INSTANCIA UMA NOVA LISTA NO ARQUIVO NOVO
                lista.add(new Produto(nome, valor, qde));
                //LENDO MAIS UMA LINHA
                itemNovo = br.readLine();
            }
            
            try (BufferedWriter b = new BufferedWriter(new FileWriter(arquivo))){
                
                for(Produto item: lista){
                    b.write(item.getNome() + String.format(" %.2f", item.total()));
                    b.newLine();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        
        catch (IOException e){
            e.printStackTrace();
        }
        
        System.out.println("====================================");
        dados.close();
    }
    
}

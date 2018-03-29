import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception 
	{
		InputStream entrada = null;
		OutputStream saida = null;
		int qtd;
		ArrayList<Endereco> EnderecoLista = new ArrayList<Endereco>();
		byte buffer[] = new byte[300];
		
		RandomAccessFile f = new RandomAccessFile("cep_ordenado.dat", "r");
		
		long ultimo = f.length()/300L;
		int cepAchar = Integer.parseInt(args[0]);
		if(args[0].length()==8){
			System.out.println(buscaBinariaRecursiva(f, 0, ultimo, cepAchar));
		}
		else{
			System.out.println("O CEP inserido deve ter 8 digitos");
		}

        f.close();	
	}


	public static String buscaBinariaRecursiva(RandomAccessFile f, long menor, long maior, int cepAchar) throws IOException{
		long meio = (maior + menor)/2;
		f.seek(meio*300);
		Endereco e = new Endereco();
		e.leEndereco(f);
		int valorMeio = Integer.parseInt(e.getCep());
		if(menor > maior){
			return "Não existe o CEP pesquisado";
		}
		else if(valorMeio == cepAchar){
			return "Logradouro: " + e.getLogradouro() + "\nBairro:" + e.getBairro() +"\nCidade: " + e.getCidade() +"\nEstado: " + e.getEstado() +"\nSigla: " + e.getSigla() +"\nCEP: " + e.getCep();
		}
		else if(valorMeio < cepAchar){
			return buscaBinariaRecursiva(f, meio+1, maior, cepAchar);
		}
		else{
			return buscaBinariaRecursiva(f, menor, meio-1, cepAchar);
		}
	}
}

